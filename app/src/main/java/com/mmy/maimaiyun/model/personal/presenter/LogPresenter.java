package com.mmy.maimaiyun.model.personal.presenter;

import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.LogBean;
import com.mmy.maimaiyun.model.personal.model.LogModel;
import com.mmy.maimaiyun.model.personal.view.LogView;
import com.mmy.maimaiyun.utils.Constants;
import com.mmy.maimaiyun.utils.MD5;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import javax.inject.Inject;


/**
 * @author Administrator
 * @创建者 lucas
 * @创建时间 2017/12/30 0030 14:37
 * @描述 TODO
 */

public class LogPresenter extends BasePresenter<LogView> {
    private LogView mView;
    @Inject
    LogModel mModel;
    @Inject
    public LogPresenter(LogView view) {
        mView = view;
    }

    public void loadData(String com, String nu) {
        String json = "{\"com\":\""+com+"\",\"num\":\""+nu+"\"}";
        mModel.loadData(new OnLoadDataListener<LogBean>() {
            @Override
            public void onResponse(String body, LogBean data, IBean iBean) {
                super.onResponse(body, data, iBean);
                mView.refreshView(data);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<LogBean>() {
                }.getType();
            }
        }, Constants.LOG_100_CUSTOMER, MD5.encode(json+Constants.LOG_100_KEY+Constants.LOG_100_CUSTOMER), json);
    }
}
