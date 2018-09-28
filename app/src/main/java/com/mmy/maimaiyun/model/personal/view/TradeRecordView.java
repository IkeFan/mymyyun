package com.mmy.maimaiyun.model.personal.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.TradeRecordBean;

import java.util.LinkedHashMap;
import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/19 0019 11:43
 * @描述 TODO
 */

public interface TradeRecordView extends IView {
    void refreshList(List<TradeRecordBean> data, boolean isLoadMore);
    void refreshMenuList(LinkedHashMap<Integer, String> map);
}
