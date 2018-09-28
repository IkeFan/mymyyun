package com.mmy.maimaiyun.model.personal.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.OrderBean;
import com.mmy.maimaiyun.utils.UIUtil;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/7 0007 17:04
 * @描述 TODO
 */

public class OrderAdapter extends BaseRecyclerViewAdapter implements BaseRecyclerViewAdapter.OnItemClickListener {
    private final int                      mDp5;
    private       List<OrderBean>          mData;
    private       Context                  mContext;
    private       OnOrderItemClickListener mListener;

    public static       String[] statusName       = {"等待买家付款", "交易成功", "等待卖家发货", "待收货", "待评分", "退款中", "已退款"};
    public static       String[] strs             = {"售后/退款", "查看物流", "确认收货", "取消订单", "付款", "提醒卖家发货", "立即评价", "追加评价"};
    public static       String[] after            = {"未售后", "售后中", "售后已完成"};
    public static final int      ALL              = -1;//全部
    public static final int      PENDING_PAYMENT  = 0;//代付款
    public static final int      COMPLETE         = 1;//已完成
    public static final int      SHIPMENT_PENDING = 2;//代发货
    public static final int      GOODS_RECEIVED   = 3;//待收货
    public static final int      GRADING          = 4;//带评分
    public static final int      REFUNDING        = 5;//退款中
    public static final int      REFUNDED         = 6;//已退款
    public static final int      DELETED          = 7;//已删除

    public static final int REFUND               = 0x1000;//售后/退款
    public static final int VIEW_LOG             = 0x1001;//查看物流
    public static final int COMFIRM              = 0x1002;//确认收获
    public static final int CANCEL_ORDER         = 0x1003;//取消订单
    public static final int PAY                  = 0x1004;//付款
    public static final int REMIND_SHIPMENT      = 0x1005;//提醒卖家发货
    public static final int IMMEDIATE_ASSESSMENT = 0x1006;//立即评价
    public static final int ADD_EVALUATION       = 0x1007;//追加评价


    public static final int     ITEM_DELETE    = 1000;//删除
    private             Integer mCurrentSelect = ALL;

