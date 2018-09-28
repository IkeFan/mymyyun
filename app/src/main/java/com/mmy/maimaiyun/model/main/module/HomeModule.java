package com.mmy.maimaiyun.model.main.module;

import com.mmy.maimaiyun.model.main.view.HomeView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/8/17 0017 11:48
 * @描述 TODO
 */
@Module
public class HomeModule {

    private HomeView mHomeView;

    public HomeModule(HomeView homeView) {
        mHomeView = homeView;
    }

    @Provides
    HomeView provideHomeView(){
        return mHomeView;
    }
}
