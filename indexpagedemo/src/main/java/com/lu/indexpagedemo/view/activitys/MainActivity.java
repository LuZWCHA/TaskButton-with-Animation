package com.lu.indexpagedemo.view.activitys;

import android.os.Bundle;
import android.util.Log;

import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.base.mvp.MvpBaseFragment;
import com.lu.indexpagedemo.base.rxjava.Notifys.BottomBarToggler;
import com.lu.indexpagedemo.bean.base.customview.BottomBar;
import com.lu.indexpagedemo.bean.base.customview.BottomBarTab;
import com.lu.indexpagedemo.view.fragments.CustomFragment;
import com.lu.indexpagedemo.view.fragments.IndexPageFragment;
import com.lu.indexpagedemo.view.fragments.NowFragment;
import com.lu.indexpagedemo.view.fragments.UserCenterFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import me.yokeyword.fragmentation.SupportActivity;


public class MainActivity extends SupportActivity {

    private final static int INDEXPAGE_FRAGMENT = 0;
    private final static int CUMSTOM_FRAGMENT = 1;
    private final static int NOW_FRAGMENT = 2;
    private final static int USERCENTER_FRAGMENT = 3;


    private BottomBar mBottomBar;
    private MvpBaseFragment[] mFragments = new MvpBaseFragment[5];
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.TRANSPARENT(getWindow());
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            mFragments[INDEXPAGE_FRAGMENT] = IndexPageFragment.newInstance();
            mFragments[NOW_FRAGMENT] = NowFragment.newInstance();
            mFragments[CUMSTOM_FRAGMENT] = CustomFragment.newInstance();
            mFragments[USERCENTER_FRAGMENT] = UserCenterFragment.newInstance();
            loadMultipleRootFragment(R.id.fragements_container,INDEXPAGE_FRAGMENT,
                    mFragments[INDEXPAGE_FRAGMENT],
                    mFragments[CUMSTOM_FRAGMENT],
                    mFragments[NOW_FRAGMENT],
                    mFragments[USERCENTER_FRAGMENT]);
            bottomNativeBarInitiz();

        }else {
            mFragments[INDEXPAGE_FRAGMENT] = findFragment(IndexPageFragment.class);
            mFragments[NOW_FRAGMENT] = findFragment(NowFragment.class);
            mFragments[CUMSTOM_FRAGMENT] = findFragment(CustomFragment.class);
            mFragments[USERCENTER_FRAGMENT] = findFragment(UserCenterFragment.class);
        }
        EventBus.getDefault().register(this);
    }

    private void bottomNativeBarInitiz() {
        mBottomBar = (BottomBar) findViewById(R.id.bottombar);
        mBottomBar.addItem(new BottomBarTab(this, R.drawable.ic_search,R.drawable.ic_main_bottombar_explore))
                .addItem(new BottomBarTab(this, R.drawable.ic_cloud,R.drawable.ic_main_bottombar_customization))
                .addItem(new BottomBarTab(this, R.drawable.ic_moment,R.drawable.ic_main_bottombar_now))
                .addItem(new BottomBarTab(this, R.drawable.ic_user,R.drawable.ic_main_bottombar_usercenter));
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                Log.e("select", position + "");
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Subscribe
    public void hideorShowBottomBar(BottomBarToggler bottomBarToggler)
    {
        if(bottomBarToggler.isShow() && !mBottomBar.isVisible())
            mBottomBar.show();
        else if(!bottomBarToggler.isShow() && mBottomBar.isVisible())
            mBottomBar.hide();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

