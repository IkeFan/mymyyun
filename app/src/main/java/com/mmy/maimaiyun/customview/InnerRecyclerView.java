package com.mmy.maimaiyun.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * @创建者 lucas
 * @创建时间 2017/9/2 0002 11:30
 * @描述 TODO
 */

public class InnerRecyclerView extends RecyclerView {
    private int mDownY;
    private int mMoveY;

    public InnerRecyclerView(Context context) {
        super(context);
        initView();
    }

    public InnerRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
//        int screenHeight = ScreenUtils.getScreenHeight();
//        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,screenHeight));
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
                    getParent().requestDisallowInterceptTouchEvent(true);

        return super.dispatchTouchEvent(ev);
    }
}
