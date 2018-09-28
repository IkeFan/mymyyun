package com.mmy.maimaiyun.wxapi;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/11/4 0004 10:37
 * @描述
 */

public class WXCustomModel extends BaseModel {
    @Inject
    public WXCustomModel() {
    }

    public void getTokenOpenid(OnLoadDataListener listener, String code) {
        Call<ResponseBody> call = mApi.getTokenOpenid(code);
        wrapRequest(listener, call);
    }

    public void getWxUserInfo(OnLoadDataListener listener, String token, String openid){
        Call<ResponseBody> call = mApi.getWXUserInfo(token, openid);
        wrapRequest(listener,call);
    }

}
