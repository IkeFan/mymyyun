package com.mmy.maimaiyun.model.main.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2018/1/30 0030 14:08
 * @描述 TODO
 */

public class ShopModel extends BaseModel {
    @Inject
    public ShopModel() {
    }

    public void getShopData(OnLoadDataListener listener,String shop_id,int page){
        wrapRequest(listener,mApi.getMainShop(shop_id, page));
    }
}
