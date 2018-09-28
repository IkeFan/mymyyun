package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.CollectionView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/8 0008 13:48
 * @描述 TODO
 */
@Module
public class CollectionModule {
    private CollectionView mView;

    public CollectionModule(CollectionView view) {
        mView = view;
    }

    @Provides
    CollectionView provideView(){
        return mView;
    }
}
