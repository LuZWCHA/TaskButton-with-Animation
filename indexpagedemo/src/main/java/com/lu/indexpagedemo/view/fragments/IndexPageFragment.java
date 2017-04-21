package com.lu.indexpagedemo.view.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.flyco.tablayout.SlidingTabLayout;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.lu.indexpagedemo.base.Tools.AppManager;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.Constant;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.contract.IndexPageContract;
import com.lu.indexpagedemo.base.mvp.MvpBaseFragment;
import com.lu.indexpagedemo.presenter.IndexPagePresenterImpl;
import com.lu.indexpagedemo.base.rxjava.Notifys.MyNotifys;
import com.lu.indexpagedemo.base.rxjava.Progrezz;
import com.lu.indexpagedemo.view.adapters.BaseRecyclerViewAdapter;
import com.lu.indexpagedemo.view.fragments.IndexPageInnerFragments.DesignerFragment;
import com.lu.indexpagedemo.view.fragments.IndexPageInnerFragments.RecommendFragment;
import com.lu.indexpagedemo.view.fragments.IndexPageInnerFragments.WorksFragment;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link IndexPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IndexPageFragment extends MvpBaseFragment<IndexPageContract.View, IndexPagePresenterImpl> implements IndexPageContract.View {

    private final static String TAG = "IndexPageFragment";

    private static final int DEFULT_DURTION = 500;//mils

    private BaseRecyclerViewAdapter.OnItemClickListener mListener;

    private Resources mResources;

    private NowFragmentPagerAdapter mIndexPageAdapter;
    private RollPagerView mRollPagerView;
    private ViewPager mViewPager;
    private SlidingTabLayout mSlidingTabLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private MaterialSearchView mSearchView;
    private Toolbar mToolbar;
    private TestLoopAdapter mTestLoopAdapter;
    private AppBarLayout mAppBarLayout;
    private PtrFrameLayout mPtrFrameLayout;
    private int appbarState;
    private Progrezz mProgrezz;//计数器计算来自于子fragments的更新是否完成
    public Progrezz.OnProgrezzEndListener updatefinish;


    public IndexPageFragment() {
    }

    public static IndexPageFragment newInstance() {
        return new IndexPageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);

        mResources = getResources();
        mProgrezz = Progrezz.create("update_progress", 2);
        updatefinish = new Progrezz.OnProgrezzEndListener() {
            @Override
            public void onEnd() {
                hideLoadingHeader();
            }
        };
        mProgrezz.setOnProgrezzEndListener(updatefinish);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_indexpage, container, false);
            mRollPagerView = (RollPagerView) rootView.findViewById(R.id.rollpageview);
            mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
            mPtrFrameLayout = (PtrFrameLayout) rootView.findViewById(R.id.ptrframelayout);
            mAppBarLayout = (AppBarLayout) rootView.findViewById(R.id.appbarlayout);
            mSlidingTabLayout = (SlidingTabLayout) rootView.findViewById(R.id.tablayout);
            mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
            mSearchView = (MaterialSearchView) rootView.findViewById(R.id.search_view);
            mTestLoopAdapter = new TestLoopAdapter(mRollPagerView);
            viewPagerInitiz();
            rollPagerViewInitiz();
            toolBarInitiz();
            searchViewInitiz();
            appBarInitiz();
            ptrFrameLayoutInitiz();
        }
        return rootView;
    }

    @Override
    protected void onFragmentVisible(boolean isFirstVisible) {
        super.onFragmentVisible(isFirstVisible);
        EventBus.getDefault().register(this);
        mPresenter.initizRollPageView();
    }

    @Override
    protected void onFragmentInVisible() {
        super.onFragmentInVisible();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MyNotifys.UpdateFinishNotify event) {
        mProgrezz.go();
    }

    void ptrFrameLayoutInitiz() {
        final StoreHouseHeader header = new StoreHouseHeader(AppManager.app());
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);

        header.initWithString("LOADING");
        header.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        header.setLineWidth(4);
        mPtrFrameLayout.setHeaderView(header);
        mPtrFrameLayout.disableWhenHorizontalMove(true);
        mPtrFrameLayout.addPtrUIHandler(header);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return appbarState == 0 && PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPresenter.updateRollPageView();
                EventBus.getDefault().post(new MyNotifys.UpdateNotify());
            }
        });
    }

    void appBarInitiz() {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                appbarState = verticalOffset;
            }
        });
    }

    void toolBarInitiz() {
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        mToolbar.inflateMenu(R.menu.searchview_item_menu);
    }

    void searchViewInitiz() {
        MenuItem item = mToolbar.getMenu().findItem(R.id.action_search);
        mSearchView.setMenuItem(item);
    }

    void viewPagerInitiz() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(RecommendFragment.newInstance());
        fragments.add(WorksFragment.newInstance());
        fragments.add(DesignerFragment.newInstance());

        mIndexPageAdapter = new NowFragmentPagerAdapter(getChildFragmentManager(), fragments);
        String[] titles = mResources.getStringArray(R.array.indexpage_viewpager_titles);
        mViewPager.setAdapter(mIndexPageAdapter);
        mSlidingTabLayout.setViewPager(mViewPager, titles);
    }

    void rollPagerViewInitiz() {
        mRollPagerView.setAdapter(mTestLoopAdapter);
        mRollPagerView.setAnimationDurtion(DEFULT_DURTION);
    }

    @Override
    protected IndexPagePresenterImpl createPresent() {
        return new IndexPagePresenterImpl();
    }

    @Override
    public void updateRollViewData(List<String> newdata) {
        mTestLoopAdapter.updateViewData(newdata);
    }

    @Override
    public void updateOne() {
        mProgrezz.go();
    }

    @Override
    public void hideLoadingHeader() {
        mPtrFrameLayout.refreshComplete();
    }

    @Override
    public String getTAG() {
        return TAG;
    }


}

class TestLoopAdapter extends LoopPagerAdapter {
    private List<String> imageUrls = new ArrayList<>();

    public TestLoopAdapter(@NonNull RollPagerView viewPager) {
        super(viewPager);
        this.imageUrls = new ArrayList<>();
    }

    public void updateViewData(List<String> newdata) {
        imageUrls = newdata;
        notifyDataSetChanged();
    }

    @Override
    public View getView(ViewGroup container, int position) {
        SimpleDraweeView view = new SimpleDraweeView(AppManager.app());
        Utils.load(imageUrls.get(position), view, Constant.screenwithpx, Utils.dip2px(250));
        return view;
    }

    @Override
    public int getRealCount() {
        return imageUrls.size();
    }
}

class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> listFragments;

    MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> al) {
        super(fm);
        listFragments = al;
    }

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
