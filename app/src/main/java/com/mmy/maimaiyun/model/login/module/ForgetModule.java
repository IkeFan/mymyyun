package com.mmy.maimaiyun.model.login.module;

import com.mmy.maimaiyun.model.login.view.ForgetView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/8/24 0024 11:37
 * @描述 TODO
 */
@Module
public class ForgetModule {
    private ForgetView mView;

    public ForgetModule(ForgetView view) {
        mView = view;
    }

    @Provides
    ForgetView provideView(){
        return mView;
    }
}
