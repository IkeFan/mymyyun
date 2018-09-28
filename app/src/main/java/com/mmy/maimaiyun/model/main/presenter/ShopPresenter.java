package com.mmy.maimaiyun.model.main.presenter;

import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.MainShopBean;
import com.mmy.maimaiyun.model.main.model.ShopModel;
import com.mmy.maimaiyun.model.main.ui.activity.GoodInfoActivity;
import com.mmy.maimaiyun.model.main.view.ShopView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2018/1/30 0030 14:06
 * @描述 TODO
 */

public class ShopPresenter extends BasePresenter<ShopView> {
    private ShopView mView;

    @Inject
    ShopModel mModel;

    @Inject
    public ShopPresenter(ShopView view) {
        mView = view;
    }

    int page = 0;

    public void loadData(String shop_id, boolean isLoadMore) {
        if (isLoadMore)
            page++;
        else
            page = 0;

        mModel.getShopData(new OnLoadDataListener<BaseBean<MainShopBean>>() {
            @Override
            public void onResponse(String body, BaseBean<MainShopBean> data, IBean iBean) {
                super.onResponse(body, data, iBean);
                if (iBean.status == 1) {
                    mView.refreshHeader(data.data);
                    mView.refreshList(data.data.goods, isLoadMore);
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<MainShopBean>>() {
                }.getType();
            }
        }, shop_id, page);
    }

    public void openGoodInfo(MainShopBean.GoodsBean item) {
        mView.openActivity(GoodInfoActivity.class, "id=" + item.id);
    }
}
