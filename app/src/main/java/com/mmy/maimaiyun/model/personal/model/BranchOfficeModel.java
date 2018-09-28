package com.mmy.maimaiyun.model.personal.model;


import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.List;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * @创建者 lucas
 * @创建时间 2017/10/25 0025 17:58
 * @描述
 */

public class BranchOfficeModel extends BaseModel {
    @Inject
    public BranchOfficeModel() {
    }

    public void subBranchOfficeData(OnLoadDataListener listener, List<MultipartBody.Part> data){
        Call<ResponseBody> call = mApi.subBranchOffice(data);
        wrapRequest(listener,call);
    }
}
