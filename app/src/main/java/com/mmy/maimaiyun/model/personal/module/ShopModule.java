package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.ShopView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/20 0020 9:52
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
