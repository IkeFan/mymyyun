package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.model.personal.view.AddNewsAddressView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/10/6 0006 16:59
 * @描述 TODO
 */
@Module
public class AddNewsAddressModule {
    private AddNewsAddressView mView;
    private BaseActivity mActivity;

    public AddNewsAddressModule(AddNewsAddressView view, BaseActivity activity) {
        mView = view;
        mActivity = activity;
    }

    @Provides
    BaseActivity provideActivity(){
        return mActivity;
    }

    @Provides
    AddNewsAddressView provideView(){
        return mView;
    }


}
