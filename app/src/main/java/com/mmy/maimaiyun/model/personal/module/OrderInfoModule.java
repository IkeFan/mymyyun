package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.OrderInfoView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/10/11 0011 16:29
 * @描述 TODO
 */
@Module
public class OrderInfoModule {
    private OrderInfoView mView;

    public OrderInfoModule(OrderInfoView view) {
        mView = view;
    }

    @Provides
    OrderInfoView provideView(){
        return mView;
    }
}
