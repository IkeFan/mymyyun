package com.mmy.maimaiyun.model.shopping.presenter;


import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.model.shopping.model.ShopCouponModel;
import com.mmy.maimaiyun.model.shopping.view.ShopCouponView;

import javax.inject.Inject;

/**
 * @创建者 lucas
 * @创建时间 2017-11-11 11-59-22
 * @描述 订单中商铺对应的优惠券控制器
 */

public class ShopCouponPresenter extends BasePresenter<ShopCouponView> {
    private ShopCouponView mV;

    @Inject
    ShopCouponModel mModel;

    @Inject
    public ShopCouponPresenter(ShopCouponView v) {
        mV = v;
    }
}
