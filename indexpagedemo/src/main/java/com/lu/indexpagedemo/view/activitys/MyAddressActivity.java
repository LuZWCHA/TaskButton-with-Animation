package com.lu.indexpagedemo.view.activitys;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Telephony;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.base.mvp.MvpBaseActivity;
import com.lu.indexpagedemo.base.rxjava.RxBus;
import com.lu.indexpagedemo.bean.AddressBean;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.contract.MyAddressContract;
import com.lu.indexpagedemo.presenter.MyAddressPresenterImpl;
import com.lu.indexpagedemo.view.adapters.MyMultiAdapter;
import com.lu.indexpagedemo.view.adapters.MyRecyclerView;
import com.lu.indexpagedemo.view.viewholders.AddressViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MyAddressActivity extends MvpBaseActivity<MyAddressContract.View,MyAddressPresenterImpl> implements MyAddressContract.View {

    private final static String TAG = "MyAddressActivity";

    public final static int EDIT_MODE = 0;
    public final static int SELECT_MODE = 1;
    public final static int SELECT_SUCCESS = 2;

    private MyRecyclerView myRecyclerView;
    private BaseQuickAdapter<AddressBean,AddressViewHolder> mAddressAdapter;
    private Toolbar mToolBar;
    private Button mButton;
    private int mode = EDIT_MODE;

    private boolean waitEditResult = false;
    private int editPoistion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        EventBus.getDefault().register(this);

        mToolBar = (Toolbar) findViewById(R.id.address_toolbar);
        if(savedInstanceState == null){

            mode = getIntent().getIntExtra("mode",EDIT_MODE);

            mButton = (Button) findViewById(R.id.address_addbutton);
            myRecyclerView = (MyRecyclerView) findViewById(R.id.myaddresslist);

            mAddressAdapter = new BaseQuickAdapter<AddressBean, AddressViewHolder>(R.layout.recycleview_item_address,null) {
                @Override
                protected void convert(AddressViewHolder helper, AddressBean item) {
                    helper.setText(R.id.address_textview,item.getAreaDetile());
                    helper.setText(R.id.name_phonenum_textview,item.getRealName()+" "+item.getPhoneNum());
                }
            };

            mAddressAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if(mode == EDIT_MODE) {
                        Intent intent = new Intent(MyAddressActivity.this, MyAddressEditionActivity.class);
                        intent.putExtra("edit_address", (Parcelable) adapter.getItem(position));
                        waitEditResult = true;
                        editPoistion = position;
                        startActivity(intent);
                    }else {
                        Intent intent = new Intent();
                        intent.putExtra("address",(Parcelable) adapter.getItem(position));
                        setResult(SELECT_SUCCESS,intent);
                        finish();
                    }
                }
            });

            mAddressAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                    new AlertDialog.Builder(MyAddressActivity.this)
                            .setTitle("删除")
                            .setMessage("是要删除这个地址吗？")
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mPresenter.deleteAddress(mAddressAdapter.getItem(position));
                                    mAddressAdapter.remove(position);
                                }
                            }).setNegativeButton("否",null)
                            .show();
                    return true;
                }
            });

            mAddressAdapter.bindToRecyclerView(myRecyclerView);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            myRecyclerView.setLayoutManager(layoutManager);
            myRecyclerView.setAdapter(mAddressAdapter);
            mAddressAdapter.setEmptyView(R.layout.recyclerview_addressemptyview);
            mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MyAddressActivity.this,MyAddressEditionActivity.class));
                    waitEditResult = false;
                }
            });

            initAddresses();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMessage(AddressBean addressBean) {
        if(waitEditResult)
            mAddressAdapter.setData(editPoistion,addressBean);
        else
            addAddress(addressBean);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initAddresses() {
        mPresenter.initAddressList();
    }

    @Override
    public void updateAddress(List<AddressBean> addressBeanList) {
        mAddressAdapter.setNewData(addressBeanList);
    }

    @Override
    public void addAddress(AddressBean addressBean) {
        mAddressAdapter.addData(addressBean);
    }

    @Override
    protected MyAddressPresenterImpl createPresent() {
        return new MyAddressPresenterImpl();
    }

}
