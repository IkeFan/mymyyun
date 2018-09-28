package com.mmy.maimaiyun.model.personal.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.RefundListBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 11:05
 * @描述 TODO
 */

public interface RefundListView extends IView {
    void refreshList(List<RefundListBean> data, boolean isLoadMore);
}
