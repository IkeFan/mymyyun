package com.mmy.maimaiyun.model.classity.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/10/14 0014 17:55
 * @描述 TODO
 */

public class ClassInfoModel extends BaseModel {
    @Inject
    public ClassInfoModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {

    }

    public void loadData(OnLoadDataListener listener, String token, String cat_id){
        Call<ResponseBody> call = mApi.getClassityInfo(token, cat_id);
        wrapRequest(listener,call);
    }
}
