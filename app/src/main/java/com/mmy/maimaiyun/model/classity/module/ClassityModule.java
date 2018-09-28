package com.mmy.maimaiyun.model.classity.module;

import com.mmy.maimaiyun.model.classity.view.ClassityView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/10/24 0024 16:21
 * @描述 TODO
 */
@Module
public class ClassityModule {
    private ClassityView mView;

    public ClassityModule(ClassityView view) {
        mView = view;
    }

    @Provides
    ClassityView provideView(){
        return mView;
    }
}
