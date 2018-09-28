package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.AddressManagerView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/8 0008 10:13
 * @描述 TODO
 */
@Module
public class AddressManagerModule {
    private AddressManagerView mView;

    public AddressManagerModule(AddressManagerView view) {
        mView = view;
    }

    @Provides
    AddressManagerView provideView(){
        return mView;
    }
}
