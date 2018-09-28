package com.mmy.maimaiyun.model.shopping.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.ShopCarListBean;
import com.mmy.maimaiyun.model.shopping.model.ShoppingModel;
import com.mmy.maimaiyun.model.shopping.view.ShoppingView;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

/**
 * @创建者 lucas
 * @创建时间 2017/8/22 0022 16:24
 * @描述 TODO
 */

public class ShoppingPresenter extends BasePresenter<ShoppingView> {
    private ShoppingView mShoppingView;
    private float mAllMoney = 0f;

    @Inject
    ShoppingModel mModel;
    private List<ShopCarListBean> mData;

    @Inject
    public ShoppingPresenter(ShoppingView shoppingView) {
        mShoppingView = shoppingView;
    }

    public void loadList() {
        mModel.getListData(new OnLoadDataListener<BaseBean<List<ShopCarListBean>>>(mShoppingView.findLoadingSmartLayout()) {

            @Override
            public void onResponse(String body, BaseBean<List<ShopCarListBean>> data) {
                mData = data.data;
                mShoppingView.refreshList(data.data);
            }

            @Override
            public void onFailure(String body, Throwable t) {
                mShoppingView.hideRefreshView();
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted() {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<ShopCarListBean>>>() {
                }.getType();
            }
        }, mShoppingView.getUserBean().token, mShoppingView.getUserBean().member_id);
        //        mShoppingData = VDataMakeHelper.getShoppingData();
    }


    //重新计算
    public void reCalculate() {
        mAllMoney = 0f;
        for (ShopCarListBean shoppingBean : mData) {
            for (ShopCarListBean.CartInfoBean infoBean : shoppingBean.cartInfo) {
                if (infoBean.isChecked)
                    mAllMoney += infoBean.shop_price * infoBean.goods_number;
            }
        }
        mShoppingView.refreshTotalMoney(mAllMoney);
    }


    //全选
    public void selectAll(boolean isChecked) {
        mAllMoney = 0f;
        if (isChecked)
            for (ShopCarListBean shoppingBean : mData) {
                for (ShopCarListBean.CartInfoBean infoBean : shoppingBean.cartInfo) {
                    mAllMoney += infoBean.shop_price * infoBean.goods_number;
                }
            }
        mShoppingView.refreshTotalMoney(mAllMoney);
        mShoppingView.selectAll(isChecked);
    }


    public void deleteShop(String ids) {
        if (TextUtils.isEmpty(ids))
            return;
        mModel.deleteShop(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data) {
                if (data.status == 1)
                    loadList();
            }

            @Override
            public void onFailure(String body, Throwable t) {
                mShoppingView.hideRefreshView();
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted() {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>() {
                }.getType();
            }
        }, mShoppingView.getUserBean().token, ids);
    }

    public void changeShopCount(String goods_id, int count) {
        mModel.changeShopCount(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data) {
                Log.d("ShoppingPresenter", data.info);
            }

            @Override
            public void onFailure(String body, Throwable t) {
                mShoppingView.hideRefreshView();
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted() {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>() {
                }.getType();
            }
        }, mShoppingView.getUserBean().token, goods_id, count);
    }

}
