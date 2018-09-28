package com.mmy.maimaiyun.model.main.presenter;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.BannerBean;
import com.mmy.maimaiyun.data.bean.HotGoodsBean;
import com.mmy.maimaiyun.data.bean.HotGoodsRecommemdBean;
import com.mmy.maimaiyun.model.main.model.HotGoodsModel;
import com.mmy.maimaiyun.model.main.model.IBannersModel;
import com.mmy.maimaiyun.model.main.ui.activity.GoodInfoActivity;
import com.mmy.maimaiyun.model.main.ui.activity.HotGoodsActivity;
import com.mmy.maimaiyun.model.main.view.HotGoodsView;
import com.mmy.maimaiyun.utils.Constants;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/2 0002 10:02
 * @描述 TODO
 */

public class HotGoodsPresenter extends BasePresenter<HotGoodsView> {
    private final HotGoodsActivity mActivity;
    private       HotGoodsView     mView;
    HashMap<String, String> attrs = new HashMap<>();


    @Inject
    HotGoodsModel mModel;
    @Inject
    IBannersModel mIBannersModel;

    private List<HotGoodsRecommemdBean>  mRecommemdBeen;
    private List<HotGoodsBean.GoodsBean> mGoodsBeen;
    private int                          pageNum;

    @Inject
    public HotGoodsPresenter(HotGoodsView view) {
        mView = view;
        mActivity = (HotGoodsActivity) view;
    }

    public void loadBanner() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", mActivity.getUserBean().token);
        map.put("position", IBannersModel.BannerType.HOTGOODS.value);
        mIBannersModel.getBanners(new OnLoadDataListener<BaseBean<List<BannerBean>>>() {

            @Override
            public void onResponse(String body, BaseBean<List<BannerBean>> data) {
                if (data.data.size() != 0)
                    mView.refreshBanner(data.data);
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

    public void loadGoodsData(int i, boolean isRefresh) {
        mModel.loadGoodHotData(new OnLoadDataListener<BaseBean<HotGoodsBean>>(mView.findLoadingSmartLayout()) {
            @Override
            public void onResponse(String body, BaseBean<HotGoodsBean> data) {
                mGoodsBeen = data.data.goods;
                mView.refreshAttrPopup(data.data.screening);
                if (isRefresh)
                    mView.refreshHotGoods(mGoodsBeen);
                else//加载更多
                    mView.loadMoreGoods(mGoodsBeen);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<HotGoodsBean>>() {
                }.getType();
            }
        }, mView.getUserBean().token, i, pageNum++, Constants.LIMIT, attrs);
    }

    public void loadRecommend() {
        mModel.loadGoodHotRecommemd(new OnLoadDataListener<BaseBean<List<HotGoodsRecommemdBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<HotGoodsRecommemdBean>> data) {
                mRecommemdBeen = data.data;
                mView.refreshRecommend(mRecommemdBeen);
                loadGoodsData(0, true);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public void onStart() {
                mView.showLoading();
            }

            @Override
            public void onCompleted() {
                mView.hideLoading();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<HotGoodsRecommemdBean>>>() {
                }.getType();
            }
        }, mView.getUserBean().token);
    }

    public void onShopItemClick(int position) {
        mView.openActivity(GoodInfoActivity.class, "id=" + mGoodsBeen.get(position).id);
    }

    public void onRecommendItemClick(int position) {
        mView.openActivity(GoodInfoActivity.class, "id=" + mRecommemdBeen.get(position).id);
    }

    public void onTabSelected(int order) {
        loadGoodsData(order, true);
    }

    public void attrsSelected(int order, HashMap<String, String> attrs) {
        this.attrs = attrs;
        loadGoodsData(order, true);
    }
}
