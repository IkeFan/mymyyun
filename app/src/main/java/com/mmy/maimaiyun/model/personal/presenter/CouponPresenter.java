package com.mmy.maimaiyun.model.personal.presenter;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.CouponBean;
import com.mmy.maimaiyun.data.bean.CouponListBean;
import com.mmy.maimaiyun.model.personal.model.CouponModel;
import com.mmy.maimaiyun.model.personal.view.CouponView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/13 0013 14:32
 * @描述 TODO
 */

public class CouponPresenter extends BasePresenter<CouponView> {
    private CouponView mView;
    @Inject
    CouponModel mModel;

    @Inject
    public CouponPresenter(CouponView view) {
        mView = view;
    }

    public void loadData() {
        mModel.getCouponList(new OnLoadDataListener<BaseBean<List<CouponBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<CouponBean>> data) {
                ArrayList<CouponBean> list = new ArrayList<>();
                ArrayList<CouponBean> list2 = new ArrayList<>();
                ArrayList<CouponBean> list3 = new ArrayList<>();
                long currentTime = System.currentTimeMillis() / 1000;
                for (CouponBean bean : data.data) {
                    //是否到可使用时间
                    if (bean.use_start_time > currentTime)
                        continue;
                    //未使用
                    if (bean.status == 1 && currentTime < bean.use_end_time) {
                        bean.isUse = false;
                        list.add(bean);
                    }
                    //已使用
                    if (bean.status == 2 && currentTime < bean.use_end_time) {
                        bean.isUse = true;
                        list2.add(bean);
                    }
                    //已过期
                    if (currentTime > bean.use_end_time) {
                        bean.isOverdue = true;
                        list3.add(bean);
                    }
                }
                CouponListBean bean = new CouponListBean(list, list2, list3);
                mView.refreshList(bean);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<CouponBean>>>() {
                }.getType();
            }
        }, mView.getUserBean().token, mView.getUserBean().member_id);
    }
}
