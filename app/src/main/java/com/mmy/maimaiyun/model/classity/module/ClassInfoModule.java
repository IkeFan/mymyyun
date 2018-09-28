package com.mmy.maimaiyun.model.classity.module;

import com.mmy.maimaiyun.model.classity.view.ClassIInfoView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/10/14 0014 17:53
 * @描述 TODO
 */
@Module
public class ClassInfoModule {
    private ClassIInfoView mView;

    public ClassInfoModule(ClassIInfoView view) {
        mView = view;
    }

    @Provides
    ClassIInfoView provideView(){
        return mView;
    }

}
