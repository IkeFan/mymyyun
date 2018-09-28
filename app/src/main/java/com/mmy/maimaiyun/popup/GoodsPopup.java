package com.mmy.maimaiyun.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.popup.adapter.GoodsPopupAdapter;

import java.util.ArrayList;

/**
 * @创建者 lucas
 * @创建时间 2017/9/1 0001 11:29
 * @描述 商品分类弹窗
 */

public class GoodsPopup extends PopupWindow implements BaseRecyclerViewAdapter.OnItemClickListener {

    private GoodsPopupAdapter            mAdapter;
    private int                          mTag;
    private OnMenuPopupItemClickListener mListener;

    public GoodsPopup(Context context) {
        super(context);
        initView(context);
    }

    public GoodsPopup(Context context,int tag) {
        super(context);
        mTag = tag;
        initView(context);
    }

    public int getTag(){
        return mTag;
    }


    private void initView(Context context) {
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new GoodsPopupAdapter(context);
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setBackgroundResource(R.color.white);
        setContentView(recyclerView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new ColorDrawable(0x30000000));
        setFocusable(true);
        setOutsideTouchable(true);
    }

    public void setData(ArrayList<String> data){
        mAdapter.setData(data);
    }

    public void setItemClickListener(OnMenuPopupItemClickListener listener){
        mListener = listener;
    }

    @Override
    public void onItemClick(View view, int position) {
        if (mListener != null) {
            mListener.onMenuPopupClick(view,position,mTag);
        }
    }

    public interface OnMenuPopupItemClickListener{
        void onMenuPopupClick(View view, int position, int tag);
    }

}
