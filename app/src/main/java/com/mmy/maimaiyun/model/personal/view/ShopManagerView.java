package com.mmy.maimaiyun.model.personal.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.ShopInfoBean;


/**
 * @创建者 lucas
 * @创建时间 2017/11/24 0024 16:18
 * @描述
 */

public interface ShopManagerView extends IView {
    void refreshView(ShopInfoBean bean);
}
