package com.mmy.maimaiyun.model.classity.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.ClassBean;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/10/24 0024 16:19
 * @描述 TODO
 */

public interface ClassityView extends IView {
    void refreshLeft(List<ClassBean> data);
    void refreshRight(LinkedHashMap<ClassBean, List<ClassBean>> data);
}
