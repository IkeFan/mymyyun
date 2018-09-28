package com.mmy.maimaiyun.model.personal.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;


/**
 * @创建者 lucas
 * @创建时间 2017/9/15 0015 15:16
 * @描述 TODO
 */

public class DistributionAdapter extends PagerAdapter {

    private Context  mContext;
    private String[] mTitles;

    public DistributionAdapter(Context context, String[] titles) {
        mContext = context;
        mTitles = titles;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_distribution, null);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
