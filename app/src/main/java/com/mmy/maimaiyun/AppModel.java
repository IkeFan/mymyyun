package com.mmy.maimaiyun;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.Map;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.HeaderMap;


/**
 * @创建者 lucas
 * @创建时间 2017/11/18 0018 11:22
 * @描述
 */

public class AppModel extends BaseModel {
    @Inject
    public AppModel() {
    }

    public void getUserMsgToken(OnLoadDataListener listener, @HeaderMap Map<String,String> headers, String userId , String name, String portraitUri){
        Call<ResponseBody> call = mApi.getMsgUserToken(headers,userId, name, portraitUri);
        wrapRequest(listener,call);
    }
}
