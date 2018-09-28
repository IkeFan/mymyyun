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
 * @创建时间 2017/10/18 0018 16:41
 * @描述 TODO
 */

public class PersonalInfoModel extends BaseModel {
    @Inject
    public PersonalInfoModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {
        Call<ResponseBody> call = mApi.midfMemberInfo(data);
        wrapRequest(listener,call);
    }

    public void uploadPic(OnLoadDataListener listener, List<MultipartBody.Part> data){
        Call<ResponseBody> call = mApi.midfHeadImg(data);
        wrapRequest(listener,call);
    }
}
