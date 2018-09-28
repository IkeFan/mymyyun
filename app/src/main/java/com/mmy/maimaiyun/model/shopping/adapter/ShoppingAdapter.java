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
import com.mmy.maimaiyun.data.bean.ShopCarListBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/8/22 0022 11:28
 * @描述
 */

public class ShoppingAdapter extends BaseRecyclerViewAdapter {

    private List<ShopCarListBean>  mData;
    private OnAdd2ShoppingListener mListener;
    private boolean                mIsSwitch;
    private onSelectChangeListener mOnSelectChangeListener;
    private Context                mContext;

    public ShoppingAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shoping, null);
        return new ShoppingHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder h, final int position) {
        final ShoppingHolder holder = (ShoppingHolder) h;
        final ShopCarListBean bean = mData.get(position);
        holder.setStr2TV(R.id.is_select_shop, bean.shopInfo);
        LinearLayout goodsView = (LinearLayout) holder.findView(R.id.good_container);
        goodsView.removeAllViews();
        if (bean.cartInfo != null)
            for (int i = 0; i < bean.cartInfo.size(); i++) {
                ShopCarListBean.CartInfoBean infoBean = bean.cartInfo.get(i);
                View child = bindGoodView(goodsView, infoBean, holder.getAdapterPosition(), i);
                goodsView.addView(child);
            }
        holder.setChecked(R.id.is_select_shop, mIsSwitch ? bean.isDel : bean.isCheck);
        holder.findView(R.id.shop_container).setOnClickListener(v -> {
            if (mIsSwitch)
                bean.isDel = !bean.isDel;
            else
                bean.isCheck = !bean.isCheck;
            holder.setChecked(R.id.is_select_shop, mIsSwitch ? bean.isDel : bean.isCheck);
            //选择商店下的所有商品
            selectAllGoods(goodsView, holder.getAdapterPosition());
            if (mListener != null) {
                mListener.onShopCheckedChanged(v, holder.getAdapterPosition());
            }
        });
    }

    private void selectAllGoods(LinearLayout goodsView, int adapterPosition) {
        ShopCarListBean listBean = mData.get(adapterPosition);
        for (int i = 0; i < listBean.cartInfo.size(); i++) {
            View childAt = goodsView.getChildAt(i);
            ShopCarListBean.CartInfoBean bean = listBean.cartInfo.get(i);
            if (mIsSwitch)
                bean.isDel = listBean.isDel;
            else
                bean.isChecked = listBean.isCheck;
            ImageView checkBox = (ImageView) childAt.findViewById(R.id.select);
            checkBox.setImageResource((mIsSwitch ? bean.isDel : bean.isChecked) ? R.mipmap.checked : R.mipmap
                    .check_normal);
        }
    }

    private View bindGoodView(LinearLayout view, ShopCarListBean.CartInfoBean bean, int adapterPosition, int position) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_item_shopping, view, false);
        TextView t1 = (TextView) rootView.findViewById(R.id.shopping_name);
        TextView t2 = (TextView) rootView.findViewById(R.id.shopping_money);
        TextView t4 = (TextView) rootView.findViewById(R.id.count);
        TextView t5 = (TextView) rootView.findViewById(R.id.show_count);
        TextView t6 = (TextView) rootView.findViewById(R.id.attrs);
        ImageView i1 = (ImageView) rootView.findViewById(R.id.shopping_icon);
        t1.setText(bean.goods_name);
        t2.setText("￥" + bean.shop_price);
        t4.setText(bean.goods_number + "");
        t5.setText("数量 " + bean.goods_number + " 件");
        t6.setText(bean.goods_attr_value);
        Picasso.with(mContext).load(bean.logo).into(i1);

        View priceContainer = rootView.findViewById(R.id.price_container);
        View countContainer = rootView.findViewById(R.id.count_container);
        if (!mIsSwitch) {
            priceContainer.setVisibility(View.VISIBLE);
            countContainer.setVisibility(View.GONE);
        } else {
            priceContainer.setVisibility(View.GONE);
            countContainer.setVisibility(View.VISIBLE);
        }
        final ImageView checkBox = (ImageView) rootView.findViewById(R.id.select);
        checkBox.setImageResource(bean.isChecked ? R.mipmap.checked : R.mipmap.check_normal);
        rootView.findViewById(R.id.delete).setOnClickListener(v -> {
            //            mData.remove(holder.getAdapterPosition());
            //            notifyItemRemoved(holder.getAdapterPosition());
            if (mOnSelectChangeListener != null) {
                mOnSelectChangeListener.onDeleteShop(bean.id);
            }
            //重新计算价格
            // TODO: 2017/11/10 0010
            if (mListener != null) {
                mListener.onReCalculate();
            }
        });
        rootView.setOnClickListener(v -> {
            if (mIsSwitch)
                bean.isDel = !bean.isDel;
            else
                bean.isChecked = !bean.isChecked;
            checkBox.setImageResource((mIsSwitch ? bean.isDel : bean.isChecked) ? R.mipmap.checked : R.mipmap
                    .check_normal);
            if (mListener != null) {
                mListener.onGoodCheckedChanged(v, mIsSwitch ? bean.isDel : bean.isChecked, adapterPosition, position);
            }
        });
        TextView count = (TextView) rootView.findViewById(R.id.count);
        //商品数量
        rootView.findViewById(R.id.minus).setOnClickListener(v -> {
            if (bean.goods_number <= 1)
                return;
            bean.goods_number -= 1;
            count.setText(bean.goods_number + "");
            //重新计算价格
            if (mListener != null) {
                mListener.onReCalculate();
            }
            if (mOnSelectChangeListener != null) {
                mOnSelectChangeListener.onShopCountChange(bean.id, bean.goods_number);
            }
        });
        rootView.findViewById(R.id.add).setOnClickListener(v -> {
            bean.goods_number += 1;
            count.setText(bean.goods_number + "");
            //重新计算价格
            if (mListener != null) {
                mListener.onReCalculate();
            }
            if (mOnSelectChangeListener != null) {
                mOnSelectChangeListener.onShopCountChange(bean.id, bean.goods_number);
            }
        });

        return rootView;
    }

    //编辑状态切换
    public void switchStatus() {
        mIsSwitch = !mIsSwitch;
        notifyDataSetChanged();
    }

    //移除购物车
    public void delete() {
        //移除的id
        String ids = getSelectIds();
        if (mOnSelectChangeListener != null && !TextUtils.isEmpty(ids)) {
            mOnSelectChangeListener.onDeleteShop(ids);
        }
    }

    public String getSelectIds() {
        String ids = "";
        for (ShopCarListBean shopCarListBean : mData) {
            for (ShopCarListBean.CartInfoBean infoBean : shopCarListBean.cartInfo) {
                if (mIsSwitch ? infoBean.isDel : infoBean.isChecked) {
                    ids += TextUtils.isEmpty(ids) ? infoBean.id : ("," + infoBean.id);
                }
            }
        }
        return ids;
    }

    public interface onSelectChangeListener {
        void onDeleteShop(String ids);

        void onShopCountChange(String goods_id, int count);
    }

    public void setOnSelectChangeListener(onSelectChangeListener onSelectChangeListener) {
        mOnSelectChangeListener = onSelectChangeListener;
    }


    public void selectAll(boolean isCheck) {
        for (ShopCarListBean bean : mData) {
            for (ShopCarListBean.CartInfoBean infoBean : bean.cartInfo) {
                infoBean.isChecked = isCheck;
            }
        }
        notifyDataSetChanged();
    }

    public void setData(List<ShopCarListBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void setOnAdd2ShoppingListener(OnAdd2ShoppingListener listener) {
        mListener = listener;
    }


    public interface OnAdd2ShoppingListener {
        void onGoodCheckedChanged(View view, boolean isChecked, int position, int goodPosition);

        void onShopCheckedChanged(View view, int position);

        //重新计算
        void onReCalculate();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ShoppingHolder extends BaseRecyclerViewHolder {

        public ShoppingHolder(View view) {
            super(view);
        }
    }
}
