package com.mmy.maimaiyun.model.personal.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.helper.VDataMakeHelper;
import com.mmy.maimaiyun.utils.UIUtil;


/**
 * @创建者 lucas
 * @创建时间 2017/9/15 0015 15:16
 * @描述 TODO
 */

public class SubDistributionPagerAdapter extends PagerAdapter {

    private Context  mContext;
    private String[] mTitles;

    public SubDistributionPagerAdapter(Context context, String[] titles) {
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
        RecyclerView recyclerView = new RecyclerView(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        recyclerView.setLayoutParams(params);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        int px = UIUtil.dp2px(mContext, 7);
        recyclerView.addItemDecoration(new SpacesItemDecoration(px,0,0,0));
        recyclerView.setAdapter(new SubDistributionAdapter(mContext, VDataMakeHelper.createStrList(10)));
        container.addView(recyclerView);
        return recyclerView;
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
