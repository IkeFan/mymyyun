package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.ShopClazzView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/20 0020 10:51
 * @描述 TODO
 */
@Module
public class ShopClazzModule {
    private ShopClazzView mView;

    public ShopClazzModule(ShopClazzView view) {
        mView = view;
    }

    @Provides
    ShopClazzView provideView(){
        return mView;
    }
}
