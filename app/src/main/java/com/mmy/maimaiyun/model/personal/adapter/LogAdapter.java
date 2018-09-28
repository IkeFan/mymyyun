package com.mmy.maimaiyun.model.personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.LogBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/12/30 0030 14:51
 * @描述 TODO
 */

public class LogAdapter extends BaseRecyclerViewAdapter {


    private List<LogBean.DataBean> mData;
    private Context                mContext;

    public LogAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_log, parent, false);
        return new BaseRecyclerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        LogBean.DataBean bean = mData.get(position);
        holder.setStr2TV(R.id.info, bean.context);
        holder.setStr2TV(R.id.time, bean.time);
        if (position == 0) {
            holder.findView(R.id.point).setBackgroundResource(R.drawable.circle_red);
            ((TextView) (holder.findView(R.id.info))).setTextColor(mContext.getResources().getColor(R.color.red));
            ((TextView) (holder.findView(R.id.time))).setTextColor(mContext.getResources().getColor(R.color.red));
        } else {
            holder.findView(R.id.point).setBackgroundResource(R.drawable.circle_gray);
            ((TextView) (holder.findView(R.id.info))).setTextColor(mContext.getResources().getColor(R.color
                    .hint_text_color));
            ((TextView) (holder.findView(R.id.time))).setTextColor(mContext.getResources().getColor(R.color
                    .hint_text_color));
        }
        holder.findView(R.id.line).setVisibility((position == mData.size() - 1) ? View.GONE : View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(LogBean data) {
        mData = data.data;
        notifyDataSetChanged();
    }
}
