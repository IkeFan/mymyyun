package com.mmy.maimaiyun.model.shopping.view;

import com.mmy.maimaiyun.base.mvp.IView;


/**
 * @创建者 lucas
 * @创建时间 2017/10/14 0014 10:29
 * @描述 TODO
 */

public interface PayView extends IView {
    void refreshView(String msg);
    void getOrderFail();

    void onResult(int fail);
}
