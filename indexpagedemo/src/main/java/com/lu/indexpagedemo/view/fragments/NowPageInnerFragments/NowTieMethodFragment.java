package com.lu.indexpagedemo.view.fragments.NowPageInnerFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.AppManager;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.base.mvp.MvpBaseFragment;
import com.lu.indexpagedemo.bean.ArticleBean;
import com.lu.indexpagedemo.contract.NowTieMethodContract;
import com.lu.indexpagedemo.presenter.NowTieMethodPresenterImpl;
import com.lu.indexpagedemo.view.adapters.MyRecyclerView;
import com.lu.indexpagedemo.view.adapters.MySimpleAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NowTieMethodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NowTieMethodFragment extends MvpBaseFragment<NowTieMethodContract.View, NowTieMethodPresenterImpl> implements NowTieMethodContract.View {

    public final static String TAG = "NowTieMethodFragment";
    private MyRecyclerView mNowTieMethodRecyclerView;
    private NowTieMethodAdapter mNowTieMethodAdapter;

    public NowTieMethodFragment() {
        // Required empty public constructor
    }

    public static NowTieMethodFragment newInstance() {
        return new NowTieMethodFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNowTieMethodAdapter = new NowTieMethodAdapter(R.layout.recyclerview_item_now_ltrp);

    }

    @Override
    protected NowTieMethodPresenterImpl createPresent() {
        return new NowTieMethodPresenterImpl();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_now_tiemethod, container, false);
            initView(rootView);
        }
        return rootView;
    }

    @Override
    public String getTAG() {
        return TAG;
    }

    private void initView(View rootView) {
        mNowTieMethodRecyclerView = (MyRecyclerView) rootView.findViewById(R.id.now_tie_method_recycler_view);
        mNowTieMethodRecyclerView.setLayoutManager(new LinearLayoutManager(AppManager.app()));
        mNowTieMethodAdapter.bindToRecyclerView(mNowTieMethodRecyclerView);
        mNowTieMethodRecyclerView.setAdapter(mNowTieMethodAdapter);
        mNowTieMethodAdapter.setEmptyView(R.layout.recylcerview_emptyview);
        for(int i = 0;i<20;i++){
            mNowTieMethodAdapter.addData(ArticleBean.makeArticleBean());
        }
    }
}

class NowTieMethodAdapter extends MySimpleAdapter<ArticleBean,NowTieMethodAdapter.NowTieMethodViewHolder>{


    public NowTieMethodAdapter(int layoutResId, List<ArticleBean> data) {
        super(layoutResId, data);
    }

    public NowTieMethodAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(NowTieMethodViewHolder helper, ArticleBean item) {
        helper.setText(R.id.now_tie_method_chat_num,String.valueOf(item.getChattingNum()));
        helper.setText(R.id.now_tie_method_like_num,String.valueOf(item.getBeenlikedNum()));
        helper.setText(R.id.now_tie_method_title,item.getTitle());
        helper.setText(R.id.now_tie_method_name,item.getUser().getName());

        Utils.load(item.getUser().getAvatar(),(SimpleDraweeView)helper.getView(R.id.now_tie_method_avatar),Utils.dip2px(16),Utils.dip2px(16));
        Utils.load(item.getImageUrl(),(SimpleDraweeView)helper.getView(R.id.now_tie_method_image),Utils.dip2px(128),Utils.dip2px(120));
    }

    class NowTieMethodViewHolder extends BaseViewHolder{

        public NowTieMethodViewHolder(View view) {
            super(view);
            addOnClickListener(getConvertView().getId());
        }
    }
}