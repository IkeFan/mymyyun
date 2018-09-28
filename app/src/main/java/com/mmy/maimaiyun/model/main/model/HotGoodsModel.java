package com.mmy.maimaiyun.model.main.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/9/2 0002 10:00
 * @描述 TODO
 */

public class HotGoodsModel extends BaseModel {
    @Inject
    public HotGoodsModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {
        Call<ResponseBody> call = mApi.getBanner(data.get("token"));
        wrapRequest(listener,call);
    }

    public void loadGoodHotData(OnLoadDataListener listener,String token,int order,int pageNum,
                                int limit,HashMap<String,String> attrs){
        Call<ResponseBody> cal = mApi.getGoodsNot(token,order,pageNum,limit,attrs);
        wrapRequest(listener,cal);
    }


    public void loadGoodHotRecommemd(OnLoadDataListener listener,String token){
        Call<ResponseBody> call = mApi.getGoodsHotRecommemd(token);
        wrapRequest(listener,call);
    }
    
}
