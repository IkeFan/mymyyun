package com.mmy.maimaiyun.model.main.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.GoodNewsClassBean;
import com.mmy.maimaiyun.data.bean.GoodNewsItemBean;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/9/4 0004 9:28
 * @描述 TODO
 */

public class GoodNewsPagerAdapter extends PagerAdapter {
    private List<GoodNewsClassBean> mTitle;
    private Context                 mContext;
    LinkedHashMap<Integer,GoodNewsAdapter>                             mMap =new LinkedHashMap<>();
    LinkedHashMap<Integer,BaseRecyclerViewAdapter.OnItemClickListener> mListeners =new LinkedHashMap<>();
    private OnNewsClickListener mListener;

    public GoodNewsPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mTitle == null ? 0 : mTitle.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pager_good_news, null);
        RecyclerView list = (RecyclerView) view.findViewById(R.id.good_news_list);
        list.setLayoutManager(new LinearLayoutManager(mContext));
        int space = UIUtil.dp2px(mContext, 3);
        list.addItemDecoration(new SpacesItemDecoration(space,0,0,0));
        GoodNewsAdapter adapter = new GoodNewsAdapter(mContext);
        list.setAdapter(adapter);
        mMap.put(position,adapter);
        container.addView(view);
        mListeners.put(position, new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                if (mListener != null) {
                    mListener.onNewsClick(position,pos);
                }
            }
        });
        adapter.addOnItemCliclListener(mListeners.get(position));
        return view;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position).cat_name;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setData(List<GoodNewsItemBean> data,int position) {
        mMap.get(position).setData(data,mTitle.get(position));
    }

    public void setClassData(List<GoodNewsClassBean> data){
        mTitle = data;
        notifyDataSetChanged();
    }

    public void setOnNewsClickListener(OnNewsClickListener listener){
        mListener = listener;
    }

    public interface OnNewsClickListener{
        void onNewsClick(int index,int position);
    }
}
