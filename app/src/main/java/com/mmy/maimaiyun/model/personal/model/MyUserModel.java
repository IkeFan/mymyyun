package com.mmy.maimaiyun.model.personal.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/11/6 0006 18:05
 * @描述
 */

public class MyUserModel extends BaseModel {
    @Inject
    public MyUserModel() {
    }

    public void getMyUser(OnLoadDataListener listener, String token, String member_id, int level){
        Call<ResponseBody> call = mApi.getMyUser(token, member_id,level);
        wrapRequest(listener,call);
    }
}
