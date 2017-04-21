package com.lu.indexpagedemo.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.mvp.MvpBaseFragment;
import com.lu.indexpagedemo.contract.NowContract;
import com.lu.indexpagedemo.presenter.NowPresenterImpl;
import com.lu.indexpagedemo.view.fragments.NowPageInnerFragments.NowMatchFragment;
import com.lu.indexpagedemo.view.fragments.NowPageInnerFragments.NowMaterialFragment;
import com.lu.indexpagedemo.view.fragments.NowPageInnerFragments.NowRecommendFragment;
import com.lu.indexpagedemo.view.fragments.NowPageInnerFragments.NowTieMethodFragment;

import java.util.ArrayList;

/**
 * Created by 陆正威 on 2017/4/12.
 */

public class NowFragment extends MvpBaseFragment<NowContract.View, NowPresenterImpl> implements NowContract.View {

    private static final String TAG = "NowFragment";
    private View view;
    private SlidingTabLayout mNowTablayout;
    private Toolbar mNowToolbar;
    private ViewPager mNowViewpager;
    private NowFragmentPagerAdapter mNowFragmentPagerAdapter;

    public NowFragment() {
    }

    public static NowFragment newInstance() {
        return new NowFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_now, container, false);
            initView(rootView);
        }

        return rootView;
    }

    @Override
    protected NowPresenterImpl createPresent() {
        return new NowPresenterImpl();
    }

    @Override
    public String getTAG() {
        return TAG;
    }

    private void initView(View rootView) {
        mNowTablayout = (SlidingTabLayout) rootView.findViewById(R.id.now_tablayout);
        //mNowToolbar = (Toolbar) rootView.findViewById(R.id.now_toolbar);
        mNowViewpager = (ViewPager) rootView.findViewById(R.id.now_viewpager);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(NowRecommendFragment.newInstance());
        fragments.add(NowMaterialFragment.newInstance());
        fragments.add(NowTieMethodFragment.newInstance());
        fragments.add(NowMatchFragment.newInstance());

        mNowFragmentPagerAdapter = new NowFragmentPagerAdapter(getChildFragmentManager(),fragments);
        String titles[] = getResources().getStringArray(R.array.now_viewpager_titles);
        mNowViewpager.setAdapter(mNowFragmentPagerAdapter);
        mNowTablayout.setViewPager(mNowViewpager,titles);
    }

}

class NowFragmentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> listFragments;

    public NowFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> al) {
        super(fm);
        if(al == null)
            al = new ArrayList<>();
        listFragments = al;
    }

    public void setFragments(ArrayList<Fragment> fragments){
        listFragments = fragments;
    }

    public NowFragmentPagerAdapter(FragmentManager fm) {
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