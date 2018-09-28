package com.mmy.maimaiyun.model.main.module;

import com.mmy.maimaiyun.model.main.view.SpecialOfferView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/25 0025 15:33
 * @描述 TODO
 */
@Module
public class SpecialOfferModule {
    private SpecialOfferView mView;

    public SpecialOfferModule(SpecialOfferView view) {
        mView = view;
    }

    @Provides
    SpecialOfferView provideView(){
        return mView;
    }
}
