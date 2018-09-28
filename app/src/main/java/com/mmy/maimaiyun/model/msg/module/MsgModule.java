package com.mmy.maimaiyun.model.msg.module;

import com.mmy.maimaiyun.model.msg.view.MsgView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/18 0018 10:45
 * @描述 TODO
 */
@Module
public class MsgModule {
    private MsgView mView;

    public MsgModule(MsgView view) {
        mView = view;
    }

    @Provides
    MsgView provideView(){
        return mView;
    }
}
