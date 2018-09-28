package com.mmy.maimaiyun.model.classity.adapter;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.customview.FlowLayout;
import com.mmy.maimaiyun.data.bean.ClassBean;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/10/24 0024 16:10
 * @描述 TODO
 */

public class ClassityRightAdapter extends BaseRecyclerViewAdapter {

    private       LinkedHashMap<ClassBean, List<ClassBean>> mData;
    private final int                                       mPx8;
    private       Context                                   mContext;
    private       OnInnerClassItemClickListener             mListener;

    public ClassityRightAdapter(Context context) {
        super(context);
        mPx8 = UIUtil.dp2px(context, 6);
        mContext = context;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classity2_right, parent, false);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        ClassBean key = (ClassBean) mData.keySet().toArray()[position];
        List<ClassBean> list = mData.get(key);
        holder.setStr2TV(R.id.title,key.cat_name);
        holder.findView(R.id.title).setOnClickListener(v->{
            if (mListener != null) {
                mListener.onClassItemClick(v,key.id,key.cat_name);
            }
        });
        FlowLayout flowLayout = (FlowLayout) holder.findView(R.id.sub_class);
        flowLayout.removeAllViews();
        for (ClassBean bean : list) {
            TextView textView = new TextView(mContext);
            textView.setPadding(mPx8, mPx8, mPx8, mPx8);
            textView.setGravity(Gravity.CENTER);
            textView.setText(bean.cat_name);
            textView.setTextColor(mContext.getResources().getColor(R.color.hint_text_color));
            textView.setBackgroundResource(R.drawable.shape_gray);
            flowLayout.addView(textView);
            textView.setOnClickListener(v -> {
                if (mListener != null) {
                    mListener.onClassItemClick(v,bean.id,bean.cat_name);
                }
            });
        }
    }

    public void setData(LinkedHashMap<ClassBean, List<ClassBean>> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setOnInnerClassItemClickListener(OnInnerClassItemClickListener listener){
        mListener = listener;
    }

    public interface OnInnerClassItemClickListener{
        void onClassItemClick(View view, String id, String name);
    }
}
