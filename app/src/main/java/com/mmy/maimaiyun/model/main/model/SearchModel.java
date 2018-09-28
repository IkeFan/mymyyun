package com.mmy.maimaiyun.model.main.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017-11-10 03-27-54
 * @描述 搜索数据模型
 */

public class SearchModel extends BaseModel {

    @Inject
    public SearchModel() {
    }

    public void search(OnLoadDataListener listener, String search_name, int page, int count,int type) {
        Call<ResponseBody> call = mApi.search(search_name, page, count,type);
        wrapRequest(listener, call);
    }
}
