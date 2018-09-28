package com.mmy.maimaiyun.model.personal.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/22 0022 11:10
 * @描述 TODO
 */

public class BusinessCustomerServiceAdapter extends BaseRecyclerViewAdapter {

    private final int    dp70;
    private Context mContext;
    private List<String> mData;

    public BusinessCustomerServiceAdapter(Context context) {
        super(context);
        dp70 = UIUtil.dp2px(context, 70);
        mContext = context;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp70);
        imageView.setLayoutParams(params);
        imageView.setImageResource(R.mipmap.ic_def);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return new BaseRecyclerViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {

    }

    public void setData(List<String> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
