package com.mmy.maimaiyun.model.personal.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/11/1 0001 15:17
 * @描述
 */

public class FeedbackModel extends BaseModel {
    @Inject
    public FeedbackModel() {
    }

    public void feedback(OnLoadDataListener listener,String token,String member_id,String content){
        Call<ResponseBody> call = mApi.feedback(token, member_id, content);
        wrapRequest(listener,call);
    }
}
