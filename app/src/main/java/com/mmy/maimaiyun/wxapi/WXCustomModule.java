package com.mmy.maimaiyun.wxapi;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/11/4 0004 10:37
 * @描述
 */
@Module
public class WXCustomModule {
    private WXCustomView mView;

    public WXCustomModule(WXCustomView view) {
        mView = view;
    }
    @Provides
    WXCustomView provideView(){
        return mView;
    }
}
