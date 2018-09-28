package com.mmy.maimaiyun.model.personal.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/10/12 0012 11:25
 * @描述 TODO
 */

public class TradeRecordModel extends BaseModel {

    @Inject
    public TradeRecordModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {
    }

    public void loadBillData(OnLoadDataListener listener, String token,int page, String member_id){
        Call<ResponseBody> call = mApi.getBill(token,page, member_id);
        wrapRequest(listener,call);
    }
}
