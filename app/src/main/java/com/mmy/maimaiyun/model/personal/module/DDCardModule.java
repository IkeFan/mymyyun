package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.DDCardView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/12/14 0014 14:30
 * @描述 TODO
 */
@Module
public class DDCardModule {
    private DDCardView mView;

    public DDCardModule(DDCardView view) {
        mView = view;
    }

    @Provides
    DDCardView provideView(){
        return mView;
    }
}
