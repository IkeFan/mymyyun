package com.mmy.maimaiyun.model.msg.module;

import com.mmy.maimaiyun.model.msg.view.NotifyListView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/18 0018 13:50
 * @描述 TODO
 */
@Module
public class NotifyListModule {
    private NotifyListView mView;

    public NotifyListModule(NotifyListView view) {
        mView = view;
    }

    @Provides
    NotifyListView provideView(){
        return mView;
    }
}
