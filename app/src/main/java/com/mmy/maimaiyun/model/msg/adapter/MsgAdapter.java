package com.mmy.maimaiyun.model.msg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.MessageBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/18 0018 9:42
 * @描述 TODO
 */

public class MsgAdapter extends BaseRecyclerViewAdapter {

    private final SimpleDateFormat mFormat;
    private List<MessageBean>      mData;

    public MsgAdapter(Context context) {
        super(context);
        mFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_msg, viewGroup, false);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int i) {
        MessageBean bean = mData.get(i);
        holder.setDraw2IV(R.id.icon,bean.icon);
        holder.setStr2TV(R.id.title_name,bean.title+"("+bean.count+")");
        holder.setStr2TV(R.id.msg,bean.msg);
        holder.setStr2TV(R.id.time,mFormat.format(new Date(bean.time)));
    }

    public void setData(ArrayList<MessageBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
