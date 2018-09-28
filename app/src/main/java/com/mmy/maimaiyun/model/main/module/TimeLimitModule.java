package com.mmy.maimaiyun.model.main.module;

import com.mmy.maimaiyun.model.main.view.TimeLimitView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/25 0025 17:44
 * @描述 TODO
 */
@Module
public class TimeLimitModule {
    private TimeLimitView mView;

    public TimeLimitModule(TimeLimitView view) {
        mView = view;
    }

    @Provides
    TimeLimitView provideView(){
        return mView;
    }
}
