package com.mmy.maimaiyun.model.personal.module;


import com.mmy.maimaiyun.model.personal.view.CommentView;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/12/6 0006 11:51
 * @描述 TODO
 */
@Module
public class CommentModule {
    private CommentView mView;

    @Inject
    public CommentModule(CommentView view) {
        mView = view;
    }

    @Provides
    CommentView provideView(){
        return mView;
    }
}
