package com.mmy.maimaiyun.model.personal.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/9/20 0020 9:53
 * @描述 TODO
 */

public class ShopModel extends BaseModel {
    @Inject
    public ShopModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {
        Call<ResponseBody> call = mApi.getBanner(data.get("token"));
        wrapRequest(listener,call);
    }
}
