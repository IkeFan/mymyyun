package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.ResetPwdView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017-11-08 02-14-06
 * @描述 修改密码module
 */
@Module
public class ResetPwdModule{
    private ResetPwdView mView;

    public ResetPwdModule(ResetPwdView view) {
        mView = view;
    }

    @Provides
    ResetPwdView provideIView(){
        return mView;
    }
}
