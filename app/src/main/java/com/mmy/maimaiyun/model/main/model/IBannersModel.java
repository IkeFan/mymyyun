package com.mmy.maimaiyun.model.main.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/10/20 0020 11:37
 * @描述 TODO
 */

public class IBannersModel extends BaseModel {

    public enum BannerType {
        HOME(1),//主页
        HOTGOODS(5),//热卖
        OVERSEAS(6);//海外
        public int value;

        BannerType(int value) {
            this.value = value;
        }
    }

    @Inject
    public IBannersModel() {
    }


    public void  getBanners(OnLoadDataListener listener, HashMap<String,Object> map) {
        map.put("type",3);
        Call<ResponseBody> call = mApi.getBanners(map);
        wrapRequest(listener,call);
    }


}
