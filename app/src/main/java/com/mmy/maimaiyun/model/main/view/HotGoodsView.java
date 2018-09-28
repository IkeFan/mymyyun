package com.mmy.maimaiyun.model.main.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.BannerBean;
import com.mmy.maimaiyun.data.bean.HotGoodsBean;
import com.mmy.maimaiyun.data.bean.HotGoodsRecommemdBean;
import com.mmy.maimaiyun.data.bean.ScreeningBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/2 0002 9:56
 * @描述 TODO
 */

public interface HotGoodsView extends IView {
    void refreshBanner(List<BannerBean> banners);
    void refreshRecommend(List<HotGoodsRecommemdBean> recommend);
    void refreshHotGoods(List<HotGoodsBean.GoodsBean> goods);
    void refreshAttrPopup(List<ScreeningBean> screening);
    void loadMoreGoods(List<HotGoodsBean.GoodsBean> goods);
}
