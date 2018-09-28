package com.mmy.maimaiyun.model.main.module;

import com.mmy.maimaiyun.model.main.view.HotGoodsView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/2 0002 9:58
 * @描述 TODO
 */
@Module
public class HotGoodsModule {
    private HotGoodsView mView;

    public HotGoodsModule(HotGoodsView view) {
        mView = view;
    }

    @Provides
    HotGoodsView provideView(){
        return mView;
    }
}
