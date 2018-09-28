package com.mmy.maimaiyun.model.shopping.model;

import android.content.Intent;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/10/6 0006 15:12
 * @描述 TODO
 */

public class ConfOrderModel extends BaseModel {
    @Inject
    public ConfOrderModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {

    }

    //单个立即购买
    public void loadData(OnLoadDataListener liener, String token, String member_id,String address_id, Intent intent){
        String goods_id = intent.getStringExtra("goods_id");
        String goods_number = intent.getStringExtra("goods_number");
        String goods_attr_id = intent.getStringExtra("goods_attr_id");
        Call<ResponseBody> call = mApi.getOrderInfo(token, goods_id, goods_number,
                goods_attr_id, member_id,address_id);
        wrapRequest(liener,call);
    }

    //多个购买
    public void confOrders(OnLoadDataListener listener, String token, String member_id, String id,String address_id){
        Call<ResponseBody> call = mApi.getConfOrders(token, member_id,id,address_id);
        wrapRequest(listener,call);
    }

    public void getAddressData(OnLoadDataListener listener, String token, String member_id){
        Call<ResponseBody> call = mApi.getAddressList(token, member_id);
        wrapRequest(listener,call);
    }

    public void submitOrder(OnLoadDataListener listener, String dataJson){
        Call<ResponseBody> order = mApi.subOrder(dataJson);
        wrapRequest(listener,order);
    }
}
