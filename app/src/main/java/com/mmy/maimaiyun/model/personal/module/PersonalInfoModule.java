package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.model.personal.view.PersonalInfoView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/10/18 0018 16:40
 * @描述 TODO
 */
@Module
public class PersonalInfoModule {
    private PersonalInfoView mView;
    private BaseActivity mActivity;

    public PersonalInfoModule(PersonalInfoView view, BaseActivity activity) {
        mView = view;
        mActivity = activity;
    }

    @Provides
    BaseActivity provideActivity(){
        return mActivity;
    }

    @Provides
    PersonalInfoView provideView(){
        return mView;
    }
}
