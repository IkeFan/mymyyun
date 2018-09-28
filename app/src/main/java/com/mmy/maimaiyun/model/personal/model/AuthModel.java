package com.mmy.maimaiyun.model.personal.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.List;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import retrofit2.http.Part;

/**
 * @创建者 lucas
 * @创建时间 2018/1/31 0031 18:31
 * @描述 TODO
 */

public class AuthModel extends BaseModel {

    @Inject
    public AuthModel() {
    }

    public void auth(OnLoadDataListener listener, @Part List<MultipartBody.Part> data) {
        wrapRequest(listener,mApi.auth(data));
    }
}
