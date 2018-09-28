package com.mmy.maimaiyun.model.main.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.GoodNewsItemBean;
import com.mmy.maimaiyun.data.bean.SearchBean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017-11-10 03-27-54
 * @描述 搜索界面接口
 */

public interface SearchView extends IView {
    void refreshGoodsView(List<SearchBean> data);

    void refreshNewsView(List<GoodNewsItemBean> dataGoods);
}
