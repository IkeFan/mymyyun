package com.mmy.maimaiyun.model.personal.module;

import com.mmy.maimaiyun.model.personal.view.OrderView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/9/7 0007 18:02
 * @描述 TODO
 */
@Module
public class OrderModule {
    private OrderView mView;

    public OrderModule(OrderView view) {
        mView = view;
    }

    @Provides
    OrderView provideView(){
        return mView;
    }
}
