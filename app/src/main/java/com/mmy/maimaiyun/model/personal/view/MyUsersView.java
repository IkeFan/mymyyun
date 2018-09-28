package com.mmy.maimaiyun.model.personal.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.UserBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/20 0020 18:13
 * @描述 TODO
 */

public interface MyUsersView extends IView {
    void refreshList(List<UserBean> data);
}
