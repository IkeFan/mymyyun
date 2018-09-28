package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.model.personal.view.AddressModifyView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/10/7 0007 14:23
 * @描述 TODO
 */
@Module
public class AddressModifyModule {
    private AddressModifyView mView;
    private BaseActivity mActivity;

    public AddressModifyModule(AddressModifyView view, BaseActivity activity) {
        mView = view;
        mActivity = activity;
    }

    @Provides
    BaseActivity provideActivity(){
        return mActivity;
    }

    @Provides
    AddressModifyView provideView(){
        return mView;
    }
}
