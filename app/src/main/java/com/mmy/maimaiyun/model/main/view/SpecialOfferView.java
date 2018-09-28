package com.mmy.maimaiyun.model.main.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.SubCateBean;
import com.mmy.maimaiyun.data.bean.SubGoodsInfoBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/25 0025 15:29
 * @描述 TODO
 */

public interface SpecialOfferView extends IView {
    void refreshList(List<SubGoodsInfoBean> data);
    void refreshMenu(List<SubCateBean> data);
}
