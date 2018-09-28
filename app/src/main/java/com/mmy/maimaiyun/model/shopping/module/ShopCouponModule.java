package com.mmy.maimaiyun.model.shopping.module;

import com.mmy.maimaiyun.model.shopping.view.ShopCouponView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017-11-11 11-59-22
 * @描述 订单中商铺对应的优惠券module
 */
@Module
public class ShopCouponModule{
    private ShopCouponView mView;

    public ShopCouponModule(ShopCouponView view) {
        mView = view;
    }

    @Provides
    ShopCouponView provideIView(){
        return mView;
    }
}
