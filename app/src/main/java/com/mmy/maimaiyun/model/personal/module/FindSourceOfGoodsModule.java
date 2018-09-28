package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.FindSourceOfGoodsView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/1 0001 11:17
 * @描述 TODO
 */
@Module
public class FindSourceOfGoodsModule {
    private FindSourceOfGoodsView mView;

    public FindSourceOfGoodsModule(FindSourceOfGoodsView view) {
        mView = view;
    }

    @Provides
    FindSourceOfGoodsView provideView(){
        return mView;
    }
}