    public OrderAdapter(Context context) {
        super(context);
        mContext = context;
        mDp5 = UIUtil.dp2px(context, 5);
        addOnItemCliclListener(this);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order, viewGroup, false);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int i) {
        OrderBean bean = mData.get(i);
        LinearLayout goodsView = (LinearLayout) holder.findView(R.id.good_container);
        LinearLayout isAfterContainer = (LinearLayout) holder.findView(R.id.is_after_container);
        goodsView.removeAllViews();
        isAfterContainer.removeAllViews();
        if (isContainerAfterType(bean.goodsInfo, false)) {
            holder.setVisibility(R.id.title_and_time, View.VISIBLE);
            holder.setVisibility(R.id.good_container, View.VISIBLE);
        } else {
            holder.setVisibility(R.id.title_and_time, View.GONE);
            holder.setVisibility(R.id.good_container, View.GONE);
        }
        if (isContainerAfterType(bean.goodsInfo, true) && mCurrentSelect == ALL) {
            holder.setVisibility(R.id.is_after_container, View.VISIBLE);
            holder.setVisibility(R.id.is_after, View.VISIBLE);
        } else {
            holder.setVisibility(R.id.is_after_container, View.GONE);
            holder.setVisibility(R.id.is_after, View.GONE);
        }
        int tempIndex = 0;

        //判断订单中是否是所有商品都是在售后
        boolean isAllAfter = true;
        for (OrderBean.GoodsInfoBean good : bean.goodsInfo) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_order_good, goodsView, false);
            //售后中与非售后分开显示
            if (good.after_status == 0) {
                isAllAfter = false;
                goodsView.addView(view);
            } else {
                if (mCurrentSelect == ALL) {
                    TextView afterView = (TextView) holder.findView(R.id.is_after);
                    afterView.setText(after[good.after_status]);
                    isAfterContainer.addView(view);
                }
            }
            ImageView icon = (ImageView) view.findViewById(R.id.order_icon);
            TextView name = (TextView) view.findViewById(R.id.order_name);
            TextView clazz = (TextView) view.findViewById(R.id.order_clazz);
            Picasso.with(mContext).load(good.logo).into(icon);
            name.setText(good.goods_name);
            clazz.setText(good.goods_attr_value);
            //设置价格
            TextView money = (TextView) view.findViewById(R.id.order_money);
            int color = mContext.getResources().getColor(R.color.colorPrimary);
            money.setText(Html.fromHtml("合计￥<font color='" + color + "'>" +
                    (good.price + good.shipping_price) + "</font>(含运费￥" + good
                    .shipping_price + ") " + "数量 " + good.goods_number + " 件"));

            LinearLayout container = (LinearLayout) view.findViewById(R.id.tag_container);
            //显示售后
            if (good.afterSale == 1 && bean.order_shop_status < 5 && good.after_status == 0)
                container.addView(createItem(bean, holder, strs[0], tempIndex, false, REFUND));
            //显示评价
            if (bean.order_shop_status == GRADING && bean.order_shop_status < 5)
                container.addView(createItem(bean, holder, strs[6], tempIndex, true, IMMEDIATE_ASSESSMENT));
            tempIndex++;
        }
        holder.setStr2TV(R.id.shop_name, bean.shop_name);
        holder.setStr2TV(R.id.order_status, statusName[bean.order_shop_status]);
        holder.setStr2TV(R.id.order_time, TimeUtils.date2String(new Date(Long.parseLong(bean.add_time) * 1000)));
        LinearLayout tags = (LinearLayout) holder.getRootView().findViewById(R.id.order_tags);
        tags.removeAllViews();
        //订单可操作的状态
        switch (bean.order_shop_status) {
            case PENDING_PAYMENT://代付款
                tags.addView(createItem(bean, holder, strs[3], 0, false, CANCEL_ORDER));
                tags.addView(createItem(bean, holder, strs[4], 1, true, PAY));
                holder.setVisibility(R.id.order_delete, View.GONE);
                break;
            case SHIPMENT_PENDING://代发货
                if (!isAllAfter)
                    tags.addView(createItem(bean, holder, strs[5], 1, true, REMIND_SHIPMENT));
                holder.setVisibility(R.id.order_delete, View.GONE);
                break;
            case GOODS_RECEIVED://待收货
                tags.addView(createItem(bean, holder, strs[1], 1, false, VIEW_LOG));
                tags.addView(createItem(bean, holder, strs[2], 2, true, COMFIRM));
                holder.setVisibility(R.id.order_delete, View.GONE);
                break;
            case GRADING://带评分
                holder.setVisibility(R.id.order_delete, View.VISIBLE);
                break;
            case COMPLETE://已完成
                tags.addView(createItem(bean, holder, strs[7], 1, false, ADD_EVALUATION));
                holder.setVisibility(R.id.order_delete, View.VISIBLE);
                break;
            case REFUNDING://退款中
                holder.setVisibility(R.id.order_delete, View.VISIBLE);
                break;
            case REFUNDED://已退款
                holder.setVisibility(R.id.order_delete, View.VISIBLE);
                break;

        }
        holder.findView(R.id.order_delete).setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onOrderInnerItemClick(v, bean, holder.getAdapterPosition(), Integer.parseInt(bean.order_id)
                        , -1, ITEM_DELETE);
            }
        });

        //如果订单没有商品则不显示
        if (goodsView.getChildCount() == 0 && isAfterContainer.getChildCount() == 0)
            holder.getRootView().setVisibility(View.GONE);
        else
            holder.getRootView().setVisibility(View.VISIBLE);
    }

    //判断是否包含售后或者非售后 0不是售后 >0正在售后
    private boolean isContainerAfterType(List<OrderBean.GoodsInfoBean> goodsInfo, boolean isAfter) {
        for (OrderBean.GoodsInfoBean bean : goodsInfo) {
            if (bean.after_status == 0 && !isAfter)
                return true;
            if (bean.after_status > 0 && isAfter)
                return true;
        }
        return false;
    }

    @NonNull
    private TextView createItem(OrderBean bean, BaseRecyclerViewHolder holder, String str, final int index, boolean
            isRed, int type) {
        TextView view = new TextView(mContext);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        view.setPadding(mDp5, mDp5 / 2, mDp5, mDp5 / 2);
        view.setText(str);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = mDp5;
        view.setLayoutParams(params);
        view.setBackgroundResource(R.drawable.stroke_black_bg);
        if (isRed) {
            view.setBackgroundResource(R.drawable.stroke_orange_bg);
            view.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        }
        view.setOnClickListener(v -> {
            if (mListener != null)
                mListener.onOrderInnerItemClick(v, bean, holder.getAdapterPosition(), Integer.parseInt(bean.order_id)
                        , index, type);
        });
        return view;
    }

    public void setData(List<OrderBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setOnOrderItemClickListener(OnOrderItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onItemClick(View view, int position) {
        if (mListener != null) {
            mListener.onOrderItemClick(view, mData.get(position), position);
        }
    }

    public void setCurrentSelect(Integer currentSelect) {
        mCurrentSelect = currentSelect;
    }

    public interface OnOrderItemClickListener {
        void onOrderInnerItemClick(View view, OrderBean bean, int position, int order_id, int index, int type);

        void onOrderItemClick(View v, OrderBean bean, int position);
    }
}
