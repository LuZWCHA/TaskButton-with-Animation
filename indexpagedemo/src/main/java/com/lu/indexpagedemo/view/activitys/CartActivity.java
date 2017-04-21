package com.lu.indexpagedemo.view.activitys;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyco.animation.FadeEnter.FadeEnter;
import com.flyco.animation.FadeExit.FadeExit;
import com.flyco.animation.SlideEnter.SlideBottomEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.utils.StatusBarUtils;
import com.flyco.dialog.widget.popup.BubblePopup;
import com.flyco.dialog.widget.popup.base.BaseBubblePopup;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.AppManager;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.base.mvp.MvpBaseActivity;
import com.lu.indexpagedemo.bean.CartItemBean;
import com.lu.indexpagedemo.bean.WorkBean;
import com.lu.indexpagedemo.bean.base.customview.GoodsView;
import com.lu.indexpagedemo.contract.CartContract;
import com.lu.indexpagedemo.presenter.CartPresenterImpl;
import com.lu.indexpagedemo.view.adapters.MyRecyclerView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.zip.Inflater;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static in.srain.cube.views.ptr.util.PtrLocalDisplay.dp2px;

public class CartActivity extends MvpBaseActivity<CartContract.View, CartPresenterImpl> implements CartContract.View, View.OnClickListener,GoodsView.OnOutOfRangeListener, GoodsView.OnChangeListener {

    private Toolbar mCartToolbar;
    private MyRecyclerView mCartItemsRecyclerview;
    private CartAdapter mCartAdapter;
    private BubblePopup mCartBubblePopup;
    /**
     * 100
     */
    private TextView mCartSumPriceTextview;
    private AppCompatButton mCartCommitButton;

    private int choosingItemPosition = -1;
    private int sumPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        if (savedInstanceState == null) {

            List<CartItemBean> list = new ArrayList<>();
            int i = 20;
            while(i>0){
                WorkBean workBean = new WorkBean();
                workBean.setTitle(i+"hi");
                workBean.setPrice(((int)(Math.random()*100)*100));
                CartItemBean cartItemBean = new CartItemBean((long) i,workBean,i);
                list.add(cartItemBean);
                i--;
            }
            mCartAdapter = new CartAdapter(R.layout.recyclerview_item_cart,list);
            initView();
            getSum(list);
        }
    }

    @Override
    protected CartPresenterImpl createPresent() {
        return new CartPresenterImpl();
    }


    @Override
    public void updateList(List<WorkBean> workBeanList) {

    }

    @Override
    public void updateSum() {

    }

    private void getSum(List<CartItemBean> cartItemBeen){
        Observable.fromIterable(cartItemBeen)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<CartItemBean, Integer>() {
                    @Override
                    public Integer apply(CartItemBean cartItemBean) throws Exception {
                        return cartItemBean.getWorkBean().getPrice() * cartItemBean.getNum();
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        sumPrice = 0;
                    }

                    @Override
                    public void onNext(Integer value) {
                        sumPrice += value;
                    }

                    @Override
                    public void onError(Throwable e) {
                        mCartSumPriceTextview.setText(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        //mCartSumPriceTextview.setText();
                        mCartSumPriceTextview.setText(String.valueOf(sumPrice*0.01f));
                    }
                });
    }

    private void initView() {
        mCartToolbar = (Toolbar) findViewById(R.id.cart_toolbar);
        mCartToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mCartItemsRecyclerview = (MyRecyclerView) findViewById(R.id.cart_items_recyclerview);
        mCartSumPriceTextview = (TextView) findViewById(R.id.cart_sum_price_textview);
        mCartCommitButton = (AppCompatButton) findViewById(R.id.cart_commit_button);
        mCartCommitButton.setOnClickListener(this);

        View inflate = View.inflate(this, R.layout.bubblepopup_choose, null);
        AppCompatButton cancel_button = (AppCompatButton) inflate.findViewById(R.id.cancel_button);
        cancel_button.setOnClickListener(this);
        AppCompatButton confirm_button= (AppCompatButton) inflate.findViewById(R.id.confirm_button);
        confirm_button.setOnClickListener(this);
        mCartBubblePopup = new BubblePopup(this, inflate)
                .cornerRadius(8)
                .bubbleColor(getResources().getColor(R.color.colorPrimary))
                .showAnim(new FadeEnter())
                .dismissAnim(new FadeExit());

        mCartItemsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mCartAdapter.bindToRecyclerView(mCartItemsRecyclerview);
        mCartItemsRecyclerview.setAdapter(mCartAdapter);
        mCartAdapter.setEmptyView(R.layout.recylcerview_emptyview);
        mCartAdapter.setOnOutOfRangeListener(this);
        mCartAdapter.setOnChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cart_commit_button:
                break;
            case R.id.confirm_button:
                removeCartItem(choosingItemPosition);
                break;
            case R.id.cancel_button:
                mCartBubblePopup.dismiss();
                break;
        }
    }

    @Override
    public void onOutOfRange(View view, boolean isTooSmall) {
        if(isTooSmall){
            if(null != mCartBubblePopup){
                mCartBubblePopup.anchorView(view);
                int[] l = {0,0};
                view.getLocationOnScreen(l);

                choosingItemPosition = (int) view.getTag();
                mCartBubblePopup.show();
            }
        }
    }

    private void removeCartItem(int positon){
        if(choosingItemPosition > -1) {
            if (choosingItemPosition < mCartAdapter.getData().size()) {
                sumPrice -= mCartAdapter.getData().get(positon).getWorkBean().getPrice();
                mCartSumPriceTextview.setText(String.valueOf(sumPrice *0.01f));
                mCartBubblePopup.superDismiss();
                mCartAdapter.remove(positon);
            }
        }
    }

    @Override
    public void onChangeListener(View view, boolean isIncrease) {
        if(mCartAdapter == null || mCartAdapter.getData() == null)
            return;
        CartItemBean cartItemBean = mCartAdapter.getData().get((int) view.getTag());
        sumPrice += (isIncrease?cartItemBean.getWorkBean().getPrice(): - cartItemBean.getWorkBean().getPrice());
        cartItemBean.setNum(cartItemBean.getNum()+(isIncrease?1:-1));
        mCartSumPriceTextview.setText(String.valueOf(sumPrice*0.01f));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}

