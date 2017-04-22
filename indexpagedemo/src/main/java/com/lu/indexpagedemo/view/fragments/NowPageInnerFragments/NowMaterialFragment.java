package com.lu.indexpagedemo.view.fragments.NowPageInnerFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.flexbox.FlexboxLayout;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.AppManager;
import com.lu.indexpagedemo.base.Tools.Constant;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.base.mvp.MvpBaseFragment;
import com.lu.indexpagedemo.bean.MaterialBean;
import com.lu.indexpagedemo.contract.NowMaterialContract;
import com.lu.indexpagedemo.presenter.NowMaterialPresenterImpl;
import com.lu.indexpagedemo.view.adapters.MyRecyclerView;
import com.lu.indexpagedemo.view.adapters.MySimpleAdapter;
import com.lu.indexpagedemo.view.simpleviews.fixtures.MessagesFixtures;
import com.stfalcon.chatkit.utils.FrescoAutoFit;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowMaterialFragment extends MvpBaseFragment<NowMaterialContract.View, NowMaterialPresenterImpl>
        implements NowMaterialContract.View, BaseQuickAdapter.OnItemChildClickListener {

    public final static String TAG = "NowMaterialFragment";
    private MyRecyclerView mNowMaterialRecyclerView;
    private PtrFrameLayout mNowMaterialRefreshLayout;
    private MaterialAdapter mMaterialAdapter;

    public NowMaterialFragment() {
        // Required empty public constructor
    }

    public static NowMaterialFragment newInstance() {
        return new NowMaterialFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            rootView = inflater.inflate(R.layout.fragment_now_material, container, false);
            initView(rootView);
        }
        return rootView;
    }

    private void initView(View rootView) {
        mNowMaterialRecyclerView = (MyRecyclerView) rootView.findViewById(R.id.now_material_recycler_view);
        mNowMaterialRefreshLayout = (PtrFrameLayout) rootView.findViewById(R.id.now_material_refresh_layout);
        mNowMaterialRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mNowMaterialRefreshLayout.setEnabled(false);

        mMaterialAdapter = new MaterialAdapter(R.layout.recyclerview_item_only_pics);
        mMaterialAdapter.bindToRecyclerView(mNowMaterialRecyclerView);

        mNowMaterialRecyclerView.setAdapter(mMaterialAdapter);
        mMaterialAdapter.setEmptyView(R.layout.recylcerview_emptyview);
        mMaterialAdapter.setOnItemChildClickListener(this);

        ArrayList<MaterialBean> materialBeanArrayList = new ArrayList<>();
        for (int i = 0;i<50;i++) {
            materialBeanArrayList.add(new MaterialBean(MessagesFixtures.getMaterialImage()));
        }
        mMaterialAdapter.setNewData(materialBeanArrayList);

    }

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    protected NowMaterialPresenterImpl createPresent() {
        return new NowMaterialPresenterImpl();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Utils.MakeTost(false,"点击了 "+ position);
    }
}

class MaterialAdapter extends MySimpleAdapter<MaterialBean, MaterialAdapter.MaterialViewHolder> {

    public MaterialAdapter(int layoutResId, List<MaterialBean> data) {
        super(layoutResId, data);
    }

    public MaterialAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(MaterialViewHolder helper, MaterialBean item) {
        FrescoAutoFit.setControllerListener((SimpleDraweeView)helper.getView(R.id.now_item_pic),item.getImageUrl(), (Constant.screenwithpx>>1));
    }

    class MaterialViewHolder extends BaseViewHolder {

        public MaterialViewHolder(View view) {
            super(view);
            SimpleDraweeView simpleDraweeView = getView(R.id.now_item_pic);
            ViewGroup.LayoutParams layoutParams= simpleDraweeView.getLayoutParams();
            layoutParams.width = Constant.screenwithpx>>1;
            simpleDraweeView.setLayoutParams(layoutParams);
            addOnClickListener(R.id.now_item_pic);
        }
    }
}
