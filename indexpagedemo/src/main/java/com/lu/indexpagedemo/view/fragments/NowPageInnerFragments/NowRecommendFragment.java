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
import com.lu.indexpagedemo.base.Tools.Constant;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.base.mvp.MvpBaseFragment;
import com.lu.indexpagedemo.bean.ArticleBean;
import com.lu.indexpagedemo.contract.NowRecommendContract;
import com.lu.indexpagedemo.presenter.NowRecommendPresenterImpl;
import com.lu.indexpagedemo.view.adapters.MyRecyclerView;
import com.lu.indexpagedemo.view.adapters.MySimpleAdapter;
import com.lu.indexpagedemo.view.simpleviews.fixtures.MessagesFixtures;

import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowRecommendFragment extends MvpBaseFragment<NowRecommendContract.View, NowRecommendPresenterImpl> implements NowRecommendContract.View {

    public final static String TAG = "NowRecommendFragment";
    private MyRecyclerView mNowRecommendRecyclerView;
    private PtrFrameLayout mNowRecommendRefreshLayout;
    private NowRecommendAdapter mNowRecommendAdapter;

    public NowRecommendFragment() {
        // Required empty public constructor
    }

    public static NowRecommendFragment newInstance() {
        return new NowRecommendFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (savedInstanceState == null) {
            rootView = inflater.inflate(R.layout.fragment_now_recommed, container, false);
            initView(rootView);
        }
        return rootView;
    }

    @Override
    protected NowRecommendPresenterImpl createPresent() {
        return new NowRecommendPresenterImpl();
    }

    @Override
    public String getTAG() {
        return TAG;
    }

    private void initView(View rootView) {
        mNowRecommendRecyclerView = (MyRecyclerView) rootView.findViewById(R.id.now_recommend_recycler_view);
        mNowRecommendRefreshLayout = (PtrFrameLayout) rootView.findViewById(R.id.now_recommend_refresh_layout);
        mNowRecommendAdapter = new NowRecommendAdapter(R.layout.recyclerview_item_now_recommend);
        mNowRecommendRecyclerView.setLayoutManager(new LinearLayoutManager(AppManager.app()));
        mNowRecommendAdapter.bindToRecyclerView(mNowRecommendRecyclerView);
        mNowRecommendRecyclerView.setAdapter(mNowRecommendAdapter);
        mNowRecommendRefreshLayout.setEnabled(false);
        mNowRecommendAdapter.setEmptyView(R.layout.recylcerview_emptyview);
        for(int i = 0; i<15;i++){
            mNowRecommendAdapter.addData(ArticleBean.makeArticleBean());
        }
    }
}

class NowRecommendAdapter extends MySimpleAdapter<ArticleBean, NowRecommendAdapter.NowRecommendViewHolder> {

    int width;
    int height;

    public NowRecommendAdapter(int layoutResId, List<ArticleBean> data) {
        super(layoutResId, data);
    }

    public NowRecommendAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(NowRecommendViewHolder helper, ArticleBean item) {
        helper.setText(R.id.now_recommend_article_title, item.getTitle());
        helper.setText(R.id.now_recommend_user_name, item.getUser().getName());
        Utils.load(item.getImageUrl(), (SimpleDraweeView) helper.getView(R.id.now_recommend_article_image), width, height);
        Utils.load(item.getUser().getAvatar(), (SimpleDraweeView) helper.getView(R.id.now_recommend_avatar), Utils.dip2px(36), Utils.dip2px(36));
    }

    class NowRecommendViewHolder extends BaseViewHolder {

        public NowRecommendViewHolder(View view) {
            super(view);
            width = Utils.dip2px(Constant.screenwithdp - 72);
            height = Utils.dip2px(140);
        }
    }
}