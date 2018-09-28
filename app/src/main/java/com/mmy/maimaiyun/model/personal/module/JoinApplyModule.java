package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.model.personal.view.JoinApplyView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/10/19 0019 11:41
 * @描述 TODO
 */
@Module
public class JoinApplyModule {
    private JoinApplyView mView;
    private BaseActivity mActivity;

    public JoinApplyModule(JoinApplyView view, BaseActivity activity) {
        mView = view;
        mActivity = activity;
    }

    @Provides
    BaseActivity provideActivity(){
        return mActivity;
    }

    @Provides
    JoinApplyView provideView(){
        return mView;
    }
}
