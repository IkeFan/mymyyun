package com.mmy.maimaiyun.model.personal.view;

import com.mmy.maimaiyun.base.mvp.IView;


/**
 * @创建者 lucas
 * @创建时间 2017/10/6 0006 16:56
 * @描述 TODO
 */

public interface AddNewsAddressView extends IView {
    void success(String msg);
    void fail(String msg);
}
