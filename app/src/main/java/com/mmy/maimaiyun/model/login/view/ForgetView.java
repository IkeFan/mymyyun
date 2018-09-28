package com.mmy.maimaiyun.model.login.view;

import com.mmy.maimaiyun.base.mvp.IView;


/**
 * @创建者 lucas
 * @创建时间 2017/8/24 0024 11:33
 * @描述 TODO
 */

public interface ForgetView extends IView {
    void onSuccess(String msg);
    void onFailed(String msg);
}
