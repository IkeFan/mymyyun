package com.mmy.maimaiyun.model.main.module;

import com.mmy.maimaiyun.model.main.view.OverseasShoppingView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/1 0001 16:13
 * @描述 TODO
 */
@Module
public class OverseasShoppingModule {
    private OverseasShoppingView mView;

    public OverseasShoppingModule(OverseasShoppingView view) {
        mView = view;
    }

    @Provides
    OverseasShoppingView provideView(){
        return mView;
    }
}
