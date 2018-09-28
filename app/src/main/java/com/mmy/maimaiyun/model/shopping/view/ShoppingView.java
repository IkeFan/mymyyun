package com.mmy.maimaiyun.model.shopping.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.ShopCarListBean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/8/22 0022 16:17
 * @描述 TODO
 */

public interface ShoppingView extends IView {
    void refreshTotalMoney(float money);
    void refreshList(List<ShopCarListBean> data);
    void selectAll(boolean isChecked);
    void hideRefreshView();
}
