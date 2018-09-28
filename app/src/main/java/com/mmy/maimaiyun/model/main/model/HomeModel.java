package com.mmy.maimaiyun.model.main.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * @创建者 lucas
 * @创建时间 2017/8/17 0017 16:18
 * @描述 TODO
 */

public class HomeModel extends BaseModel {
    @Inject
    public HomeModel() {
    }


    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {
        Call<ResponseBody> call = mApi.getBanner(data.get("token"));
        wrapRequest(listener, call);
    }

    public void loadNews(OnLoadDataListener listener, String token) {
        Call<ResponseBody> call = mApi.getNewsHead(token);
        wrapRequest(listener, call);
    }

    public void loadBest(OnLoadDataListener listener, String token) {
        wrapRequest(listener, mApi.getBest(token));
    }

    public void loadRecommend(OnLoadDataListener listener, String token,int page_count) {
        wrapRequest(listener, mApi.getRecommend(token,page_count));
    }

    public void loadHomeEvery(OnLoadDataListener listener){
        wrapRequest(listener,mApi.getHomeEvery());
    }

    public void loadHomeOnlyNew(OnLoadDataListener listener){
        wrapRequest(listener,mApi.getHomeOnlyNew());
    }
}
