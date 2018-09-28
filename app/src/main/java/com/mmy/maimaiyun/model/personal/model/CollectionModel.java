package com.mmy.maimaiyun.model.personal.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/9/8 0008 13:50
 * @描述 TODO
 */

public class CollectionModel extends BaseModel {

    @Inject
    public CollectionModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {

    }

    public void loadList(OnLoadDataListener listener, String token, String member_id){
        Call<ResponseBody> call = mApi.getCollectList(token, member_id);
        wrapRequest(listener,call);
    }

    public void delCollect(OnLoadDataListener listener, String token, String ids){
        Call<ResponseBody> call = mApi.delCollect(token, ids);
        wrapRequest(listener,call);
    }
}
