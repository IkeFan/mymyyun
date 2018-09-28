package com.mmy.maimaiyun.model.classity.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.ClassityBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/10/14 0014 17:51
 * @描述 TODO
 */

public interface ClassIInfoView extends IView {
    void refreshView(List<ClassityBean> data);

    void loadFailde();
}
