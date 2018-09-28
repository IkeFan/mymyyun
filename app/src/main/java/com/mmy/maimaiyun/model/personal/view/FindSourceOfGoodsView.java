package com.mmy.maimaiyun.model.personal.view;

import com.mmy.maimaiyun.base.mvp.IView;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/9/1 0001 11:16
 * @描述 TODO
 */

public interface FindSourceOfGoodsView extends IView {
    void refreshTabs(List<String> tabs);
    void refreshGoods(List<Integer> data);
}
