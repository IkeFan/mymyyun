package com.mmy.maimaiyun.model.personal.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.CollectionBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/8 0008 13:46
 * @描述 TODO
 */

public interface CollectionView extends IView {
    void refresh(List<CollectionBean> data);
}
