package com.mmy.maimaiyun.model.login.view;

import com.mmy.maimaiyun.base.mvp.IView;


/**
 * @创建者 lucas
 * @创建时间 2017/8/24 0024 10:18
 * @描述 TODO
 */

public interface RegisterView extends IView {
    void onRegisterSuccess();
    void onRegisterFailed(String msg);
}
