package com.mmy.maimaiyun.model.main.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/10/19 0019 16:19
 * @描述
 */

public class MainModel extends BaseModel {
    @Inject
    public MainModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {

    }

    public void getVersion(OnLoadDataListener listener, String token){
        Call<ResponseBody> call = mApi.getVersion(token);
        wrapRequest(listener,call);
    }

    //更新用户信息
    public void getUserInfo(OnLoadDataListener listener, String member_id){
        Call<ResponseBody> info = mApi.refreshUserInfo(member_id);
        wrapRequest(listener,info);
    }
}
