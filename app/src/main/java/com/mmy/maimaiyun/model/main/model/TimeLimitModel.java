package com.mmy.maimaiyun.model.main.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/10/12 0012 15:21
 * @描述
 */

public class TimeLimitModel extends BaseModel {
    @Inject
    public TimeLimitModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {

    }

    public void loadData(OnLoadDataListener listener, String token,int type ){
        Call<ResponseBody> call = mApi.getTimeLimitData(token, type);
        wrapRequest(listener,call);
    }
}
