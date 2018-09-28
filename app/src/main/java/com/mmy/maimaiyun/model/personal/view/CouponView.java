package com.mmy.maimaiyun.model.personal.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.CouponListBean;


/**
 * @创建者 lucas
 * @创建时间 2017/9/13 0013 14:31
 * @描述 TODO
 */

public interface CouponView extends IView {
    void refreshList(CouponListBean data);
}
