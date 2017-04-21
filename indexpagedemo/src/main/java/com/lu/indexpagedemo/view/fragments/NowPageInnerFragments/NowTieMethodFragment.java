package com.lu.indexpagedemo.view.fragments.NowPageInnerFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.mvp.MvpBaseFragment;
import com.lu.indexpagedemo.contract.NowTieMethodContract;
import com.lu.indexpagedemo.presenter.NowTieMethodPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NowTieMethodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NowTieMethodFragment extends MvpBaseFragment<NowTieMethodContract.View,NowTieMethodPresenterImpl>implements NowTieMethodContract.View {

    public final static String TAG = "NowTieMethodFragment";

    public NowTieMethodFragment() {
        // Required empty public constructor
    }


    public static NowTieMethodFragment newInstance() {
        return new NowTieMethodFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected NowTieMethodPresenterImpl createPresent() {
        return new NowTieMethodPresenterImpl();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_tiemethod, container, false);
    }

    @Override
    public String getTAG() {
        return TAG;
    }
}
