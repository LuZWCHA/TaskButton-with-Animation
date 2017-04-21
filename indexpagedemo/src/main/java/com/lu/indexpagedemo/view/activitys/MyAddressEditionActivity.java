package com.lu.indexpagedemo.view.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.lljjcoder.citypickerview.widget.CityPicker;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.base.mvp.MvpBaseActivity;
import com.lu.indexpagedemo.bean.AddressBean;
import com.lu.indexpagedemo.contract.MyAddressEditionContract;
import com.lu.indexpagedemo.presenter.MyAddressEditionPresenterImpl;

import org.greenrobot.eventbus.EventBus;

public class MyAddressEditionActivity extends MvpBaseActivity<MyAddressEditionContract.View, MyAddressEditionPresenterImpl> implements MyAddressEditionContract.View {

    AddressBean addressBean;
    CityPicker mCityPicker;
    AppCompatButton mSaveButton;
    EditText mUserNameEditText;
    EditText mMainAddressEditText;
    EditText mSubAddressEditText;
    EditText mPhoneEditText;
    RadioGroup mSexSelectorGroup;
    Toolbar mToolBar;

    boolean isCitySelect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address_edition);
        if (savedInstanceState == null) {
            addressBean = getIntent().getParcelableExtra("edit_address");
            if (addressBean == null)
                addressBean = new AddressBean();
            InitizCityPicker();
            InitizOtherViews();
        }
    }

    private void InitizCityPicker() {
        mCityPicker = new CityPicker.Builder(this)
                .textSize(20)
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#ffffff")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("浙江省")
                .city("杭州市")
                .district("西湖区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        mCityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];

                addressBean.setCode(Integer.parseInt(code));
                addressBean.setAreaDetile(province + "-" + city + "-" + district);
                mMainAddressEditText.setText(addressBean.getAreaDetile());
                isCitySelect = true;
            }

            @Override
            public void onCancel() {
                Utils.MakeTost(false, "已取消");
            }
        });

    }

    private void InitizOtherViews() {
        mToolBar = (Toolbar) findViewById(R.id.address_add_toolbar);
        mSaveButton = (AppCompatButton) findViewById(R.id.address_add_savabutton);
        mUserNameEditText = (EditText) findViewById(R.id.address_add_username);
        mMainAddressEditText = (EditText) findViewById(R.id.address_add_mainaddress);
        mSubAddressEditText = (EditText) findViewById(R.id.address_add_subaddress);
        mSexSelectorGroup = (RadioGroup) findViewById(R.id.sex_group);
        mPhoneEditText = (EditText) findViewById(R.id.address_add_phone);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mMainAddressEditText.setFocusable(false);
        mMainAddressEditText.setFocusableInTouchMode(false);
        mMainAddressEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCityPicker.show();
            }
        });
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEmpty()) {
                    addressBean.setAreaDetile(mMainAddressEditText.getText() + "-" + mSubAddressEditText.getText());
                    addressBean.setRealName(mUserNameEditText.getText().toString());
                    addressBean.setSex(mSexSelectorGroup.getCheckedRadioButtonId() == R.id.man_button);
                    addressBean.setPhoneNum(mPhoneEditText.getText().toString());
                    mPresenter.saveNewAddress(addressBean);
                    EventBus.getDefault().post(addressBean);
                    finish();
                } else {
                    Utils.MakeTost(true, "请填写完整的信息");
                }
            }
        });

        if (addressBean != null && addressBean.getCode() != -1) {

            mPhoneEditText.setText(addressBean.getPhoneNum());
            mUserNameEditText.setText(addressBean.getRealName());

            int p = addressBean.getAreaDetile().lastIndexOf('-');
            String mainAddress = addressBean.getAreaDetile().substring(0,p);
            String subAddress = addressBean.getAreaDetile().substring(p+1);
            mMainAddressEditText.setText(mainAddress);
            mSubAddressEditText.setText(subAddress);
            isCitySelect = true;
        }
    }

    private boolean checkEmpty() {
        return !(TextUtils.isEmpty(mUserNameEditText.getText()) || TextUtils.isEmpty(mSubAddressEditText.getText())
                || TextUtils.isEmpty(mPhoneEditText.getText()) || !isCitySelect);
    }

    @Override
    protected MyAddressEditionPresenterImpl createPresent() {
        return new MyAddressEditionPresenterImpl();
    }

    @Override
    public void uploadNewAddress() {

    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showFail() {
    }


}
