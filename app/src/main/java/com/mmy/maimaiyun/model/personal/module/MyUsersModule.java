package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.MyUsersView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/20 0020 18:15
 * @描述 TODO
 */
@Module
public class MyUsersModule {
    private MyUsersView mView;

    public MyUsersModule(MyUsersView view) {
        mView = view;
    }

    @Provides
    MyUsersView provideView(){
        return mView;
    }
}
