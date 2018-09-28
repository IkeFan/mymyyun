package com.mmy.maimaiyun.model.main.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.SubCateBean;
import com.mmy.maimaiyun.data.bean.SubGoodsInfoBean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/12/1 0001 11:53
 * @描述 TODO
 */

public interface OneOneView extends IView {
    void refreshClassView(List<SubCateBean> classBean);
    void refreshGoodsView(List<SubGoodsInfoBean> subGoodsInfo, String cat_pic);
}
