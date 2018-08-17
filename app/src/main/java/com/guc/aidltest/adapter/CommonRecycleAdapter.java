package com.guc.aidltest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public abstract class CommonRecycleAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {
    protected List<T> dataList;
    protected int layoutId;
    protected Context mContext;
    private LayoutInflater layoutInflater;

    public CommonRecycleAdapter(Context context, List<T> dataList, int layoutId) {
        this.mContext = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.dataList = dataList;
        this.layoutId = layoutId;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = layoutInflater.inflate(layoutId, parent, false);

        return new CommonViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        bindData(holder, dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    public void update(List<T> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void addList(List<T> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clear() {
        this.dataList.clear();
        notifyDataSetChanged();
    }

    public boolean hasData() {
        return dataList != null && dataList.size() > 0;
    }

    public abstract void bindData(CommonViewHolder holder, T data);
}
