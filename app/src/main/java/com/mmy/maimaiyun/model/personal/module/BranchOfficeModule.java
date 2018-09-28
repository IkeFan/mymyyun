package com.mmy.maimaiyun.model.personal.module;


import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.model.personal.view.BranchOfficeView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/10/25 0025 17:58
 * @描述
 */
@Module
public class BranchOfficeModule {
    private BranchOfficeView mView;
    private BaseActivity mActivity;

    public BranchOfficeModule(BranchOfficeView view, BaseActivity activity) {
        mView = view;
        mActivity = activity;
    }

    @Provides
    BaseActivity provideActivity(){
        return mActivity;
    }

    @Provides
    BranchOfficeView provideView(){
        return mView;
    }
}

