package com.mmy.maimaiyun.model.login.module;

import com.mmy.maimaiyun.model.login.view.BindPhoneView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/11/2 0002 10:47
 * @描述
 */
@Module
public class BindPhoneModule {

    private BindPhoneView mView;

    public BindPhoneModule(BindPhoneView view) {
        mView = view;
    }

    @Provides
    BindPhoneView provideView(){
        return mView;
    }
}
