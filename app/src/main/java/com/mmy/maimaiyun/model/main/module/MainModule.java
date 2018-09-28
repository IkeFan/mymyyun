package com.mmy.maimaiyun.model.main.module;

import com.mmy.maimaiyun.model.main.view.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/23 0023 16:22
 * @描述 TODO
 */
@Module
public class MainModule {
    private MainView mView;

    public MainModule(MainView view) {
        mView = view;
    }

    @Provides
    MainView provideView(){
        return mView;
    }
}
