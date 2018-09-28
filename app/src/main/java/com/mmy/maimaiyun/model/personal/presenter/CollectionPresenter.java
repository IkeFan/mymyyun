package com.mmy.maimaiyun.model.personal.presenter;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.CollectionBean;
import com.mmy.maimaiyun.model.main.ui.activity.GoodInfoActivity;
import com.mmy.maimaiyun.model.personal.model.CollectionModel;
import com.mmy.maimaiyun.model.personal.view.CollectionView;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

/**
 * @创建者 lucas
 * @创建时间 2017/9/8 0008 13:47
 * @描述 TODO
 */

public class CollectionPresenter extends BasePresenter<CollectionView> {
    private CollectionView mView;
    @Inject
    CollectionModel mModel;
    private List<CollectionBean> mData;

    @Inject
    public CollectionPresenter(CollectionView view) {
        mView = view;
    }

    public void loadData() {
        mModel.loadList(new OnLoadDataListener<BaseBean<List<CollectionBean>>>(mView.findLoadingSmartLayout()) {
            @Override
            public void onResponse(String body, BaseBean<List<CollectionBean>> data) {
                mData = data.data;
                mView.refresh(mData);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<CollectionBean>>>() {
                }.getType();
            }
        }, mView.getUserBean().token, mView.getUserBean().member_id);
    }

    public void delCollect(String ids) {
        mModel.delCollect(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data) {
                if (data.status == 1)
                    loadData();
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }


            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>() {
                }.getType();
            }
        }, mView.getUserBean().token, ids);
    }

    public void openGoodInfoPage(int position) {
        CollectionBean bean = mData.get(position);
        mView.openActivity(GoodInfoActivity.class, "id=" + bean.goods_id);
    }
}
