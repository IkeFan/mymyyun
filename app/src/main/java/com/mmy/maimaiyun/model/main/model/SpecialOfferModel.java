package com.mmy.maimaiyun.model.main.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/10/11 0011 10:55
 * @描述 TODO
 */

public class SpecialOfferModel extends IActivityGoodsModel {

    private String mString = "天天特价";

    @Override
    public String getActivityName() {
        return mString;
    }

    public void resetActivityName(String name){
        mString = name;
    }

    @Inject
    public SpecialOfferModel() {
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
