package com.lu.indexpagedemo.view.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.contract.UserCenterContract;
import com.lu.indexpagedemo.base.mvp.MvpBaseFragment;
import com.lu.indexpagedemo.presenter.UserCenterPresenterImpl;
import com.lu.indexpagedemo.view.activitys.CartActivity;
import com.lu.indexpagedemo.view.activitys.FocusActivity;
import com.lu.indexpagedemo.view.activitys.MyAddressActivity;
import com.lu.indexpagedemo.view.activitys.MyInformationActivity;
import com.lu.indexpagedemo.view.activitys.MyMoneyActivity;
import com.lu.indexpagedemo.view.activitys.MyOrderActivity;
import com.lu.indexpagedemo.view.simpleviews.DailogActivity;
import com.lu.indexpagedemo.view.simpleviews.SettingsActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserCenterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserCenterFragment extends MvpBaseFragment<UserCenterContract.View,UserCenterPresenterImpl>implements UserCenterContract.View, View.OnClickListener {

    private SimpleDraweeView mHeader;
    private LinearLayout mOrder;
    private LinearLayout mCart;
    private LinearLayout mAddress;
    private LinearLayout mMoney;
    private LinearLayout mLove;
    private LinearLayout mFouce;
    private LinearLayout mMessageCenter;
    private SimpleDraweeView mMessage;
    private LinearLayout mServer;
    private LinearLayout mSetting;
    private LinearLayout mQuestion;


    private static final String TAG = "UserCenterFragment";

    public UserCenterFragment() {
        // Required empty public constructor
    }

    public static UserCenterFragment newInstance() {
        return new UserCenterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_user_center, container, false);

            mOrder = (LinearLayout) rootView.findViewById(R.id.myorders);
            mCart = (LinearLayout) rootView.findViewById(R.id.cart);
            mAddress = (LinearLayout) rootView.findViewById(R.id.addresslinearlayout);
            mMoney = (LinearLayout) rootView.findViewById(R.id.mymoney);
            mLove = (LinearLayout) rootView.findViewById(R.id.mylove);
            mFouce = (LinearLayout) rootView.findViewById(R.id.myfocus);
            mMessageCenter = (LinearLayout) rootView.findViewById(R.id.messagecenter);
            mMessage =(SimpleDraweeView) rootView.findViewById(R.id.message);
            mServer = (LinearLayout) rootView.findViewById(R.id.server);
            mQuestion = (LinearLayout) rootView.findViewById(R.id.question);
            mHeader = (SimpleDraweeView) rootView.findViewById(R.id.usercenter_head);
            mSetting = (LinearLayout) rootView.findViewById(R.id.setting);

            mHeader.setOnClickListener(this);
            mQuestion.setOnClickListener(this);
            mAddress.setOnClickListener(this);
            mMessage.setOnClickListener(this);
            mMessageCenter.setOnClickListener(this);
            mMoney.setOnClickListener(this);
            mLove.setOnClickListener(this);
            mFouce.setOnClickListener(this);
            mOrder.setOnClickListener(this);
            mSetting.setOnClickListener(this);
            mServer.setOnClickListener(this);
            mCart.setOnClickListener(this);
        }
        return rootView;
    }
    @Override
    protected UserCenterPresenterImpl createPresent() {
        return new UserCenterPresenterImpl();
    }

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.usercenter_head:
                goUserInformationSetting();
                break;
            case R.id.mymoney:
                goMyMoney();
                break;
            case R.id.mylove:
                goMyLove();
                break;
            case R.id.myfocus:
                goMyFocus();
                break;
            case R.id.addresslinearlayout:
                goMyAddress();
                break;
            case R.id.messagecenter:
                goMesssageCenter();
                break;
            case R.id.message:
                goMessages();
                break;
            case R.id.setting:
                goSetting();
                break;
            case R.id.question:
                goQuestion();
                break;
            case R.id.server:
                goServer();
                break;
            case R.id.cart:
                goCart();
                break;
            case R.id.myorders:
                goMyOrder();
                break;
        }
    }

    private void goMyOrder() {
        openActivity(MyOrderActivity.class);
    }

    private void goCart() {
        openActivity(CartActivity.class);
    }

    private void goServer() {

    }

    private void goQuestion() {

    }

    private void goSetting() {
        openActivity(SettingsActivity.class);
    }

    private void goMessages() {
        openActivity(DailogActivity.class);
    }

    private void goMesssageCenter() {

    }

    private void goMyAddress() {
        openActivity(MyAddressActivity.class);
    }

    private void goMyFocus() {
        openActivity(FocusActivity.class);
    }

    private void goMyLove() {

    }

    private void goMyMoney() {
        openActivity(MyMoneyActivity.class);
    }

    private void goUserInformationSetting(){
        openActivity(MyInformationActivity.class);
    }

    private void openActivity(Class clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

}
