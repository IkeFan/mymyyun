package com.mmy.maimaiyun.helper;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


/**
 * @创建者 lucas
 * @创建时间 2017/10/26 0026 15:29
 * @描述 上拉加载更多
 */

public class PushRefreshScrollHelper extends RecyclerView.OnScrollListener {

    private LinearLayoutManager mLayoutManager;

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    private RecyclerView mRecyclerView;
    //一屏显示的item数量
    private int          mVisibleChildCount;
    private int          mItemCount;
    boolean isLoading;
    private int                    mLastVisibleItemPosition;
    private OnPushLoadMoreListener mListener;

    public PushRefreshScrollHelper(RecyclerView recyclerView) {
        mLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        mRecyclerView = recyclerView;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mVisibleChildCount = recyclerView.getChildCount();
        mItemCount = mLayoutManager.getItemCount();
        mLastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
        Log.d("PullRefreshScrollListen", "mLastVisibleItemPosition:" + mLastVisibleItemPosition);
        Log.d("PullRefreshScrollListen", "mItemCount:" + mItemCount);
        Log.d("PullRefreshScrollListen", "mVisibleChildCount:" + mVisibleChildCount);
        //判断是否能否继续向下滑动
        if (!isLoading && mVisibleChildCount < mItemCount && !mRecyclerView.canScrollVertically(1)) {
            //开始加载
            isLoading = true;
            if (mListener != null) {
                mListener.onLoadingMore();
            }
        }

    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setOnPushLoadMoreListener(OnPushLoadMoreListener listener) {
        mListener = listener;
    }

    public interface OnPushLoadMoreListener {
        void onLoadingMore();
    }

}
