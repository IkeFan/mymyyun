package com.mmy.maimaiyun.model.personal.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/12/30 0030 14:50
 * @描述 TODO
 */

public class LogModel extends BaseModel {
    @Inject
    public LogModel() {
    }

    public void loadData(OnLoadDataListener listener,String customer,String sign,String param){
        Call<ResponseBody> call = mApi.getLogList(customer, sign,param);
        wrapRequest(listener,call);
    }
}
