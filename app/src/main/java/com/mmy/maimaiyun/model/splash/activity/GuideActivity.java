package com.mmy.maimaiyun.model.splash.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.model.login.activity.LoginActivity;
import com.mmy.maimaiyun.model.splash.adapter.GuideAdapter;
import com.mmy.maimaiyun.utils.Constants;
import com.mmy.maimaiyun.utils.SPUtils;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 引导页
 */
public class GuideActivity extends BaseActivity {
    private int mDX;
    private int[]           bgids       = new int[]{R.mipmap.start_1, R.mipmap.start_2, R.mipmap.start_3, R.mipmap.start_4};
    private List<ImageView> mImageViews = new ArrayList<>();
    @Bind(R.id.vp_guid_viewpager)
    ViewPager    mViewpager;
    @Bind(R.id.ll_guid_graypoint)
    LinearLayout mContainer;
    @Bind(R.id.v_guide_redpoint)
    View         red_point;
    @Bind(R.id.et_guid_exp)
    TextView     bt_exp;

    @Override
    protected void initDagger(AppComponent appComponent) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        for (int bid : bgids) {
            ImageView iv = new ImageView(this);
            iv.setBackgroundResource(bid);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            //图片添加到集合中
            mImageViews.add(iv);
            //添加白色点
            View v_graypoint = new View(this);
            v_graypoint.setBackgroundResource(R.drawable.circle_white);
            int dp10 = UIUtil.dp2px(this, 10);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dp10, dp10);
            v_graypoint.setLayoutParams(lp);
            if (bid != bgids[0]) {
                //不是第一个点，添加左边距10
                lp.leftMargin =dp10;
            }
            mContainer.addView(v_graypoint);
        }
        //在布局完成时
        red_point.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mDX = mContainer.getChildAt(1).getLeft() - mContainer.getChildAt(0).getLeft();
                red_point.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        mViewpager.setAdapter(new GuideAdapter(mImageViews));
    }

    @Override
    public void initEvent() {
        super.initEvent();
        bt_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //记录已经进入过设置向导界面
                SPUtils.getInstance().put(Constants.IS_NO_SHOW_LAUNCHER,true);
                // 跳转到主界面
                Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();//关闭设置向导界面
            }
        });
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                float left = mDX * (position + positionOffset);

                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) red_point.getLayoutParams();
                params.leftMargin = Math.round(left);
                //设置红点布局参数
                red_point.setLayoutParams(params);

            }

            @Override
            public void onPageSelected(int position) {
                // 如果是第二个页面 显示button
                if (position == mImageViews.size() - 1) {
                    bt_exp.setVisibility(View.VISIBLE);
                } else {
                    bt_exp.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_guide;
    }
}
