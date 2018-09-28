package com.mmy.maimaiyun.model.personal.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/11/16 0016 16:28
 * @描述
 */

public class SpreadCenterModel extends BaseModel {
    @Inject
    public SpreadCenterModel() {
    }

    public void getDistOrder(OnLoadDataListener listener, String token , String member_id,int page,int type){
        Call<ResponseBody> call = mApi.getDistOrder(token, member_id,page,type);
        wrapRequest(listener,call);
    }
}
