package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.FeedbackView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/11/1 0001 15:16
 * @描述
 */
@Module
public class FeedbackModule {
    private FeedbackView mView;

    public FeedbackModule(FeedbackView view) {
        mView = view;
    }

    @Provides
    FeedbackView provideView(){
        return mView;
    }
}
