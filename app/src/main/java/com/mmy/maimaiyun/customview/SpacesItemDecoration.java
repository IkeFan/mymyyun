package com.mmy.maimaiyun.customview;


import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @创建者 lucas
 * @创建时间 2017/8/22 0022 15:55
 * @描述 分割线
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space = -1;
    private int mTop;
    private int mBot;
    private int mLeft;
    private int mRight;


    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    public SpacesItemDecoration(int top, int bot, int left, int right) {
        mTop = top;
        mBot = bot;
        mLeft = left;
        mRight = right;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = space == -1 ? mLeft : space;
        outRect.right = space == -1 ? mRight : space;
        outRect.bottom = space == -1 ? mBot : space;

        // Add top margin only for the first item to avoid double space between items
        //        if (parent.getChildPosition(view) == 0)
        outRect.top = space == -1 ? mTop : space;
    }
}
