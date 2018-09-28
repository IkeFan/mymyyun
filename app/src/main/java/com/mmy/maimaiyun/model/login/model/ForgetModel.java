package com.mmy.maimaiyun.model.login.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/8/25 0025 17:10
 * @描述 TODO
 */

public class ForgetModel extends BaseModel {

    @Inject
    public ForgetModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {
        Call<ResponseBody> call = mApi.forget(data.get("phone"), data.get("pwd"), data.get("code"));
        wrapRequest(listener,call);
    }
}
