package com.mmy.maimaiyun.model.main.module;

import com.mmy.maimaiyun.model.main.view.AllCommentView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 11:48
 * @描述 TODO
 */
@Module
public class AllCommentModule {
    private AllCommentView mView;

    public AllCommentModule(AllCommentView view) {
        mView = view;
    }

    @Provides
    AllCommentView provideView(){
        return mView;
    }
}
