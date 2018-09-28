package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.ShopManagerView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/11/24 0024 16:19
 * @描述
 */
@Module
public class ShopManagerModule {
    private ShopManagerView mView;

    public ShopManagerModule(ShopManagerView view) {
        mView = view;
    }

    @Provides
    ShopManagerView provideView(){
        return mView;
    }
}
