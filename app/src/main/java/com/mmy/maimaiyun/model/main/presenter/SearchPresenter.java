package com.mmy.maimaiyun.model.main.presenter;


import android.app.Activity;
import android.content.Intent;

import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.GoodNewsItemBean;
import com.mmy.maimaiyun.data.bean.SearchBean;
import com.mmy.maimaiyun.model.main.model.SearchModel;
import com.mmy.maimaiyun.model.main.ui.activity.GoodInfoActivity;
import com.mmy.maimaiyun.model.main.ui.activity.NewsInfoActivity;
import com.mmy.maimaiyun.model.main.ui.activity.SearchActivity;
import com.mmy.maimaiyun.model.main.view.SearchView;
import com.mmy.maimaiyun.utils.Constants;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

/**
 * @创建者 lucas
 * @创建时间 2017-11-10 03-27-54
 * @描述 搜索控制器
 */

public class SearchPresenter extends BasePresenter<SearchView> {
    private SearchView mV;
    private int page = 0;

    @Inject
    SearchModel mModel;
    private List<SearchBean> mDataGoods;
    private List<GoodNewsItemBean> mDataNews;
    private final Activity mActivity;

    @Inject
    public SearchPresenter(SearchView v) {
        mActivity = (Activity) v;
        mV = v;
    }

    public void searchGoods(String search_name, int currentType) {
        mModel.search(new OnLoadDataListener<BaseBean<List<SearchBean>>>(mV.findLoadingSmartLayout()) {
            @Override
            public void onResponse(String body, BaseBean<List<SearchBean>> data, IBean iBean) {
                mDataGoods = data.data;
                mV.refreshGoodsView(mDataGoods);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<SearchBean>>>() {
                }.getType();
            }
        }, search_name, page, Constants.LIMIT, currentType);
    }

    public void searchNews(String search_name){
        mModel.search(new OnLoadDataListener<BaseBean<List<GoodNewsItemBean>>>(mV.findLoadingSmartLayout()) {
            @Override
            public void onResponse(String body, BaseBean<List<GoodNewsItemBean>> data, IBean iBean) {
                super.onResponse(body, data, iBean);
                mDataNews = data.data;
                mV.refreshNewsView(mDataNews);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<GoodNewsItemBean>>>() {
                }.getType();
            }
        }, search_name, page, Constants.LIMIT,SearchActivity.NEWS);
    }

    public void openGoodInfo(int position) {
        mV.openActivity(GoodInfoActivity.class,"id="+ mDataGoods.get(position).id);
    }

    public void openNewsInfo(int position) {
        if (mDataNews!=null){
            GoodNewsItemBean bean = mDataNews.get(position);
            Intent intent = new Intent(mActivity, NewsInfoActivity.class);
            intent.putExtra("bean",bean);
            mActivity.startActivity(intent);
        }
    }
}
