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
 * @创建时间 2017/12/5 0005 17:54
 * @描述 TODO
 */

public class RefundSubmitModel extends BaseModel {
    @Inject
    public RefundSubmitModel() {
    }

    public void refund(OnLoadDataListener listener, List<MultipartBody.Part> data){
        Call<ResponseBody> call = mApi.refund(data);
        wrapRequest(listener,call);
    }
}
