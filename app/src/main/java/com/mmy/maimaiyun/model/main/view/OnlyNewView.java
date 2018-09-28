package com.mmy.maimaiyun.model.main.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.OnlyNewBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2018/1/17 0017 19:15
 * @描述 TODO
 */

public interface OnlyNewView extends IView {
    void refreshView(List<OnlyNewBean.GoodsBean> goods);
}
