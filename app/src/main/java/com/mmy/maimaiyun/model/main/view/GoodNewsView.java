package com.mmy.maimaiyun.model.main.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.GoodNewsClassBean;
import com.mmy.maimaiyun.data.bean.GoodNewsItemBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/4 0004 9:48
 * @描述 TODO
 */

public interface GoodNewsView extends IView {
    void refreshClass(List<GoodNewsClassBean> data);
    void refreshList(List<GoodNewsItemBean> data, int position);
}
