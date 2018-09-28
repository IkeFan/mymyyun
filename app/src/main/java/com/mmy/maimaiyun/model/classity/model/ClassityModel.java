package com.mmy.maimaiyun.model.classity.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/10/24 0024 16:22
 * @描述 TODO
 */

public class ClassityModel extends BaseModel {
    @Inject
    public ClassityModel() {
    }

    public void loadLeftData(OnLoadDataListener listener, String token){
        Call<ResponseBody> call = mApi.classs(token);
        wrapRequest(listener,call);
    }

    public void loadRightData(OnLoadDataListener listener, String token , String id){
        Call<ResponseBody> call = mApi.getClassInfo(token, id);
        wrapRequest(listener,call);
    }
}
