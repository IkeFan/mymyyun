package com.mmy.maimaiyun.model.main.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.NewGoodBean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2018/1/18 0018 17:32
 * @描述 TODO
 */

public interface NewGoodView extends IView{
    void refreshView(List<NewGoodBean.GoodsBean> goods, boolean isLoadMore);
}
