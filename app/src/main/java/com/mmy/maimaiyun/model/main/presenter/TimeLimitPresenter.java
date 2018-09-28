package com.mmy.maimaiyun.model.main.presenter;

import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.TimeLimitBean;
import com.mmy.maimaiyun.model.main.model.TimeLimitModel;
import com.mmy.maimaiyun.model.main.ui.activity.GoodInfoActivity;
import com.mmy.maimaiyun.model.main.view.TimeLimitView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/25 0025 17:42
 * @描述 TODO
 */

public class TimeLimitPresenter extends BasePresenter<TimeLimitView> {
    private TimeLimitView mView;
    @Inject
    TimeLimitModel mModel;
    private TimeLimitBean mData;

    @Inject
    public TimeLimitPresenter(TimeLimitView view) {
        mView = view;
    }

    public void loadListData(int type) {
        mModel.loadData(new OnLoadDataListener<BaseBean<TimeLimitBean>>() {
            @Override
            public void onResponse(String body, BaseBean<TimeLimitBean> data, IBean iBean) {
                if (iBean.status == 1) {
                    //设置开始时间
                    mData = data.data;
//                    mView.refreshTime(data.data.flashsale.end_time);

                        mView.refresTodayhList(mData.today);

                }
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<TimeLimitBean>>() {
                }.getType();
            }
        }, mView.getUserBean().token, type);
    }

    public void selectTab(int position) {
        if (position == 0)
            mView.refresTodayhList(mData.today);
        else
            mView.refresTomorrowhList(mData.tomorrow);
    }

    public void openGoodInfo(int position, int tabPosition) {
        if (mData==null) return;
        String id = tabPosition == 0 ? mData.today.get(position).id : mData.tomorrow.get(position).id;
        mView.openActivity(GoodInfoActivity.class, "id=" + id);
    }
}
