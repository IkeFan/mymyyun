package com.mmy.maimaiyun.helper;

import android.os.Handler;
import android.support.annotation.Nullable;

/**
 * @创建者 lucas
 * @创建时间 2017/7/7 0007 9:17
 * @描述 倒计时任务
 */

public class CountDownTask implements Runnable {

    private Handler                 mHandler;
    private int                     count;
    private OnCountDownTaskListener mListener;
    private static final int SPACE = 1000;

    public CountDownTask(@Nullable Handler handler, @Nullable OnCountDownTaskListener listener) {
        mHandler = handler;
        mListener = listener;
    }

    @Override
    public void run() {
        if (count-- <= 0) {
            mListener.complete();
            return;
        }
        mListener.progress(count);
        mHandler.postDelayed(this, SPACE);
    }

    public void start() {
        count = 60;
        mHandler.post(this);
    }

    public void stop() {
        mHandler.removeCallbacks(this);
    }

    public interface OnCountDownTaskListener {
        void progress(int progress);
        void complete();
    }
}
