package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.RefundListView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 11:06
 * @描述 TODO
 */
@Module
public class RefundListModule {
    private RefundListView mView;

    public RefundListModule(RefundListView view) {
        mView = view;
    }

    @Provides
    RefundListView provideView(){
        return mView;
    }
}
