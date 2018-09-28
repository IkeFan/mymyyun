package com.mmy.maimaiyun.model.shopping.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.ConfOrdersBean;
import com.mmy.maimaiyun.model.shopping.adapter.ShopCouponAdapter;
import com.mmy.maimaiyun.model.shopping.component.DaggerShopCouponComponent;
import com.mmy.maimaiyun.model.shopping.module.ShopCouponModule;
import com.mmy.maimaiyun.model.shopping.presenter.ShopCouponPresenter;
import com.mmy.maimaiyun.model.shopping.view.ShopCouponView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.io.Serializable;

import butterknife.Bind;

/**
 * @创建者 lucas
 * @创建时间 2017-11-11 11-59-22
 * @描述 订单中商铺对应的优惠券界面
 */
public class ShopCouponActivity extends BaseActivity<ShopCouponPresenter> implements ShopCouponView, BaseRecyclerViewAdapter.OnItemClickListener {

    @Bind(R.id.title_center_text)
    TextView     mTitleCenterText;
    @Bind(R.id.list)
    RecyclerView mList;
    private ShopCouponAdapter       mAdapter;
    private ConfOrdersBean.ShopBean mBean;

    public static final  int SHOP_COUPON = 0x0100;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerShopCouponComponent.builder()
                .appComponent(appComponent)
                .shopCouponModule(new ShopCouponModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitleCenterText.setText("优惠券");
        mList.setLayoutManager(new LinearLayoutManager(this));
        int px = UIUtil.dp2px(this, 5);
        mList.addItemDecoration(new SpacesItemDecoration(0,px,px,px));
        mAdapter = new ShopCouponAdapter(this);
        mList.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        Serializable data = getIntent().getSerializableExtra("data");
        if (data instanceof ConfOrdersBean.ShopBean){
            mBean = (ConfOrdersBean.ShopBean) data;
            mAdapter.setData(mBean.couponInfo);
            mAdapter.setShopName(mBean.shopName);
        }
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mAdapter.addOnItemCliclListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_shop_coupon;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent data = getIntent();
        data.putExtra("bean",mBean.couponInfo.get(position));
        setResult(SHOP_COUPON,data);
        finishSelf();
    }
}
