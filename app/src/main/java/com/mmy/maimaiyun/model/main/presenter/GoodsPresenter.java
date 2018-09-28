package com.mmy.maimaiyun.model.main.presenter;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.BoutiqueBean;
import com.mmy.maimaiyun.data.bean.GoodsBestBean;
import com.mmy.maimaiyun.data.bean.GoodsClazzBean;
import com.mmy.maimaiyun.model.main.model.GoodsModel;
import com.mmy.maimaiyun.model.main.ui.activity.GoodInfoActivity;
import com.mmy.maimaiyun.model.main.view.GoodsView;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/1 0001 11:18
 * @描述 TODO
 */

public class GoodsPresenter extends BasePresenter<GoodsView> {
    private GoodsView mView;
    HashMap<String, String> attrs = new HashMap<>();
    @Inject
    GoodsModel mGoodsModel;

    @Inject
    public GoodsPresenter(GoodsView view) {
        mView = view;
    }

    private BaseBean<List<GoodsClazzBean>> mClazzData;

    public void loadTabsData() {
//        mView.showLoading();
        mGoodsModel.loadClazzData(new OnLoadDataListener<BaseBean<List<GoodsClazzBean>>>() {

            @Override
            public void onResponse(String body, BaseBean<List<GoodsClazzBean>> data) {
                mClazzData = data;
//                mView.hideLoading();
                loadGoodsData(data.data.get(0).id, 0);
                mView.refreshTabs(data.data);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<GoodsClazzBean>>>() {
                }.getType();
            }
        }, mView.getUserBean().token);
    }

    private BaseBean<BoutiqueBean> mGoodsData;

    public void loadGoodsData(int i, int order) {
//        mView.showLoading();
        mGoodsModel.loadGoodsBest(new OnLoadDataListener<BaseBean<BoutiqueBean>>(mView.findLoadingSmartLayout()) {
            @Override
            public void onResponse(String body, BaseBean<BoutiqueBean> data) {
                mGoodsData = data;
//                mView.hideLoading();
                mView.refreshGoods(data.data.goods);
                mView.refreshAttrPopup(data.data.screening);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public void onCompleted() {
                attrs.clear();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<BoutiqueBean>>() {
                }.getType();
            }
        }, mView.getUserBean().token, i, order, attrs);
    }

    public void attrsSelected(int position, int order,HashMap<String,String> attrs){
        int id = mClazzData.data.get(position).id;
        this.attrs = attrs;
        loadGoodsData(id, order);
    }

    //添加到购物车
    public void add2ShopCar(int position) {

    }

    public void onTabSelected(int position, int order) {
        int id = mClazzData.data.get(position).id;
        loadGoodsData(id, order);
    }

    public void onShopItemClick(int position) {
        GoodsBestBean bean = mGoodsData.data.goods.get(position);
        mView.openActivity(GoodInfoActivity.class, "id=" + bean.id);
    }
}
