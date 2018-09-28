package com.mmy.maimaiyun.customview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.mmy.maimaiyun.helper.AutoScrollTask;


/**
 * lxw
 */
public class InnerViewPager extends ViewPager {

    private int            mDownX;
    private int            mDownY;
    private int            mMoveX;
    private int            mMoveY;
    private AutoScrollTask mAutoScrollTask;

    public InnerViewPager(Context context) {
        super(context);
    }

    public InnerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAutoScrollTask(AutoScrollTask autoScrollTask) {
        mAutoScrollTask = autoScrollTask;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mAutoScrollTask!=null)
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = (int) ev.getRawX();
                mDownY = (int) ev.getRawY();
                mAutoScrollTask.stop();
                break;
            case MotionEvent.ACTION_MOVE:
                mMoveX = (int) ev.getRawX();
                mMoveY = (int) ev.getRawY();
                int diffX = mMoveX - mDownX;
                int diffY = mMoveY - mDownY;
                if (Math.abs(diffX) > Math.abs(diffY))
                    mAutoScrollTask.stop();
                break;
            case MotionEvent.ACTION_UP:
                mAutoScrollTask.start();
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

}
