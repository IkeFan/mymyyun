package com.mmy.maimaiyun.model.splash.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/12/8 0008 11:38
 * @描述 TODO
 */

public class GuideAdapter extends PagerAdapter {
    private List<ImageView> mImageViews;

    public GuideAdapter(List<ImageView> mImageViews) {
        this.mImageViews = mImageViews;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // ImageView
        ImageView iv = mImageViews.get(position);
        container.addView(iv);
        return iv;
    }

    @Override
    public int getCount() {
        return mImageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
