package com.mmy.maimaiyun.model.personal.presenter;

import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.model.personal.view.OrderInfoView;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/10/11 0011 16:28
 * @描述 TODO
 */

public class OrderInfoPresenter extends BasePresenter<OrderInfoView> {
    private OrderInfoView mView;

    @Inject
    public OrderInfoPresenter(OrderInfoView view) {
        mView = view;
    }
}
