package com.mmy.maimaiyun.model.main.module;

import com.mmy.maimaiyun.model.main.view.GoodNewsView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/4 0004 9:50
 * @描述 TODO
 */
@Module
public class GoodNewsModule {
    private GoodNewsView mView;

    public GoodNewsModule(GoodNewsView view) {
        mView = view;
    }

    @Provides
    GoodNewsView provideView(){
        return mView;
    }
}
