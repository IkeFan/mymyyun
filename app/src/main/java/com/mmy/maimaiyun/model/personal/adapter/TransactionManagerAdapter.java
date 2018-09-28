package com.mmy.maimaiyun.model.personal.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.able.OnItemInnerClickListener;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/7 0007 17:04
 * @描述 TODO
 */

public class TransactionManagerAdapter extends BaseRecyclerViewAdapter {
    private final int                      mDp5;
    private       List<String>             mData;
    private       Context                  mContext;
    private       OnItemInnerClickListener mListener;
    public static final int CUSTOMER_SERVICE = 0;

    public TransactionManagerAdapter(Context context) {
        super(context);
        mContext = context;
        mDp5 = UIUtil.dp2px(context, 5);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_transaction_manager, viewGroup,false);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int i) {
        //设置价格
        TextView money = (TextView) holder.getRootView().findViewById(R.id.order_money);
        int color = mContext.getResources().getColor(R.color.colorPrimary);
        money.setText(Html.fromHtml("合计￥<font color='" + color + "'>55.00</font>(含运费￥3.00) 数量 1 件"));
        holder.findView(R.id.customer_service).setOnClickListener(v->{
            if (mListener!=null)
                mListener.onItemInnerClick(v,holder.getAdapterPosition(),CUSTOMER_SERVICE);
        });
    }

    public void setData(List<String> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void setOnItemInnerClickListener(OnItemInnerClickListener listener){
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

}
