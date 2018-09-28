package com.mmy.maimaiyun.model.personal.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2018/1/22 0022 15:06
 * @描述 TODO
 */

public class RefundListModel extends BaseModel {
    @Inject
    public RefundListModel() {
    }


    public void getList(OnLoadDataListener listener,String member_id,int page,String token){
        wrapRequest(listener,mApi.getRefundList(member_id,page,token));
    }
}
