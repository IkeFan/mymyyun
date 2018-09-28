package com.mmy.maimaiyun.model.login.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/11/2 0002 10:48
 * @描述
 */

public class BindPhoneModel extends BaseModel {
    @Inject
    public BindPhoneModel() {
    }

    public void bindPhone(OnLoadDataListener listener, String phone, String code, String headimg, String openid, String nickname){
        Call<ResponseBody> call = mApi.bindPhone(headimg, openid, nickname, phone, code);
        wrapRequest(listener,call);
    }
}
