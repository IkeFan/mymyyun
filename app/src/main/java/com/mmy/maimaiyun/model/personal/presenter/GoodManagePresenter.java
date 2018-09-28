package com.mmy.maimaiyun.model.personal.presenter;

import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.helper.VDataMakeHelper;
import com.mmy.maimaiyun.model.personal.view.GoodManageView;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/16 0016 15:14
 * @描述 TODO
 */

public class GoodManagePresenter extends BasePresenter<GoodManageView> {
    private GoodManageView mView;

    @Inject
    public GoodManagePresenter(GoodManageView view) {
        mView = view;
    }

    public void loadData(){
        mView.refreshList(VDataMakeHelper.createStrList(10));
    }

}
