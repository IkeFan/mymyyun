package com.mmy.maimaiyun.model.main.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/12/1 0001 15:26
 * @描述 TODO
 */

public class OneOneModel extends IActivityGoodsModel {


    @Override
    public String getActivityName() {
        return "一品一县";
    }

    @Inject
    public OneOneModel() {
    }

    public void getClazz(OnLoadDataListener listener){
        getActivityList(id -> {
            ActivityRequestBody body = new ActivityRequestBody();
            body.activityId = id;
            getActivityGoods(listener,body);
        });
    }

    public void getGoods(OnLoadDataListener listener,int cateId){
        getActivityList(id -> {
            ActivityRequestBody body = new ActivityRequestBody();
            body.activityId = id;
            body.activityCateId = cateId;
            body.isShowGoods = 1;
            getActivityGoods(listener,body);
        });
    }

}
