package com.mmy.maimaiyun.model.main.presenter;

import android.content.Intent;

import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.activity.BaseFragment;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.BannerBean;
import com.mmy.maimaiyun.data.bean.BestBean;
import com.mmy.maimaiyun.data.bean.GoodNewsItemBean;
import com.mmy.maimaiyun.data.bean.HomeEveryBean;
import com.mmy.maimaiyun.data.bean.HomeOnlyNewBean;
import com.mmy.maimaiyun.data.bean.RecommendBean;
import com.mmy.maimaiyun.model.main.model.HomeModel;
import com.mmy.maimaiyun.model.main.model.IBannersModel;
import com.mmy.maimaiyun.model.main.ui.activity.GoodInfoActivity;
import com.mmy.maimaiyun.model.main.ui.activity.NewsInfoActivity;
import com.mmy.maimaiyun.model.main.ui.fragment.HomeFragment;
import com.mmy.maimaiyun.model.main.view.HomeView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * @创建者 lucas
 * @创建时间 2017/8/17 0017 16:29
 * @描述 TODO
 */

public class HomePresenter extends BasePresenter<HomeView> {
    private HomeView mHomeView;

    @Inject
    HomeModel     mHomeModel;
    @Inject
    IBannersModel mIBannersModel;

    private final BaseActivity           mActivity;
    private       List<RecommendBean>    mData;
    private       List<BestBean>         mBestData;
    private       List<GoodNewsItemBean> mDataNews;

    @Inject
    public HomePresenter(HomeView homeView) {
        mHomeView = homeView;
        mActivity = (BaseActivity) ((BaseFragment) homeView).getActivity();
    }

    public void loadBanner() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", mActivity.getUserBean().token);
        map.put("position", IBannersModel.BannerType.HOME.value);
        mHomeView.showLoading();
        mIBannersModel.getBanners(new OnLoadDataListener<BaseBean<List<BannerBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<BannerBean>> data) {
                if (data.data.size() != 0) {
                    mHomeView.showBanner(data.data);
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<BannerBean>>>() {
                }.getType();
            }
        }, map);
    }

    public void loadNews() {
        mHomeModel.loadNews(new OnLoadDataListener<BaseBean<List<GoodNewsItemBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<GoodNewsItemBean>> data) {
                mDataNews = data.data;
                mHomeView.showNews(mDataNews);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public void onCompleted() {
                mHomeView.hideLoading();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<GoodNewsItemBean>>>() {
                }.getType();
            }
        }, mActivity.getUserBean().token);
    }

    public void loadBest() {
        mHomeModel.loadBest(new OnLoadDataListener<BaseBean<List<BestBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<BestBean>> data) {
                mBestData = data.data;
                mHomeView.refreshBest(mBestData);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public void onCompleted() {
                mHomeView.hideLoading();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<BestBean>>>() {
                }.getType();
            }
        }, mActivity.getUserBean().token);
    }

    int pageNumber = 0;

    public void loadRecommend(boolean isLoadMore) {
        if (isLoadMore)
            pageNumber++;
        else
            pageNumber = 0;
        mHomeModel.loadRecommend(new OnLoadDataListener<BaseBean<List<RecommendBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<RecommendBean>> data) {
                mData = data.data;
                mHomeView.refreshRecommend(mData, isLoadMore);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public void onCompleted() {
                mHomeView.hideLoading();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<RecommendBean>>>() {
                }.getType();
            }
        }, mActivity.getUserBean().token, pageNumber);
    }

    public void openGoodInfoPage(int position, int type, RecommendBean o) {
        String id = null;
        switch (type) {
            case HomeFragment.BOUTIQUE:
                id = mBestData.get(position).id;
                break;
            case HomeFragment.RECOMMEND:
                id = o.id;
                break;
        }
        mActivity.openActivity(GoodInfoActivity.class, "id=" + id);
    }

    public void openNewsInfo(int displayedChild) {
        if (mDataNews != null) {
            GoodNewsItemBean bean = mDataNews.get(displayedChild);
            Intent intent = new Intent(mActivity, NewsInfoActivity.class);
            intent.putExtra("bean", bean);
            mActivity.startActivity(intent);
        }
    }

    public void loadHomeEveryData() {
        mHomeModel.loadHomeEvery(new OnLoadDataListener<BaseBean<HomeEveryBean>>() {
            @Override
            public void onResponse(String body, BaseBean<HomeEveryBean> data, IBean iBean) {
                if (iBean.status == 1) {
                    List<HomeEveryBean.TodayBean> today = data.data.today;
                    mHomeView.refreshHomeEveryView(today);
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<HomeEveryBean>>() {
                }.getType();
            }
        });
    }

    public void loadHomeOnlyNewData() {
        mHomeModel.loadHomeOnlyNew(new OnLoadDataListener<BaseBean<HomeOnlyNewBean>>() {
            @Override
            public void onResponse(String body, BaseBean<HomeOnlyNewBean> data, IBean iBean) {
                if (iBean.status == 1)
                    mHomeView.refreshHomeOnlyNewView(data.data.goods);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<HomeOnlyNewBean>>() {
                }.getType();
            }
        });
    }
}
