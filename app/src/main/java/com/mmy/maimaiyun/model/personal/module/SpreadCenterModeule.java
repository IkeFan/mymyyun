package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.SpreadCenterView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/11/16 0016 16:27
 * @描述
 */
@Module
public class SpreadCenterModeule {
    private SpreadCenterView mView;

    public SpreadCenterModeule(SpreadCenterView view) {
        mView = view;
    }

    @Provides
    SpreadCenterView provideView(){
        return mView;
    }
}
