package com.mmy.maimaiyun.model.personal.module;


import com.mmy.maimaiyun.model.personal.view.RefundSubmitView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/12/5 0005 17:53
 * @描述 TODO
 */
@Module
public class RefundSubmitModule {
    private RefundSubmitView mView;

    public RefundSubmitModule(RefundSubmitView view) {
        mView = view;
    }

    @Provides
    RefundSubmitView provideView(){
        return mView;
    }
}
