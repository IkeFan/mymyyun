package com.mmy.maimaiyun.model.main.module;

import com.mmy.maimaiyun.model.main.view.OnlyNewView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2018/1/17 0017 19:16
 * @描述 TODO
 */
@Module
public class OnlyNewModule {
    private OnlyNewView mView;

    public OnlyNewModule(OnlyNewView view) {
        mView = view;
    }

    @Provides
    OnlyNewView provideView(){
        return  mView;
    }
}
