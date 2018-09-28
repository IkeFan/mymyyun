package com.mmy.maimaiyun.model.main.module;

import com.mmy.maimaiyun.model.main.view.GoodsView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/1 0001 11:17
 * @描述 TODO
 */
@Module
public class GoodsModule {
    private GoodsView mView;

    public GoodsModule(GoodsView view) {
        mView = view;
    }

    @Provides
    GoodsView provideView(){
        return mView;
    }
}
