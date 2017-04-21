package com.lu.indexpagedemo.view.fragments.NowPageInnerFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.mvp.MvpBaseFragment;
import com.lu.indexpagedemo.contract.NowMatchContract;
import com.lu.indexpagedemo.contract.NowMaterialContract;
import com.lu.indexpagedemo.contract.NowRecommendContract;
import com.lu.indexpagedemo.presenter.NowMatchPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NowMatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NowMatchFragment extends MvpBaseFragment<NowMaterialContract.View,NowMatchPresenterImpl>implements NowMatchContract.View {

    public NowMatchFragment() {
        // Required empty public constructor
    }


    public static NowMatchFragment newInstance() {
        return new NowMatchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected NowMatchPresenterImpl createPresent() {
        return new NowMatchPresenterImpl();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_match, container, false);
    }

}
