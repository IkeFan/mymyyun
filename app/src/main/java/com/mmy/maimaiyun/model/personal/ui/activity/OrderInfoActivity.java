package com.mmy.maimaiyun.model.personal.ui.activity;

import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.data.bean.OrderBean;
import com.mmy.maimaiyun.model.personal.adapter.OrderAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerOrderInfoComponent;
import com.mmy.maimaiyun.model.personal.module.OrderInfoModule;
import com.mmy.maimaiyun.model.personal.presenter.OrderInfoPresenter;
import com.mmy.maimaiyun.model.personal.view.OrderInfoView;
import com.mmy.maimaiyun.utils.UIUtil;
import com.squareup.picasso.Picasso;

import java.util.Date;

import butterknife.Bind;

/**
 * 订单详情
 */
public class OrderInfoActivity extends BaseActivity<OrderInfoPresenter> implements OrderInfoView {

    @Bind(R.id.title_center_text)
    TextView  mTitle;
    @Bind(R.id.name)
    TextView  mName;
    @Bind(R.id.phone)
    TextView  mPhone;
    @Bind(R.id.address)
    TextView  mAddress;
    @Bind(R.id.time)
    TextView  mTime;
    @Bind(R.id.order_id)
    TextView  mOrderId;
    @Bind(R.id.freight)
    TextView  mFreight;
    @Bind(R.id.coupon)
    TextView  mCoupon;
    @Bind(R.id.all_money)
    TextView  mAllMoney;
    @Bind(R.id.payment)
    TextView  mPayment;
    @Bind(R.id.good_container)
    LinearLayout mGoodContainer;
    @Bind(R.id.status)
    TextView mStatus;
    @Bind(R.id.delete)
    View mDelete;
    @Bind(R.id.operation)
    LinearLayout mOperation;
    private int mDp5;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerOrderInfoComponent.builder()
                .appComponent(appComponent)
                .orderInfoModule(new OrderInfoModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mDp5 = UIUtil.dp2px(this, 5);
        mTitle.setText("订单详情");
        OrderBean bean = (OrderBean) getIntent().getSerializableExtra("bean");
        mName.setText("收货人：" + bean.consignee);
        mPhone.setText(bean.mobile);
        mAddress.setText("收货地址：" + bean.address);
        mTime.setText(TimeUtils.date2String(new Date(Long.parseLong(bean.add_time) * 1000)));
        mOrderId.setText(bean.order_shop_sn);
        float allMoney = 0;
        for (OrderBean.GoodsInfoBean good : bean.goodsInfo) {
            allMoney += good.price;
        }
        mAllMoney.setText("￥" +allMoney);
        mPayment.setText("￥" +allMoney);
        mStatus.setText(OrderAdapter.statusName[bean.my_order_status]);
        for (OrderBean.GoodsInfoBean good : bean.goodsInfo) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_order_good, mGoodContainer, false);
            mGoodContainer.addView(view);
            ImageView icon = (ImageView) view.findViewById(R.id.order_icon);
            TextView name = (TextView) view.findViewById(R.id.order_name);
            TextView clazz = (TextView) view.findViewById(R.id.order_clazz);
            Picasso.with(this).load(good.logo).into(icon);
            name.setText(good.goods_name);
            clazz.setText(good.goods_attr_value);
            //设置价格
            TextView money = (TextView) view.findViewById(R.id.order_money);
            int color = getResources().getColor(R.color.colorPrimary);
            money.setText(Html.fromHtml("合计￥<font color='" + color + "'>" + good.price + "</font>(含运费￥"+good.shipping_price+") " +
                    "数量 " + good.goods_number + " 件"));
        }
        //订单可操作的状态
//        switch (bean.my_order_status) {
//            case PENDING_PAYMENT:
//                mOperation.addView(createItem(strs[3],0,false));
//                mOperation.addView(createItem(strs[4],1,true));
//                mDelete.setVisibility(View.GONE);
//                break;
//            case SHIPMENT_PENDING:
//                mOperation.addView(createItem(strs[0],0,false));
//                mOperation.addView(createItem(strs[5],1,true));
//                mDelete.setVisibility(View.GONE);
//                break;
//            case GOODS_RECEIVED:
//                mOperation.addView(createItem(strs[0],0,false));
//                mOperation.addView(createItem(strs[1],1,false));
//                mOperation.addView(createItem(strs[2],2,true));
//                mDelete.setVisibility(View.GONE);
//                break;
//            case GRADING:
//                mOperation.addView(createItem(strs[0],0,false));
//                mOperation.addView(createItem(strs[6],1,true));
//                break;
//            case COMPLETE:
//                mOperation.addView(createItem(strs[0],0,false));
//                mOperation.addView(createItem(strs[7],1,false));
//                break;
//        }
    }

    @NonNull
    private TextView createItem(String str, int index,boolean isRed) {
        TextView view = new TextView(this);
        view.setPadding(mDp5, mDp5, mDp5, mDp5);
        view.setText(str);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = mDp5;
        view.setLayoutParams(params);
        view.setBackgroundResource(R.drawable.stroke_black_bg);
        if (isRed) {
            view.setBackgroundResource(R.drawable.stroke_orange_bg);
            view.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_order_info;
    }
}
