package com.mmy.maimaiyun.model.shopping.module;

import com.mmy.maimaiyun.model.shopping.view.ConfOrderView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/10/6 0006 15:11
 * @描述 TODO
 */
@Module
public class ConfOrderModule {
    private ConfOrderView mView;

    public ConfOrderModule(ConfOrderView view) {
        mView = view;
    }

    @Provides
    ConfOrderView provideView(){
        return mView;
    }
}
