package com.mmy.maimaiyun.model.main.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/9/1 0001 11:18
 * @描述 TODO
 */

public class GoodsModel extends BaseModel {

    @Inject
    public GoodsModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {

    }

    public void loadClazzData(OnLoadDataListener listener, String token){
        Call<ResponseBody> call = mApi.getGoodsBestact(token);
        wrapRequest(listener,call);
    }

    public void loadGoodsBest(OnLoadDataListener listener, String token, int cat_id, int order, HashMap<String,String> attrs){
        Call<ResponseBody> call = mApi.getGoodsBest(token, cat_id,order,attrs);
        wrapRequest(listener,call);
    }
}
