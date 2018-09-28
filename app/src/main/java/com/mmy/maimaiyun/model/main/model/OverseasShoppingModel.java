package com.mmy.maimaiyun.model.main.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/9/1 0001 16:16
 * @描述 TODO
 */

public class OverseasShoppingModel extends IActivityGoodsModel {
    @Override
    public String getActivityName() {
        return null;
    }

    @Inject
    public OverseasShoppingModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {
        Call<ResponseBody> call = mApi.getBanner(data.get("token"));
        wrapRequest(listener,call);
    }

    public void loadClass(OnLoadDataListener listener, String token){
        Call<ResponseBody> call = mApi.getOverseasGoodsClass(token);
        wrapRequest(listener,call);
    }

    public void loadGoods(OnLoadDataListener listener, String token, String cat_id){
        Call<ResponseBody> call = mApi.getOverseasGood(token, cat_id);
        wrapRequest(listener,call);
    }
}
