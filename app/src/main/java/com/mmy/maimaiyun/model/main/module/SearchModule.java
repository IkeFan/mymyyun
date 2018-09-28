package com.mmy.maimaiyun.model.main.module;

import com.mmy.maimaiyun.model.main.view.SearchView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017-11-10 03-27-54
 * @描述 搜索module
 */
@Module
public class SearchModule{
    private SearchView mView;

    public SearchModule(SearchView view) {
        mView = view;
    }

    @Provides
    SearchView provideIView(){
        return mView;
    }
}
