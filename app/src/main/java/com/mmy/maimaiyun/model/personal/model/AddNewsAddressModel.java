package com.mmy.maimaiyun.model.personal.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/10/6 0006 17:00
 * @描述 TODO
 */

public class AddNewsAddressModel extends BaseModel {
    @Inject
    public AddNewsAddressModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {
        Call<ResponseBody> call = mApi.addNewsAddress(
                data.get("token"),
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
}
