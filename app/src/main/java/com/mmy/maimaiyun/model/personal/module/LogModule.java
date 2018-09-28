package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.LogView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/12/30 0030 14:49
 * @描述 TODO
 */
@Module
public class LogModule {
    private LogView mView;

    public LogModule(LogView view) {
        mView = view;
    }

    @Provides
    LogView provideView(){
        return mView;
    }
}
