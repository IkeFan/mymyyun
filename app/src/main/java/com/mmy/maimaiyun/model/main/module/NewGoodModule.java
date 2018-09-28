package com.mmy.maimaiyun.model.main.module;

import com.mmy.maimaiyun.model.main.view.NewGoodView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2018/1/18 0018 17:34
 * @描述 TODO
 */
@Module
public class NewGoodModule {
    private NewGoodView mView;

    public NewGoodModule(NewGoodView view) {
        mView = view;
    }

    @Provides
    NewGoodView provideView(){
        return mView;
    }
}
