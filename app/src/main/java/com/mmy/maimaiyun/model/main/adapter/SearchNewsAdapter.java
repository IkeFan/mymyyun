package com.mmy.maimaiyun.model.main.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.GoodNewsItemBean;
import com.mmy.maimaiyun.utils.TimeUtil;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/4 0004 9:58
 * @描述 TODO
 */

public class SearchNewsAdapter extends BaseRecyclerViewAdapter {
    private List<GoodNewsItemBean> mData;

    public SearchNewsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_good_news, parent,false);
        return new BaseRecyclerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
            GoodNewsItemBean bean = mData.get(position);
            holder.setStr2TV(R.id.name, bean.title);
            holder.setStr2TV(R.id.content, bean.description);
            TimeUtil util = new TimeUtil();
            String timeDifference = util.getTimeDifference(bean.publish_time*1000, System.currentTimeMillis());
            holder.setStr2TV(R.id.time, timeDifference + "之前");
            if (!TextUtils.isEmpty(bean.thumb))
                holder.setPath2IV(R.id.icon, bean.thumb);

    }

    public void setData(List<GoodNewsItemBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
