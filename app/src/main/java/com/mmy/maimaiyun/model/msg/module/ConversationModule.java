package com.mmy.maimaiyun.model.msg.module;

import com.mmy.maimaiyun.model.msg.view.ConversationView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/21 0021 16:38
 * @描述 TODO
 */
@Module
public class ConversationModule {
    private ConversationView mView;

    public ConversationModule(ConversationView view) {
        mView = view;
    }

    @Provides
    ConversationView provideView(){
        return mView;
    }
}
