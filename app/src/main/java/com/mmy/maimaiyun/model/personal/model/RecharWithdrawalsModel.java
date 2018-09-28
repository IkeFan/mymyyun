package com.mmy.maimaiyun.model.personal.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/11/17 0017 16:05
 * @描述
 */

public class RecharWithdrawalsModel extends BaseModel {
    @Inject
    public RecharWithdrawalsModel() {
    }

    public void withdraw(OnLoadDataListener listener, String token, String member_id, String bank_id, String money){
        Call<ResponseBody> call = mApi.withdraw(token, member_id, bank_id, money);
        wrapRequest(listener,call);
    }

    public void rechargeMember(OnLoadDataListener listener,String token,String member_id,String money,String type){
        wrapRequest(listener,mApi.rechargeMember(token,member_id,money,type));
    }
}
