package com.mmy.maimaiyun.model.msg.view;

import com.mmy.maimaiyun.base.mvp.IView;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/18 0018 13:49
 * @描述 TODO
 */

public interface NotifyListView extends IView {
    void refreshList(List<String> data);
}
