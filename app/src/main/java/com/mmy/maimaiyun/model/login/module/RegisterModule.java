package com.mmy.maimaiyun.model.login.module;

import com.mmy.maimaiyun.model.login.view.RegisterView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/8/24 0024 10:18
 * @描述 TODO
 */
@Module
public class RegisterModule {
    private RegisterView mView;

    public RegisterModule(RegisterView view) {
        mView = view;
    }

    @Provides
    RegisterView provideView(){
        return mView;
    }
}
