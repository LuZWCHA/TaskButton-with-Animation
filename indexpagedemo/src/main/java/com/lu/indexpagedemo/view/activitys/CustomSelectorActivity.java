package com.lu.indexpagedemo.view.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.imnjh.imagepicker.SImagePicker;
import com.imnjh.imagepicker.activity.PhotoPickerActivity;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.Constant;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.bean.AddressBean;
import com.lu.indexpagedemo.view.adapters.MyRecyclerView;
import com.lu.indexpagedemo.view.viewholders.DragPicViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CustomSelectorActivity extends AppCompatActivity
        implements View.OnClickListener,
        NavigationTabStrip.OnTabStripSelectedIndexListener,
        RadioGroup.OnCheckedChangeListener,
        AdapterView.OnItemSelectedListener{

    private final static int PICK_IMAGE = 0x0101;
    private final static int PICK_ADDRESS = MyAddressActivity.SELECT_MODE;

    private MyDragAdapter myDragAdapter;

    private ArrayList<String> selectedList;

    private final int deflautPrice = 199;

    private int ObjectNum = 1;
    private int ObjectSize = 0;
    private int ObjectMaterial = 0;

    private int ChooseType = 0;

    private Toolbar mCustomToolbar;
    private NavigationTabStrip mCustomTabStrip;
    private AppCompatEditText mCustomTitle;
    private TextView mCustomTotalPrice;
    /**
     * 丝巾定制
     */
    private AppCompatEditText mCustomContent;
    private MyRecyclerView mCustomPicList;
    /**
     * 未选择
     */
    private TextView mCustomAddressChoose;
    private LinearLayout mCustomSelect;
    private AppCompatSpinner mCustomSizeChoose;
    private AppCompatEditText mCustomNumChoose;
    private RadioGroup mCustomMaterialChoose;
    /**
     * 0.0
     */
    private TextView mCustomSinglePrice;
    private AppCompatButton mCustomSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_selector);
        initView();

        selectedList = new ArrayList<>();
        mCustomPicList.setLayoutManager(new GridLayoutManager(this, 4));
        myDragAdapter = new MyDragAdapter(R.layout.recyclerview_item_custom_pics, selectedList);
        mCustomPicList.setAdapter(myDragAdapter);

        ItemDragAndSwipeCallback dragAndSwipeCallback = new ItemDragAndSwipeCallback(myDragAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(dragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(mCustomPicList);

        myDragAdapter.enableDragItem(itemTouchHelper);
        myDragAdapter.addData("add view");

        myDragAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Utils.MakeTost(false, position + "," + view.getId());
                if (view.getId() == R.id.custom_item_delete)
                    myDragAdapter.remove(position);
                else if (view.getId() == R.id.custom_item_add) {
                    ArrayList<String> list = selectedList;
                    list.remove(list.size() - 1);
                    choosePics(list);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            getPics(data);
        }
        if (requestCode == PICK_ADDRESS && resultCode == MyAddressActivity.SELECT_SUCCESS) {
           getAddress(data);
        }
    }

    private void initView() {

        mCustomMaterialChoose = (RadioGroup) findViewById(R.id.custom_material);
        mCustomMaterialChoose.setSelected(true);
        mCustomMaterialChoose.setOnCheckedChangeListener(this);

        mCustomToolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        mCustomToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mCustomTabStrip = (NavigationTabStrip) findViewById(R.id.custom_tab_strip);
        mCustomTabStrip.setTabIndex(0);
        mCustomTabStrip.setOnTabStripSelectedIndexListener(this);

        mCustomTitle = (AppCompatEditText) findViewById(R.id.custom_title);
        mCustomContent = (AppCompatEditText) findViewById(R.id.custom_content);

        mCustomPicList = (MyRecyclerView) findViewById(R.id.custom_pic_list);

        mCustomAddressChoose = (TextView) findViewById(R.id.custom_address_choose_textview);
        mCustomSelect = (LinearLayout) findViewById(R.id.custom_address_select_button);
        mCustomSelect.setOnClickListener(this);

        mCustomSizeChoose = (AppCompatSpinner) findViewById(R.id.custom_size_choose);
        mCustomSizeChoose.setSelection(0);
        mCustomSizeChoose.setOnItemSelectedListener(this);

        mCustomNumChoose = (AppCompatEditText) findViewById(R.id.custom_num_choose);
        mCustomNumChoose.setText("1");
        mCustomNumChoose.setTextIsSelectable(false);
        mCustomNumChoose.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s!= null && s.length()>0) {
                    ObjectNum = Integer.valueOf(s.toString());
                    if(Integer.valueOf(s.toString()) == 0){
                        mCustomNumChoose.setText(null);
                    }
                    priceChanged(true);
                }else {
                    ObjectNum = 0;
                    priceChanged(true);
                }
            }
        });

        mCustomSinglePrice = (TextView) findViewById(R.id.custom_single_price);
        mCustomSinglePrice.setText(String.valueOf(priceMap(1,deflautPrice,ObjectSize,ObjectMaterial)));

        mCustomTotalPrice = (TextView) findViewById(R.id.custom_total_price);
        mCustomTotalPrice.setText(String.valueOf(priceMap(ObjectNum,deflautPrice,ObjectSize,ObjectMaterial)));

        mCustomSubmit = (AppCompatButton) findViewById(R.id.custom_submit);
        mCustomSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_address_select_button:
                chooseAddress();
                break;
            case R.id.custom_submit:
                break;
        }
    }

    private boolean checkEmpty(){
        return !(TextUtils.isEmpty(mCustomTitle.getText()) || TextUtils.isEmpty(mCustomContent.getText()) || TextUtils.isEmpty(mCustomNumChoose.getText())
                || TextUtils.isEmpty(mCustomAddressChoose.getText()) || myDragAdapter.getData().size() < 2);
    }

    private int priceMap(int num,int primPrice,int size,int materal){
        return (primPrice + size*30 + materal*100) * num;
    }

    private void chooseAddress(){
        Intent intent = new Intent(CustomSelectorActivity.this, MyAddressActivity.class);
        intent.putExtra("mode", PICK_ADDRESS);
        startActivityForResult(intent, PICK_ADDRESS);
    }

    private void choosePics(ArrayList<String> seletedPic){
        SImagePicker
                .from(CustomSelectorActivity.this)
                .maxCount(9)
                .rowCount(3)
                .setSelected(seletedPic)
                .pickText(R.string.image_picker_text)
                .pickMode(SImagePicker.MODE_IMAGE)
                .forResult(PICK_IMAGE);
    }

    private void getPics(Intent data){
        final ArrayList<String> pathList =
                data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT_SELECTION);
        final boolean original =
                data.getBooleanExtra(PhotoPickerActivity.EXTRA_RESULT_ORIGINAL, false);

        selectedList = pathList;
        selectedList.add("add view");
        myDragAdapter.setNewData(selectedList);
        //originalView.setText("原图：" + original);
    }

    private void getAddress(Intent data){
        AddressBean addressBean = data.getParcelableExtra("address");
        if (addressBean != null) {
            // TODO: 2017/4/20 do something
            Utils.MakeTost(false, addressBean.getAreaDetile());
            mCustomAddressChoose.setText(addressBean.getAreaDetile());
        }
    }

    @Override
    public void onStartTabSelected(String title, int index) {
        //do nothing
    }

    @Override
    public void onEndTabSelected(String title, int index) {
        ChooseType = index;
    }

    void priceChanged(boolean onlyNum){
        int single = priceMap(1,deflautPrice,ObjectSize,ObjectMaterial);

        if(!onlyNum)
            mCustomSinglePrice.setText(String.valueOf(single));

        mCustomTotalPrice.setText(String.valueOf(ObjectNum*single));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if(R.id.scraf1 == checkedId)
            ObjectMaterial = 0;
        else
            ObjectMaterial = 1;
        priceChanged(false);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(ObjectSize != position) {
            ObjectSize = position;
            priceChanged(false);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

}
class MyDragAdapter extends BaseItemDraggableAdapter<String, DragPicViewHolder> {
    public MyDragAdapter(List<String> data) {
        super(data);
    }

    @Override
    protected int getDefItemViewType(int position) {
        if (position == getData().size() - 1)
            return R.layout.recyclerview_item_addone;
        return super.getDefItemViewType(position);
    }

    @Override
    protected DragPicViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        if (viewType == R.layout.recyclerview_item_addone) {
            return new FakeViewHolder(getItemView(viewType, parent));
        }
        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    protected void convert(DragPicViewHolder helper, String item) {
        if (item.equals("add view")) {
            return;
        }
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.custom_item_pic);
        Utils.load("file://" + item, simpleDraweeView, Constant.screenwithpx / 4 - 100, Constant.screenwithpx / 4 - 100);
        //simpleDraweeView.setLayoutParams(new GridLayoutManager.LayoutParams(AppManager.screenwithpx/4-100,AppManager.screenwithpx/4-100));
    }

    MyDragAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    private class FakeViewHolder extends DragPicViewHolder {
        FakeViewHolder(View view) {
            super(view);
            addOnClickListener(R.id.custom_item_add);
        }
    }
}

