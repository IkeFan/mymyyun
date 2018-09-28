package com.mmy.maimaiyun.model.personal.presenter;

import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.helper.VDataMakeHelper;
import com.mmy.maimaiyun.model.personal.view.TransactionManagerView;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/21 0021 17:47
 * @描述 TODO
 */

public class TransactionManagerPresenter extends BasePresenter<TransactionManagerView> {
    private TransactionManagerView mView;

    @Inject
    public TransactionManagerPresenter(TransactionManagerView view) {
        mView = view;
    }

    public void loadData(){
        mView.refresh(VDataMakeHelper.createStrList(10));
    }
}
