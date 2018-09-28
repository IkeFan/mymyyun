package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.BankCarView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/18 0018 15:44
 * @描述 TODO
 */
@Module
public class BankCarModule {
    private BankCarView mView;

    public BankCarModule(BankCarView view) {
        mView = view;
    }

    @Provides
    BankCarView provideView(){
        return mView;
    }
}
