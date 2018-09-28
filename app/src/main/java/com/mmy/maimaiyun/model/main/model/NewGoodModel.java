package com.mmy.maimaiyun.model.main.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2018/1/18 0018 17:34
 * @描述 TODO
 */

public class NewGoodModel extends BaseModel {
    @Inject
    public NewGoodModel() {
    }

    public void getNewGoodData(OnLoadDataListener listener,int page){
        wrapRequest(listener,mApi.getNewGoods("is_new",page));
    }
}
