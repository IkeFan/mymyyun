package com.mmy.maimaiyun.model.shopping.module;

import com.mmy.maimaiyun.model.shopping.view.ShoppingView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/8/22 0022 16:17
 * @描述 TODO
 */
@Module
public class ShoppingModule {
    private ShoppingView mView;

    public ShoppingModule(ShoppingView view) {
        mView = view;
    }

    @Provides
    ShoppingView provideShoppingView(){
        return mView;
    }
}
