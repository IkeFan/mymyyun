package com.mmy.maimaiyun.model.main.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/10/14 0014 16:36
 * @描述 TODO
 */

public class VolumeModel extends IActivityGoodsModel {
    @Override
    public String getActivityName() {
        return null;
    }

    @Inject
    public VolumeModel() {
    }

    @Override
    public void load(OnLoadDataListener listener) {

    }

    @Override
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {

    }

    public void loadData(OnLoadDataListener listener, String token){
        Call<ResponseBody> call = mApi.getVolumeGastronomy(token, 55);
        wrapRequest(listener,call);
    }
}
