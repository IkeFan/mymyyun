package com.mmy.maimaiyun.model.personal.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.SpreadCenterBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/11/16 0016 16:24
 * @描述
 */

public interface SpreadCenterView extends IView {
    void refreshView(SpreadCenterBean data, boolean isLoadMore);

    void refreshList(List<SpreadCenterBean.InfoBean> list, boolean isLoadMore);
}
