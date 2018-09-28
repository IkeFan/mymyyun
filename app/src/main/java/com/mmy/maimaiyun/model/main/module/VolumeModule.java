package com.mmy.maimaiyun.model.main.module;

import com.mmy.maimaiyun.model.main.view.VolumeView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 15:25
 * @描述 TODO
 */
@Module
public class VolumeModule {
    private VolumeView mVolumeView;

    public VolumeModule(VolumeView volumeView) {
        mVolumeView = volumeView;
    }

    @Provides
    VolumeView provideView(){
        return mVolumeView;
    }
}
