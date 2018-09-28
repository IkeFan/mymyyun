package com.mmy.maimaiyun.model.shopping.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.FragmentScoped;
import com.mmy.maimaiyun.model.shopping.module.ShoppingModule;
import com.mmy.maimaiyun.model.shopping.ui.fragment.ShoppingFragment;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/8/22 0022 16:21
 * @描述 TODO
 */
@FragmentScoped
@Component(modules = {ShoppingModule.class},dependencies = AppComponent.class)
public interface ShoppingComponent {
    void  inject(ShoppingFragment fragment);
}
