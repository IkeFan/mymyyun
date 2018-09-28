package com.mmy.maimaiyun.model.personal.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.CouponListBean;
import com.mmy.maimaiyun.utils.UIUtil;


/**
 * @创建者 lucas
 * @创建时间 2017/9/13 0013 15:15
 * @描述
 */

public class CouponPagerAdapter extends PagerAdapter {
    private Context        mContexts;
    private String[]       mTitles;
    private CouponListBean mBean;

    public CouponPagerAdapter(Context contexts, String[] titles, CouponListBean bean) {
        mContexts = contexts;
        mTitles = titles;
        mBean = bean;
    }

    @Override
    public int getCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RecyclerView recyclerView = new RecyclerView(mContexts);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        recyclerView.setLayoutParams(params);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContexts));
        int px = UIUtil.dp2px(mContexts, 7);
        recyclerView.addItemDecoration(new SpacesItemDecoration(px, 0, px * 2, px * 2));
        CouponAdapter adapter = new CouponAdapter(mContexts);
        recyclerView.setAdapter(adapter);
        switch (position) {
            case 0:
                adapter.setData(mBean.data1);
                break;
            case 1:
                adapter.setData(mBean.data2);
                break;
            case 2:
                adapter.setData(mBean.data3);
                break;
        }
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
