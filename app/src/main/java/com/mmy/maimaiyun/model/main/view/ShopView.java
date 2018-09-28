package com.mmy.maimaiyun.model.main.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.MainShopBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2018/1/30 0030 14:05
 * @描述 TODO
 */

public interface ShopView extends IView {
    void refreshHeader(MainShopBean shop);
    void refreshList(List<MainShopBean.GoodsBean> goods, boolean isLoadMore);
}
