package com.mmy.maimaiyun.model.splash.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/10/30 0030 11:05
 * @描述
 */

public class SplashModel extends BaseModel {
    @Inject
    public SplashModel() {
    }

    public void getCitys(OnLoadDataListener listener){
        Call<ResponseBody> call = mApi.getCitys();
        wrapRequest(listener,call);
    }
}
