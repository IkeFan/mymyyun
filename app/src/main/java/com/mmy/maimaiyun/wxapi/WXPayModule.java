package com.mmy.maimaiyun.wxapi;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/10/16 0016 10:44
 * @描述 TODO
 */
@Module
public class WXPayModule {
    private WXPayView mView;

    public WXPayModule(WXPayView view) {
        mView = view;
    }
    @Provides
    WXPayView provideView(){
        return mView;
    }
}
