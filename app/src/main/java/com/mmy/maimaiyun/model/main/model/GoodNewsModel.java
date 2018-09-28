package com.mmy.maimaiyun.model.main.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/9/4 0004 9:51
 * @描述 TODO
 */

public class GoodNewsModel extends BaseModel {
    @Inject
    public GoodNewsModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {

    }

    public void loadClass(OnLoadDataListener listener, String token){
        Call<ResponseBody> call = mApi.getGoodsNewsClass(token, "6");
        wrapRequest(listener,call);
    }

    public void loadList(OnLoadDataListener listener, String token, String cat_id){
        Call<ResponseBody> call = mApi.getGoodsNewsList(token, cat_id);
        wrapRequest(listener,call);
    }
}
