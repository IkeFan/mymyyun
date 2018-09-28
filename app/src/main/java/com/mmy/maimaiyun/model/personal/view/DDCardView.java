package com.mmy.maimaiyun.model.personal.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.DDCardBean;


/**
 * @创建者 lucas
 * @创建时间 2017/12/14 0014 14:28
 * @描述 TODO
 */

public interface DDCardView extends IView {
    void refreshView(DDCardBean data);
}
