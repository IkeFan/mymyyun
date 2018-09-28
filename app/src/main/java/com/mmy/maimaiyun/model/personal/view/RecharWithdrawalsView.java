package com.mmy.maimaiyun.model.personal.view;

import com.mmy.maimaiyun.base.mvp.IView;


/**
 * @创建者 lucas
 * @创建时间 2017/11/17 0017 16:03
 * @描述
 */

public interface RecharWithdrawalsView extends IView {
    void refreshUserInfo();
    void getOrderFail();

    void onResult(int fail);
}
