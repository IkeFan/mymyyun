package com.mmy.maimaiyun.model.personal.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/10/7 0007 14:24
 * @描述 TODO
 */

public class AddressModifyModel extends BaseModel {

    @Inject
    public AddressModifyModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {
        Call<ResponseBody> call = mApi.modifAddress(
                data.get("token"),
                data.get("address_id"),
                data.get("member_id"),
                data.get("consignee"),
                data.get("mobile"),
                data.get("province"),
                data.get("city"),
                data.get("district"),
                data.get("street"),
                data.get("address"),
                data.get("is_default")
        );
        wrapRequest(listener,call);
    }

    public void delAddress(OnLoadDataListener listener, String token, String member_id, String address_id){
        Call<ResponseBody> call = mApi.delAddress(token, member_id, address_id);
        wrapRequest(listener,call);
    }
}
