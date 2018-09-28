package com.mmy.maimaiyun.model.main.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.GoodsBestBean;
import com.mmy.maimaiyun.data.bean.GoodsClazzBean;
import com.mmy.maimaiyun.data.bean.ScreeningBean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/9/1 0001 11:16
 * @描述 TODO
 */

public interface GoodsView extends IView {
    void refreshTabs(List<GoodsClazzBean> tabs);
    void refreshGoods(List<GoodsBestBean> data);
    void refreshAttrPopup(List<ScreeningBean> data);
}
