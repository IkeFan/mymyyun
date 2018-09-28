package com.mmy.maimaiyun.model.personal.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.OrderBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/7 0007 18:03
 * @描述 TODO
 */

public interface OrderView extends IView {
    void refreshOrderList(List<OrderBean> data);
    void deleteOrderSuccess();
}
