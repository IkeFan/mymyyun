package com.mmy.maimaiyun.model.login.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * @创建者 lucas
 * @创建时间 2017/8/25 0025 9:20
 * @描述 TODO
 */

public class RegisterModel extends BaseModel {
    @Inject
    public RegisterModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {
        Call<ResponseBody> call = mApi.sendCode(data.get("phone"));
        wrapRequest(listener,call);
    }

    public void sendCode(OnLoadDataListener listener, String phone){
        Call<ResponseBody> call = mApi.sendCode(phone);
        wrapRequest(listener,call);
    }

    public void register(OnLoadDataListener listener, String phone, String pwd, String code, String type, String invite_code){
        Call<ResponseBody> call = mApi.register(phone,pwd,code,type,invite_code,1);
        wrapRequest(listener,call);
    }
}
