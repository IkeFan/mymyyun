package com.mmy.maimaiyun.model.personal.view;

import com.mmy.maimaiyun.base.mvp.IView;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/16 0016 15:13
 * @描述 TODO
 */

public interface GoodManageView extends IView {
    void refreshList(List<String> data);
}
