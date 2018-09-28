package com.mmy.maimaiyun.model.main.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.ClassBean;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/9/1 0001 17:32
 * @描述 TODO
 */

public class OverseasPagerAdapter extends PagerAdapter {
    private Context         mContext;
    private List<ClassBean> mData;

    public OverseasPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RecyclerView view = new RecyclerView(mContext);
        container.addView(view);
        OverseasAdapter adapter = new OverseasAdapter(mContext);
        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(mContext));
        view.addItemDecoration(new SpacesItemDecoration(UIUtil.dp2px(mContext, 5)));
//        adapter.setData(mData.get(position).datas);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    public void setData(List<ClassBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mData.get(position).cat_name;
    }

    public static class OverseasBean {
        public String        title;
        public List<Integer> datas;

        public OverseasBean(String title, List<Integer> datas) {
            this.title = title;
            this.datas = datas;
        }
    }
}

