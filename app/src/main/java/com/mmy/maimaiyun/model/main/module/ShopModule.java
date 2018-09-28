package com.mmy.maimaiyun.model.main.module;

import com.mmy.maimaiyun.model.main.view.ShopView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2018/1/30 0030 14:07
 * @描述 TODO
 */
@Module
public class ShopModule {
    private ShopView mView;

    public ShopModule(ShopView view) {
        mView = view;
    }

    @Provides
    ShopView provideView(){
        return mView;
    }
}
