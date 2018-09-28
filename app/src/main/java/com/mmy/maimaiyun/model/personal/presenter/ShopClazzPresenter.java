package com.mmy.maimaiyun.model.personal.presenter;

import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.helper.VDataMakeHelper;
import com.mmy.maimaiyun.model.personal.view.ShopClazzView;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/20 0020 10:50
 * @描述 TODO
 */

public class ShopClazzPresenter extends BasePresenter<ShopClazzView> {
    private ShopClazzView mView;

    @Inject
    public ShopClazzPresenter(ShopClazzView view) {
        mView = view;
    }

    public void loadData(){
        mView.refreshList(VDataMakeHelper.createStrList(10));
    }
}
