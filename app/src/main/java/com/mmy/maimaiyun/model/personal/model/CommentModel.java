package com.mmy.maimaiyun.model.personal.model;


import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.List;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * @创建者 lucas
 * @创建时间 2017/12/6 0006 11:52
 * @描述 TODO
 */

public class CommentModel extends BaseModel {
    @Inject
    public CommentModel() {
    }

    public void goodComment(OnLoadDataListener listener, List<MultipartBody.Part> data){
        Call<ResponseBody> call = mApi.goodComment(data);
        wrapRequest(listener,call);
    }
}
