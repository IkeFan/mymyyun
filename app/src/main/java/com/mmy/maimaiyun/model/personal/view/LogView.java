package com.mmy.maimaiyun.model.personal.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.LogBean;


/**
 * @创建者 lucas
 * @创建时间 2017/12/30 0030 14:35
 * @描述 TODO
 */

public interface LogView extends IView {
    void refreshView(LogBean logBean);
}
