package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.TradeRecordView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/19 0019 11:46
 * @描述 TODO
 */
@Module
public class TradeRecordModule {
    private TradeRecordView mView;

    public TradeRecordModule(TradeRecordView view) {
        mView = view;
    }

    @Provides
    TradeRecordView provideView(){
        return mView;
    }
}
