package com.mmy.maimaiyun.model.main.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.BannerBean;
import com.mmy.maimaiyun.data.bean.CommentBean;
import com.mmy.maimaiyun.data.bean.GoodsInfoAttrBean;
import com.mmy.maimaiyun.data.bean.GoodsInfoBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/5 0005 11:13
 * @描述 TODO
 */

public interface GoodInfoView extends IView {
    void showBanner(List<BannerBean> data);
    void initColorPopup(List<List<GoodsInfoAttrBean.SpecBean>> data);
    void initColorLogo(String logo);
    void initParameterPopup(List<GoodsInfoAttrBean.AttributeBean> data);
    void showDetail(GoodsInfoBean bean);
    void showComment(List<CommentBean> data);
    void refreshNesPrice(String newMoney);
    void initCoupon(List<GoodsInfoBean.CouponsBean> coupons);
}
