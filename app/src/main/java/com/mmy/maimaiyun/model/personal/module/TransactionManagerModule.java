package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.TransactionManagerView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/21 0021 17:48
 * @描述 TODO
 */
@Module
public class TransactionManagerModule {
    private TransactionManagerView mView;

    public TransactionManagerModule(TransactionManagerView view) {
        mView = view;
    }

    @Provides
    TransactionManagerView provideView(){
        return mView;
    }
}
