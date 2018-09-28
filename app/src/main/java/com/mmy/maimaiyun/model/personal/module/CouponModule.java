package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.CouponView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/13 0013 14:33
 * @描述 TODO
 */
@Module
public class CouponModule {
    private CouponView mView;

    public CouponModule(CouponView view) {
        mView = view;
    }

    @Provides
    CouponView provideView(){
        return mView;
    }
}
