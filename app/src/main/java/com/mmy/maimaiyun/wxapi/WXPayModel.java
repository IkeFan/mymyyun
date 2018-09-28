package com.mmy.maimaiyun.wxapi;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/10/16 0016 10:45
 * @描述 TODO
 */

public class WXPayModel extends BaseModel {
    @Inject
    public WXPayModel() {
    }

    public void checkPayStatus(OnLoadDataListener listener, String token, String order_sn){
        Call<ResponseBody> call = mApi.checkPayStatus(token, order_sn);
        wrapRequest(listener,call);
    }

}
