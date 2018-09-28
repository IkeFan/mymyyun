package com.mmy.maimaiyun.model.classity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.ClassBean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/8/21 0021 18:14
 * @描述 TODO
 */

public class ClassAdapter extends BaseRecyclerViewAdapter {

    private List<ClassBean> mData;

    private int selectPosition;
    private Context mContext;
    private OnSelectListener mListener;

    public ClassAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clazz, parent,false);
        return new BaseRecyclerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final BaseRecyclerViewHolder holder, int position) {
        int color = selectPosition == position ? mContext.getResources().getColor(R.color.colorPrimary)
                : mContext.getResources().getColor(R.color.white);
        int color2 = selectPosition == position ? mContext.getResources().getColor(R.color.colorPrimary)
                : mContext.getResources().getColor(R.color.textColorSecondary);
        holder.getRootView().findViewById(R.id.divide).setBackgroundColor(color);
        TextView text = (TextView) holder.getRootView().findViewById(R.id.clazz_name);
        text.setTextColor(color2);
        holder.setStr2TV(R.id.clazz_name,mData.get(position).cat_name);
        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPosition = holder.getPosition();
                Log.d("ClassAdapter", "selectPosition:" + selectPosition);
                notifyDataSetChanged();
                if (mListener!=null)
                    mListener.onSelect(holder.getPosition());
            }
        });
    }

    public void setOnSelectListener(OnSelectListener listener){
        mListener = listener;
    }

    public interface OnSelectListener{
        void onSelect(int position);
    }

    public void setData(List<ClassBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }
}
