package com.mmy.maimaiyun.model.msg.presenter;

import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.helper.VDataMakeHelper;
import com.mmy.maimaiyun.model.msg.view.NotifyListView;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/18 0018 13:49
 * @描述 TODO
 */

public class NotifyListPresenter extends BasePresenter<NotifyListView> {
    private NotifyListView mView;

    @Inject
    public NotifyListPresenter(NotifyListView view) {
        mView = view;
    }

    public void loadData(){
        mView.refreshList(VDataMakeHelper.createStrList(10));
    }
}
