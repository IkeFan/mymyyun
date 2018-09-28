package com.mmy.maimaiyun.model.personal.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.BankCarBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/18 0018 15:42
 * @描述 TODO
 */

public interface BankCarView extends IView {
    void refreshList(List<BankCarBean> data);
}
