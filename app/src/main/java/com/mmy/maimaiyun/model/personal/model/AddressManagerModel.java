package com.mmy.maimaiyun.model.personal.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/9/8 0008 10:14
 * @描述 TODO
 */

public class AddressManagerModel extends BaseModel {

    @Inject
    public AddressManagerModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {

    }

    public void getAddressListData(OnLoadDataListener listener,String token,String member_id){
        Call<ResponseBody> call = mApi.getAddressList(token, member_id);
        wrapRequest(listener,call);
    }

    public void mdifDefaultAddress(OnLoadDataListener listener,String toke,String member_id,String address_id){
        Call<ResponseBody> call = mApi.midfDefaultAddress(toke, member_id, address_id);
        wrapRequest(listener,call);
    }

    public void delAddress(OnLoadDataListener listener,String token,String member_id,String address_id){
        Call<ResponseBody> call = mApi.delAddress(token, member_id, address_id);
        wrapRequest(listener,call);
    }
}
