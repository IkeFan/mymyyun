package com.mmy.maimaiyun.model.main.module;

import com.mmy.maimaiyun.model.main.view.GoodInfoView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/5 0005 11:17
 * @描述 TODO
 */
@Module
public class GoodInfoModule {
    private GoodInfoView mView;

    public GoodInfoModule(GoodInfoView view) {
        mView = view;
    }

    @Provides
    GoodInfoView provideView(){
        return mView;
    }
}
