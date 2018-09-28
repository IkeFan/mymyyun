package com.mmy.maimaiyun.model.login.module;

import com.mmy.maimaiyun.model.login.view.LoginView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/8/12 0012 16:02
 * @描述 TODO
 */
@Module
public class LoginModule{
    private LoginView mView;

    public LoginModule(LoginView view) {
        mView = view;
    }

    @Provides
    LoginView provideIView(){
        return mView;
    }
}
