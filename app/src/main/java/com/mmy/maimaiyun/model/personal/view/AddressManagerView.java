package com.mmy.maimaiyun.model.personal.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.AddressItemBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/8 0008 10:11
 * @描述 TODO
 */

public interface AddressManagerView extends IView {
    void refreshView(List<AddressItemBean> data);
}
