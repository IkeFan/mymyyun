package com.mmy.maimaiyun.model.shopping.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.shopping.module.ShopCouponModule;
import com.mmy.maimaiyun.model.shopping.ui.activity.ShopCouponActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017-11-11 11-59-22
 * @描述 订单中商铺对应的优惠券组件
 */
@ActivityScoped
@Component(modules = ShopCouponModule.class,dependencies = AppComponent.class)
public interface ShopCouponComponent {
    void inject(ShopCouponActivity view);
}
