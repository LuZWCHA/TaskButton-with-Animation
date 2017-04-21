package com.lu.indexpagedemo.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.lu.indexpagedemo.bean.base.IBaseBean;

import java.util.HashSet;

/**
 * Created by 陆正威 on 2017/4/4.
 */

public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private HashSet<String> failset;
    protected OnItemClickListener onItemClickListener;
    private int headersize ;

    public interface OnItemClickListener{
        void onItemClick(View itemView,int pos,IBaseBean bean);
    }

    BaseRecyclerViewAdapter() {
        failset = new HashSet<>();
        headersize = 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        if(onItemClickListener != listener)
            onItemClickListener = listener;
    }

    public boolean isContain(String uri){
        return failset != null && (failset.contains(uri));
    }

    public void addFailUrl(String url){
        if(failset != null && !isContain(url)){
            failset.add(url);
        }
    }

    public boolean removeUrl(String url){
        if(isContain(url)) {
            failset.remove(url);
            return true;
        }
        return false;
    }

    public void clearAll()
    {
        if(failset != null)
            failset.clear();
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(new NotifyObserver(observer,headersize));
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private class NotifyObserver extends RecyclerView.AdapterDataObserver {

        RecyclerView.AdapterDataObserver mDataObserver;

        int mHeaderSize = 0;

        public NotifyObserver(RecyclerView.AdapterDataObserver dataObserver,int headerSize) {
            mDataObserver = dataObserver;
            mHeaderSize = headerSize;
        }
        @Override
        public void onChanged() {
            mDataObserver.onChanged();
        }
        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            mDataObserver.onItemRangeChanged(positionStart + mHeaderSize , itemCount);
        }
        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            mDataObserver.onItemRangeChanged(positionStart + mHeaderSize , itemCount, payload);
        }
        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            mDataObserver.onItemRangeInserted(positionStart + mHeaderSize , itemCount);
        }
        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            mDataObserver.onItemRangeMoved(fromPosition, toPosition, itemCount);
        }
        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            mDataObserver.onItemRangeRemoved(positionStart + mHeaderSize , itemCount);
        }
    }
}
