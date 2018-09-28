package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.model.personal.view.AuthView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/12/11 0011 14:30
 * @描述 TODO
 */
@Module
public class AuthModule {
    private AuthView mView;
    private BaseActivity mAuthActivity;

    public AuthModule(AuthView view, BaseActivity authActivity) {
        mView = view;
        mAuthActivity = authActivity;
    }

    @Provides
    AuthView provideView(){
        return  mView;
    }

    @Provides
    BaseActivity provideActivity(){
        return mAuthActivity;
    }
}