class CartAdapter extends BaseQuickAdapter<CartItemBean,CartAdapter.CartItemViewHolder>{

    private GoodsView.OnOutOfRangeListener onOutOfRangeListener;
    private GoodsView.OnChangeListener onChangeListener;

    public CartAdapter(int layoutResId, List<CartItemBean> data) {
        super(layoutResId, data);
    }

    public CartAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(CartItemViewHolder helper, CartItemBean item) {
        helper.setText(R.id.cart_item_work_content,item.getWorkBean().getTitle());
        helper.setText(R.id.cart_item_price,String.valueOf(item.getWorkBean().getPrice()*0.01f));
        GoodsView goodsView = helper.getView(R.id.cart_item_num_button);
        goodsView.setTag(helper.getAdapterPosition());
        if(item.getNum()>= 1)
            goodsView.setNum(item.getNum());
        else {
            goodsView.setNum(1);
            onOutOfRangeListener.onOutOfRange(goodsView,true);
        }
    }

    void setOnOutOfRangeListener(GoodsView.OnOutOfRangeListener onOutOfRangeListener) {
        this.onOutOfRangeListener = onOutOfRangeListener;
    }

    void setOnChangeListener(GoodsView.OnChangeListener onChangeListener) {
        this.onChangeListener = onChangeListener;
    }

    class CartItemViewHolder extends BaseViewHolder{

        public CartItemViewHolder(View view) {
            super(view);
            GoodsView goodsView = getView(R.id.cart_item_num_button);
            goodsView.setMin(1);
            goodsView.setOnOutOfRangeListener(onOutOfRangeListener);
            goodsView.setOnChangeListener(onChangeListener);
            addOnClickListener(R.id.cart_item_rootview);
        }
    }
}