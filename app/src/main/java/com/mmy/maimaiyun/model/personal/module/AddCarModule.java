package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.AddCarView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/11/15 0015 15:33
 * @描述
 */
@Module
public class AddCarModule {
    private AddCarView mView;

    public AddCarModule(AddCarView view) {
        mView = view;
    }
    @Provides
    AddCarView provideView(){
        return mView;
    }
}
