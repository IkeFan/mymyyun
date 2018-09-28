package com.mmy.maimaiyun.model.shopping.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.ConfOrdersBean;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/10/7 0007 9:37
 * @描述
 */

public class ConfOrdersAdapter extends BaseRecyclerViewAdapter {

    private List<ConfOrdersBean.ShopBean>     mData;
    private Context                           mContext;
    private OnConfOrderListInnerClickListener mListInnerCLickListener;

    //优惠券
    public static final int TYPE_COUPON = 0;
    //嘟嘟卡
    public static final int TYPE_DUDU   = 2;
    //商品
    public static final int TYPE_GOOD   = 1;

    public ConfOrdersAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shoping, parent, false);
        view.findViewById(R.id.is_select_shop).setVisibility(View.GONE);
        view.findViewById(R.id.shop_name).setVisibility(View.VISIBLE);
        view.findViewById(R.id.coupon).setVisibility(View.VISIBLE);
        view.findViewById(R.id.fullcut).setVisibility(View.VISIBLE);
        view.findViewById(R.id.dudu).setVisibility(View.VISIBLE);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        ConfOrdersBean.ShopBean bean = mData.get(position);
        holder.setStr2TV(R.id.shop_name, bean.shopName);
        LinearLayout goodsView = (LinearLayout) holder.findView(R.id.good_container);
        goodsView.removeAllViews();
        if (bean.goodsInfo != null)
            for (int i = 0; i < bean.goodsInfo.size(); i++) {
                ConfOrdersBean.ShopBean.GoodsInfoBean infoBean = bean.goodsInfo.get(i);
                View child = bindGoodView(goodsView, infoBean, holder, i);
                goodsView.addView(child);
            }

        //活动
        String cut = "";
        if (bean.fullcutInfo != null)
            for (ConfOrdersBean.ShopBean.FullcutInfoBean fullcutInfoBean : bean.fullcutInfo) {
                cut = cut + (TextUtils.isEmpty(cut) ? "" : "/") + fullcutInfoBean.desc;
            }
        holder.setStr2TV(R.id.fullcut, cut);

        //优惠券设置点击事件
        View couponView = holder.findView(R.id.coupon);
        TextView couponName = (TextView) couponView.findViewById(R.id.coupon_name);
        couponName.setText(bean.selectShopCoupon == null ? "请选择" : bean.selectShopCoupon.name);
        if (bean.couponInfo != null && bean.couponInfo.size() != 0) {
            couponView.setOnClickListener(v -> {
                if (mListInnerCLickListener != null) {
                    mListInnerCLickListener.onOrderListInnerClick(v, holder.getAdapterPosition(), -1, TYPE_COUPON);
                }
            });
        } else {
            couponName.setText("暂无优惠券");
        }

        if (bean.selectShopCoupon != null) {
            couponName.setText(bean.selectShopCoupon.name);
        }
        //嘟嘟卡
        View duduView = holder.findView(R.id.dudu);
        if (!TextUtils.isEmpty(bean.duducardNum))
            holder.setStr2TV(R.id.dudu_count, "剩余" + bean.duducardNum + "张(100元)");
        duduView.setVisibility(bean.duducardInfo == null ? View.GONE : View.VISIBLE);
        holder.setChecked(R.id.dudu_name, bean.selectDudu);
        duduView.setOnClickListener(v -> {
            if (mListInnerCLickListener != null) {
                View view = holder.findView(R.id.dudu_name);
                mListInnerCLickListener.onOrderListInnerClick(view, holder.getAdapterPosition(), -1, TYPE_DUDU);
            }
        });
    }

    private View bindGoodView(LinearLayout view, ConfOrdersBean.ShopBean.GoodsInfoBean bean, BaseRecyclerViewHolder
            holder, int i) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_item_shopping, view, false);
        rootView.findViewById(R.id.select).setVisibility(View.GONE);
        TextView t1 = (TextView) rootView.findViewById(R.id.shopping_name);
        TextView t2 = (TextView) rootView.findViewById(R.id.shopping_money);
        TextView t4 = (TextView) rootView.findViewById(R.id.count);
        TextView t5 = (TextView) rootView.findViewById(R.id.show_count);
        TextView t6 = (TextView) rootView.findViewById(R.id.attrs);
        TextView t7 = (TextView) rootView.findViewById(R.id.logistics_money);
        ImageView i1 = (ImageView) rootView.findViewById(R.id.shopping_icon);
        t1.setText(bean.goods_name);
        t2.setText("￥" + bean.shop_price);
        t4.setText(bean.goods_number + "");
        t5.setText("数量 " + bean.goods_number + " 件");
        t7.setText("邮费：￥" + bean.logistics_money);
        if (!TextUtils.isEmpty(bean.goods_attr_value))
            t6.setText(bean.goods_attr_value);
        Picasso.with(mContext).load(bean.logo).error(R.mipmap.ic_def).into(i1);
        //商品点击事件
        rootView.setOnClickListener(v -> {
            if (mListInnerCLickListener != null) {
                mListInnerCLickListener.onOrderListInnerClick(v, holder.getAdapterPosition(), i, TYPE_GOOD);
            }
        });
        return rootView;
    }

    public void setData(List<ConfOrdersBean.ShopBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setOnConfOrderListInnerCLickListener(OnConfOrderListInnerClickListener listInnerCLickListener) {
        mListInnerCLickListener = listInnerCLickListener;
    }

    public void refreshCoupon(int shop_position, ConfOrdersBean.ShopBean.CouponInfoBean coupon) {
        mData.get(shop_position).selectShopCoupon = coupon;
        notifyDataSetChanged();
    }

    //修改UI
    public void chechDudu(int shopPosition, boolean isSelect) {
        mData.get(shopPosition).selectDudu = isSelect;
        if (isSelect)
            mData.get(shopPosition).selectShopCoupon = null;
        notifyDataSetChanged();
    }

    public interface OnConfOrderListInnerClickListener {
        void onOrderListInnerClick(View view, int shopPosition, int goodPosition, int type);
    }
}
