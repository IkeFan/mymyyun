package com.mmy.maimaiyun.model.shopping.module;

import com.mmy.maimaiyun.model.shopping.view.PayView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/10/14 0014 10:30
 * @描述 TODO
 */
@Module
public class PayModule {
    private PayView mView;

    public PayModule(PayView view) {
        mView = view;
    }

    @Provides
    PayView provideView(){
        return mView;
    }
}
