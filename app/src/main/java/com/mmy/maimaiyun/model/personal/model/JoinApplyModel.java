package com.mmy.maimaiyun.model.personal.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/10/19 0019 11:42
 * @描述 TODO
 */

public class JoinApplyModel extends BaseModel {
    @Inject
    public JoinApplyModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {

    }

    public void pushData(OnLoadDataListener listener, List<MultipartBody.Part> parts){
        Call<ResponseBody> call = mApi.Injoin(parts);
        wrapRequest(listener,call);
    }
}
