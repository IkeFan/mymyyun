package com.mmy.maimaiyun.model.personal.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.BannerBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/20 0020 9:43
 * @描述 TODO
 */

public interface ShopView extends IView {
    void showBanner(List<BannerBean> data);
    void refreshList(List<String> data);
}
