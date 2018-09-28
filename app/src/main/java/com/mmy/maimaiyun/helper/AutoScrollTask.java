package com.mmy.maimaiyun.helper;


import android.app.Activity;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.customview.InnerViewPager;
import com.mmy.maimaiyun.data.bean.BannerBean;
import com.mmy.maimaiyun.model.main.adapter.BannerAdapter;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2016/1/14 10:59
 * @描述 广告轮播任务
 */
public class AutoScrollTask implements Runnable {
    private final BannerAdapter  mBannerAdapter;
    private       Activity       mContext;
    private       InnerViewPager mInnerViewPager;
    private       LinearLayout   mPoints;
    private       Handler        mHandler;
    private       boolean        mIsStart;

    public AutoScrollTask(InnerViewPager innerViewPager, LinearLayout points, Handler handler, Activity context) {
        mInnerViewPager = innerViewPager;
        mPoints = points;
        mHandler = handler;
        mBannerAdapter = new BannerAdapter(context);
        mContext = context;
        innerViewPager.setAdapter(mBannerAdapter);
        innerViewPager.setAutoScrollTask(this);
    }

    public void setData(List<BannerBean> banners){
        mBannerAdapter.setData(banners);
        initViewPager(banners);
    }

    private void initViewPager(final List<BannerBean> banners) {
        int index = Integer.MAX_VALUE / 2;
        int diff = index % banners.size();
        mInnerViewPager.setCurrentItem(index - diff);
        mPoints.removeAllViews();
        // mContainerIndicator添加内容
        for (int i = 0; i < banners.size(); i++) {
            ImageView ivIndicatorView = new ImageView(mContext);
            ivIndicatorView.setImageResource(R.drawable.circle_white);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(UIUtil.dp2px(mContext, 8), UIUtil.dp2px
                    (mContext, 8));// 8px
            params.leftMargin = UIUtil.dp2px(mContext, 10);
            params.bottomMargin = UIUtil.dp2px(mContext, 10);
            ivIndicatorView.setLayoutParams(params);
            mPoints.addView(ivIndicatorView);
            // 默认选中第一个
            if (i == 0) {
                ivIndicatorView.setImageResource(R.drawable.circle_blue);
            }
        }
        ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                position = position % banners.size();
                // 1.还原所有的效果
                for (int i = 0; i < banners.size(); i++) {
                    ImageView iv = (ImageView) mPoints.getChildAt(i);
                    iv.setImageResource(R.drawable.circle_white);
                }
                // 2.修改你想选中view的效果
                ImageView curImageView = (ImageView) mPoints.getChildAt(position);
                curImageView.setImageResource(R.drawable.circle_blue);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        };
        // 设置点的选中效果
        mInnerViewPager.setOnPageChangeListener(mOnPageChangeListener);
    }


    // 开始轮播
    public void start() {
//        Log.d("AutoScrollTask", "start");
        mIsStart = true;
        stop();
        mHandler.postDelayed(this, 3000);
    }

    // 停止轮播
    public void stop() {
//        Log.d("AutoScrollTask", "stop");
        if (!mIsStart)
            return;
        mIsStart = false;
        mHandler.removeCallbacks(this);
    }

    public boolean isStart(){
        return mIsStart;
    }

    @Override
    public void run() {
        int index = mInnerViewPager.getCurrentItem();
        index++;
//        Log.d("AutoScrollTask", "index:" + index);
        mInnerViewPager.setCurrentItem(index);
        start();
    }
}
