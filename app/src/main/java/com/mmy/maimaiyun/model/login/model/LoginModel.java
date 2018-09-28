package com.mmy.maimaiyun.model.login.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/8/11 0011 18:26
 * @描述 TODO
 */

public class LoginModel extends BaseModel {

    @Inject
    public LoginModel() {
    }

    @Override
    public void pushData(final OnLoadDataListener listener, HashMap<String, String> data) {
        Call<ResponseBody> call = mApi.login(data.get("username"), data.get("pwd"));
        wrapRequest(listener, call);
    }

    public void thirdParty(OnLoadDataListener listener, String headimg, String openid, String nickname){
        Call<ResponseBody> call = mApi.thirdParty(headimg, openid, nickname);
        wrapRequest(listener,call);
    }
}
