package com.mmy.maimaiyun.model.main.module;

import com.mmy.maimaiyun.model.main.view.OneOneView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/12/1 0001 15:25
 * @描述 TODO
 */
@Module
public class OneOneModule {
    private OneOneView mView;

    public OneOneModule(OneOneView view) {
        mView = view;
    }

    @Provides
    public OneOneView provideView(){
        return mView;
    }
}
