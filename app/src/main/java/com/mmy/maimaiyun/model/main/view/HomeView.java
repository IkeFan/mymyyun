package com.mmy.maimaiyun.model.main.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.BannerBean;
import com.mmy.maimaiyun.data.bean.BestBean;
import com.mmy.maimaiyun.data.bean.GoodNewsItemBean;
import com.mmy.maimaiyun.data.bean.HomeEveryBean;
import com.mmy.maimaiyun.data.bean.HomeOnlyNewBean;
import com.mmy.maimaiyun.data.bean.RecommendBean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/8/17 0017 11:48
 * @描述 TODO
 */

public interface HomeView extends IView {
    void showBanner(List<BannerBean> data);
    void showNews(List<GoodNewsItemBean> data);
    void refreshBest(List<BestBean> data);
    void refreshRecommend(List<RecommendBean> data, boolean isLoadMore);
    void refreshHomeEveryView(List<HomeEveryBean.TodayBean> data);
    void refreshHomeOnlyNewView(List<HomeOnlyNewBean.GoodsBean> data);
}
