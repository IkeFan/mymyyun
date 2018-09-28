package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.BrowseHistoryView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 9:46
 * @描述 TODO
 */
@Module
public class BrowseHistoryModule {
    private BrowseHistoryView mView;

    public BrowseHistoryModule(BrowseHistoryView view) {
        mView = view;
    }

    @Provides
    BrowseHistoryView provideView(){
        return mView;
    }
}
