package com.lu.indexpagedemo.view.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.daimajia.swipe.SwipeLayout;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.contract.CustomContract;
import com.lu.indexpagedemo.base.mvp.MvpBaseFragment;
import com.lu.indexpagedemo.presenter.CustomPresenterImpl;
import com.lu.indexpagedemo.view.activitys.CustomSelectorActivity;
import com.lu.indexpagedemo.view.activitys.TestActivity;

public class CustomFragment extends MvpBaseFragment<CustomContract.View,CustomPresenterImpl>implements CustomContract.View{
    public CustomFragment() {
        // Required empty public constructor
    }

    public static CustomFragment newInstance() {

        return new CustomFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_custom, container, false);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CustomSelectorActivity.class));
            }
        });
        return rootView;
    }

    @Override
    protected CustomPresenterImpl createPresent() {
        return new CustomPresenterImpl();
    }

}
