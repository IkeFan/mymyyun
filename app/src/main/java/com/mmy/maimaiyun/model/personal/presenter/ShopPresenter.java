package com.mmy.maimaiyun.model.personal.presenter;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.BannerBean;
import com.mmy.maimaiyun.helper.VDataMakeHelper;
import com.mmy.maimaiyun.model.personal.model.ShopModel;
import com.mmy.maimaiyun.model.personal.view.ShopView;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/20 0020 9:44
 * @描述 TODO
 */

public class ShopPresenter extends BasePresenter<ShopView> {
    private       ShopView     mView;
    private final BaseActivity mActivity;
    @Inject
    ShopModel mModel;

    @Inject
    public ShopPresenter(ShopView view) {
        mView = view;
        mActivity = (BaseActivity) view;
    }

    public void loadBanner() {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", mActivity.getUserBean().token);
        mModel.pushData(new OnLoadDataListener<BaseBean<List<BannerBean>>>() {

            @Override
            public void onResponse(String body, BaseBean<List<BannerBean>> data) {
                mView.showBanner(data.data);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted() {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<BannerBean>>>() {
                }.getType();
            }
        }, map);
    }

    public void loadListData() {
        mView.refreshList(VDataMakeHelper.createStrList(10));
    }
}
