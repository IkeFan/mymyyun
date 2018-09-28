package com.mmy.maimaiyun.model.shopping.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * @创建者 lucas
 * @创建时间 2017/9/30 0030 16:11
 * @描述 TODO
 */
public class ShoppingModel extends BaseModel {

    @Inject
    public ShoppingModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {

    }

    public void getListData(OnLoadDataListener listener,String token,String member_id){
        Call<ResponseBody> call = mApi.getShopCarList(token, member_id);
        wrapRequest(listener,call);
    }

    public void deleteShop(OnLoadDataListener listener,String token,String ids){
        Call<ResponseBody> call = mApi.deleteShopCar(token, ids);
        wrapRequest(listener,call);
    }

    public void changeShopCount(OnLoadDataListener listener,String token,String id,int count){
        Call<ResponseBody> call = mApi.changeShopCount(token, id, count);
        wrapRequest(listener,call);
    }
}
