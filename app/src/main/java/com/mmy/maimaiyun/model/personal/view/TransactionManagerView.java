package com.mmy.maimaiyun.model.personal.view;

import com.mmy.maimaiyun.base.mvp.IView;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/21 0021 17:46
 * @描述 TODO
 */

public interface TransactionManagerView extends IView {
    void refresh(List<String> data);
}
