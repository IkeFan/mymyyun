package com.mmy.maimaiyun.model.personal.presenter;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.SpreadCenterBean;
import com.mmy.maimaiyun.model.personal.model.SpreadCenterModel;
import com.mmy.maimaiyun.model.personal.view.SpreadCenterView;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/11/16 0016 16:24
 * @描述
 */

public class SpreadCenterPresenter extends BasePresenter<SpreadCenterView> {
    private SpreadCenterView mView;
    @Inject
    SpreadCenterModel mModel;
    private SpreadCenterBean mData;

    @Inject
    public SpreadCenterPresenter(SpreadCenterView view) {
        mView = view;
    }

    int page = 0;

    public void loadData(boolean isLoadMore, int type) {
        if (isLoadMore)
            page++;
        else
            page = 0;
        mModel.getDistOrder(new OnLoadDataListener<BaseBean<SpreadCenterBean>>() {
            @Override
            public void onResponse(String body, BaseBean<SpreadCenterBean> data, IBean iBean) {
                super.onResponse(body, data, iBean);
                mView.hideLoading();
                if (iBean.status == 1) {
                    mData = data.data;
                    mView.refreshView(mData, isLoadMore);
                    mView.refreshList(mData.info, isLoadMore);
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {
                mView.hideLoading();
            }

            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<SpreadCenterBean>>() {
                }.getType();
            }
        }, mView.getUserBean().token, mView.getUserBean().member_id, page, type);
    }

}
