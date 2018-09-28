package com.mmy.maimaiyun.model.main.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.BannerBean;
import com.mmy.maimaiyun.data.bean.SubCateBean;
import com.mmy.maimaiyun.data.bean.SubGoodsInfoBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/1 0001 16:14
 * @描述 TODO
 */

public interface OverseasShoppingView extends IView {
    void showBanner(List<BannerBean> data);

    void refreshGoods(List<SubGoodsInfoBean> data);

    void refreshClass(List<SubCateBean> data);

}
