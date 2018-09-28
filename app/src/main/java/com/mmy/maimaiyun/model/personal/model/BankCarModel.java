package com.mmy.maimaiyun.model.personal.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/11/15 0015 15:19
 * @描述
 */

public class BankCarModel extends BaseModel {
    @Inject
    public BankCarModel() {
    }

    public void getBankList(OnLoadDataListener listener, String token, String member_id){
        Call<ResponseBody> call = mApi.getBankList(token, member_id);
        wrapRequest(listener,call);
    }

    public void delBankCard(OnLoadDataListener listener,String token,String id,String member_id){
        Call<ResponseBody> call = mApi.delBankCard(token, id, member_id);
        wrapRequest(listener,call);
    }

    public void addBankCard(OnLoadDataListener listener,String token,String name,String id_card,
                            String deposit,String bank_card,String member_id){
        Call<ResponseBody> call = mApi.addBankCard(token, member_id, name, id_card, deposit, bank_card);
        wrapRequest(listener,call);
    }
}
