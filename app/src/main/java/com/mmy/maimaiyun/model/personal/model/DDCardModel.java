package com.mmy.maimaiyun.model.personal.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/12/14 0014 14:31
 * @描述 TODO
 */

public class DDCardModel extends BaseModel {
    @Inject
    public DDCardModel() {
    }

    public void getDDCardData(OnLoadDataListener listener,String token,String member_id){
        Call<ResponseBody> call = mApi.getDDCard(token, member_id);
        wrapRequest(listener,call);
    }
}
