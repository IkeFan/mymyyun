package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.RecharWithdrawalsView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/11/17 0017 16:04
 * @描述
 */
@Module
public class RecharWithdrawalsModule {
    private RecharWithdrawalsView mView;

    public RecharWithdrawalsModule(RecharWithdrawalsView view) {
        mView = view;
    }

    @Provides
    RecharWithdrawalsView provideView(){
        return mView;
    }
}
