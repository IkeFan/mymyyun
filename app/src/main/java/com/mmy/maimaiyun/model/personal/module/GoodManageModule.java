package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.GoodManageView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/16 0016 15:16
 * @描述 TODO
 */

@Module
public class GoodManageModule {
    private GoodManageView mView;

    public GoodManageModule(GoodManageView view) {
        mView = view;
    }

    @Provides
    GoodManageView provideView(){
        return mView;
    }
}
