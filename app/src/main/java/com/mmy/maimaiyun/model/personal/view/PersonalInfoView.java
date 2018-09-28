package com.mmy.maimaiyun.model.personal.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.UserBean;


/**
 * @创建者 lucas
 * @创建时间 2017/10/18 0018 16:37
 * @描述 TODO
 */

public interface PersonalInfoView extends IView {
    void refreshView(UserBean data);
}
