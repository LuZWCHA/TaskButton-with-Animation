package com.lu.indexpagedemo.view.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.AppManager;
import com.lu.indexpagedemo.base.Tools.Constant;
import com.lu.indexpagedemo.base.Tools.QSdkApp;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.base.mvp.ChatBaseActivity.Model.User;
import com.lu.indexpagedemo.base.mvp.MvpBaseActivity;
import com.lu.indexpagedemo.bean.WorkBean;
import com.lu.indexpagedemo.contract.WorkDetailsContract;
import com.lu.indexpagedemo.presenter.WorkDetailsPresenterImpl;
import com.lu.indexpagedemo.view.simpleviews.DesignerDetailsActivity;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class WorkDetailsActivity extends MvpBaseActivity<WorkDetailsContract.View, WorkDetailsPresenterImpl> implements WorkDetailsContract.View, View.OnClickListener {

    public final static String TAG = "WorkDetailsActivity";
    private static final int DEFULT_DURTION = 500;//mils
    private long showId;
    private AVLoadingIndicatorView mLoadView;
    private RollPagerView mWorkDetailRollPagerView;
    private SimpleDraweeView mWorkDetailDesigner;
    private LinearLayout mWorkDetailGotoVr;
    private NestedScrollView mWorkDetailRootview;
    private SimpleDraweeView mWorkDetailLikeButton;
    private Toolbar mWorkDetailToolbar;
    private AppCompatButton mWorkDetailGiveIssues;
    private RollPageViewAdapter mRollPageViewAdapter;
    /**
     * 加入购物车
     */
    private AppCompatButton mWorkDetailAddIntoCart;
    /**
     * ALEX
     */
    private TextView mWorkDetailDesignerName;
    private TextView mWorkDetailTitle;
    private WebView mWorkDetailContent;
    /**
     * 2111
     */
    private TextView mWorkDetailPrice;
    /**
     * 条评论
     */
    private TextView mWorkDetailChatNum;

    private User mUser;
    private WorkBean mWorkBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showId = getIntent().getLongExtra("id", -1);
        //View rootView = getLayoutInflater().inflate(R.layout.activity_work_details);
        setContentView(R.layout.activity_work_details);
        initView();
        initData();
    }

    private void initData() {
        if (showId != -1)
            mPresenter.getWorkBean(showId);
    }


    @Override
    protected WorkDetailsPresenterImpl createPresent() {
        return new WorkDetailsPresenterImpl();
    }

    @Override
    public String getTAG() {
        return TAG;
    }

    private void initView() {
        mLoadView = (AVLoadingIndicatorView) findViewById(R.id.load_view);
        mWorkDetailRollPagerView = (RollPagerView) findViewById(R.id.work_detail_roll_pager_view);
        mWorkDetailDesigner = (SimpleDraweeView) findViewById(R.id.work_detail_designer);
        mWorkDetailDesigner.setOnClickListener(this);
        mWorkDetailGotoVr = (LinearLayout) findViewById(R.id.work_detail_goto_vr);
        mWorkDetailGotoVr.setOnClickListener(this);
        mWorkDetailRootview = (NestedScrollView) findViewById(R.id.work_detail_rootview);
        mWorkDetailLikeButton = (SimpleDraweeView) findViewById(R.id.work_detail_like_button);
        mWorkDetailToolbar = (Toolbar) findViewById(R.id.work_detail_toolbar);
        mWorkDetailGiveIssues = (AppCompatButton) findViewById(R.id.work_detail_give_issues);
        mWorkDetailGiveIssues.setOnClickListener(this);
        mWorkDetailAddIntoCart = (AppCompatButton) findViewById(R.id.work_detail_add_into_cart);
        mWorkDetailAddIntoCart.setOnClickListener(this);

        //showLoadView(true);
        mWorkDetailDesignerName = (TextView) findViewById(R.id.work_detail_designer_name);
        mWorkDetailTitle = (TextView) findViewById(R.id.work_detail_title);
        mWorkDetailContent = (WebView) findViewById(R.id.work_detail_content);
        mWorkDetailLikeButton.setOnClickListener(this);
        mWorkDetailPrice = (TextView) findViewById(R.id.work_detail_price);
        mWorkDetailChatNum = (TextView) findViewById(R.id.work_detail_chat_num);

        mWorkDetailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRollPageViewAdapter = new RollPageViewAdapter(mWorkDetailRollPagerView);
        mWorkDetailRollPagerView.setAnimationDurtion(DEFULT_DURTION);
        mWorkDetailRollPagerView.setAdapter(mRollPageViewAdapter);
    }

    private void showLoadView(boolean show) {
        if (show) {
            mLoadView.smoothToShow();
            mWorkDetailRootview.setVisibility(View.GONE);
        } else {
            mLoadView.hide();
            mWorkDetailRootview.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.work_detail_designer:
                if(mUser == null || mWorkBean == null) break;
                startActivity(new Intent(this, DesignerDetailsActivity.class));
                EventBus.getDefault().postSticky(mUser);
                break;
            case R.id.work_detail_goto_vr:
                break;
            case R.id.work_detail_give_issues:
                break;
            case R.id.work_detail_add_into_cart:
                break;
            case R.id.work_detail_like_button:
                break;
        }
    }

    @Override
    public void showLoading() {
        showLoadView(true);
    }

    @Override
    public void hideLoading() {
        showLoadView(false);
    }

    @Override
    public void updateDetails(WorkBean workBean) {
        mWorkBean = workBean;
        ArrayList<String> images = new ArrayList<>();
        images.add(Utils.AddBaseUrl(workBean.getImage()));
        mRollPageViewAdapter.updateImages(images);
        mWorkDetailTitle.setText(workBean.getTitle());
        mWorkDetailPrice.setText(String.valueOf(workBean.getPrice()));
        QSdkApp.webViewInitiz(mWorkDetailContent,new WebChromeClient(),workBean.getContent()+"<p style=\"color:gray;font-size:0.75em;text-align:right\">"+ workBean.getTime()+"</p>",Constant.screenwithdp);
    }

    @Override
    public void updateUser(User user) {
        mUser = user;
        mWorkDetailDesignerName.setText(user.getNickname());
        Utils.load(Utils.AddBaseUrl(user.getAvatarPath()),mWorkDetailDesigner,Utils.dip2px(48),Utils.dip2px(48));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWorkDetailContent.destroy();
    }
}

class RollPageViewAdapter extends LoopPagerAdapter {

    private List<String> images;

    RollPageViewAdapter(RollPagerView viewPager) {
        super(viewPager);
        images = new ArrayList<>();
    }

    void updateImages(ArrayList<String> images) {
        this.images = images;
        notifyDataSetChanged();
    }

    @Override
    public View getView(ViewGroup container, int position) {
        SimpleDraweeView view = new SimpleDraweeView(AppManager.app());
        Utils.load(images.get(position), view, Constant.screenwithpx, Utils.dip2px(280));
        return view;
    }

    @Override
    public int getRealCount() {
        return images.size();
    }
}