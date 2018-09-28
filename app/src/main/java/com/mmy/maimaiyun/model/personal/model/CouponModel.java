package com.mmy.maimaiyun.model.personal.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/11/6 0006 10:16
 * @描述
 */

public class CouponModel extends BaseModel {
    @Inject
    public CouponModel() {
    }

    public void getCouponList(OnLoadDataListener listener, String token, String member_id){
        Call<ResponseBody> call = mApi.getCouponList(token, member_id);
        wrapRequest(listener,call);
    }
}
