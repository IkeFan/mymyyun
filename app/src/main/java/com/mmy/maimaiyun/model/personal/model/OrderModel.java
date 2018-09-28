package com.mmy.maimaiyun.model.personal.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/9/7 0007 18:04
 * @描述 TODO
 */

public class OrderModel extends BaseModel {
    @Inject
    public OrderModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {

    }

    public void loadData(OnLoadDataListener listener, String token, String member_id){
        Call<ResponseBody> call = mApi.getOrderList(token, member_id);
        wrapRequest(listener,call);
    }

    public void delOrder(OnLoadDataListener listener, String token, String member_id, String order_sn, String action){
        Call<ResponseBody> call = mApi.delOrder(token, member_id, order_sn,action);
        wrapRequest(listener,call);
    }

    public void confirmOrder(OnLoadDataListener listener, String token, String member_id, String sn_order){
        Call<ResponseBody> call = mApi.confirmOrder(token,member_id, sn_order);
        wrapRequest(listener,call);
    }
}
