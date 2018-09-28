package com.mmy.maimaiyun.model.personal.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/11/24 0024 16:20
 * @描述
 */

public class ShopManagerModel extends BaseModel {
    @Inject
    public ShopManagerModel() {
    }

    public void getShopInfo(OnLoadDataListener listener , String token, String member_id){
        Call<ResponseBody> call = mApi.getShopInfo(token, member_id);
        wrapRequest(listener,call);
    }
}
