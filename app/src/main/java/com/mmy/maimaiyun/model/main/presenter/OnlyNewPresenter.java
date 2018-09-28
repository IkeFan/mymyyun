package com.mmy.maimaiyun.model.main.presenter;

import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.OnlyNewBean;
import com.mmy.maimaiyun.model.main.model.TimeLimitModel;
import com.mmy.maimaiyun.model.main.ui.activity.GoodInfoActivity;
import com.mmy.maimaiyun.model.main.view.OnlyNewView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2018/1/17 0017 19:15
 * @描述 TODO
 */

public class OnlyNewPresenter extends BasePresenter<OnlyNewView> {

    @Inject
    TimeLimitModel mModel;
    private OnlyNewView mView;
    private List<OnlyNewBean.GoodsBean> mGoods;

    @Inject
    public OnlyNewPresenter(OnlyNewView view) {
        mView = view;
    }

    public void loadData() {
        mModel.loadData(new OnLoadDataListener<BaseBean<OnlyNewBean>>() {
            @Override
            public void onResponse(String body, BaseBean<OnlyNewBean> data, IBean iBean) {
                if (iBean.status == 1 && data.data!=null) {
                    mGoods = data.data.goods;
                    mView.refreshView(mGoods);
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<OnlyNewBean>>() {
                }.getType();
            }
        }, mView.getUserBean().token, 2);
    }

    public void openGoodInfo(int position) {
        mView.openActivity(GoodInfoActivity.class, "id=" + mGoods.get(position).id);
    }
}
