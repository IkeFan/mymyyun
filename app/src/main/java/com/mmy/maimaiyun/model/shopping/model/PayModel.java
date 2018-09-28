package com.mmy.maimaiyun.model.shopping.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/10/14 0014 10:31
 * @描述 TODO
 */

public class PayModel extends BaseModel {
    @Inject
    public PayModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {

    }

    public void loadWxPayInfo(OnLoadDataListener listener, String token, String order_sn, String order_amount){
        Call<ResponseBody> call = mApi.getWXPayInfo(token, order_sn, order_amount);
        wrapRequest(listener,call);
    }

    public void loadAliPayInfo(OnLoadDataListener listener, String token, String order_sn, String order_amount){
        Call<ResponseBody> call = mApi.getAliPayInfo(token, order_sn, order_amount);
        wrapRequest(listener,call);
    }

    public void loadBalancePayInfo(OnLoadDataListener listener,String token,String member_id,String order_sn,String order_amount){
        Call<ResponseBody> call = mApi.getBalancePayInfo(token, member_id, order_sn, order_amount);
        wrapRequest(listener,call);
    }
}
