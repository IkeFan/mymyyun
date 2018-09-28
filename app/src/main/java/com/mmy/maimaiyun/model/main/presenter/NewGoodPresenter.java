package com.mmy.maimaiyun.model.main.presenter;

import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.NewGoodBean;
import com.mmy.maimaiyun.model.main.model.NewGoodModel;
import com.mmy.maimaiyun.model.main.view.NewGoodView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2018/1/18 0018 17:33
 * @描述 TODO
 */

public class NewGoodPresenter extends BasePresenter<NewGoodView> {
    private NewGoodView mView;
    @Inject
    NewGoodModel mModel;

    @Inject
    public NewGoodPresenter(NewGoodView view) {
        mView = view;
    }

    int page = 0;

    public void loadData(boolean isLoadMore){
        if (isLoadMore)
            page++;
        else
            page = 0;
        mModel.getNewGoodData(new OnLoadDataListener<BaseBean<NewGoodBean>>() {
            @Override
            public void onResponse(String body, BaseBean<NewGoodBean> data, IBean iBean) {
                super.onResponse(body, data, iBean);
                if (iBean.status==1)
                    mView.refreshView(data.data.goods,isLoadMore);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<NewGoodBean>>(){}.getType();
            }
        },page);
    }
}
