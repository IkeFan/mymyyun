package com.mmy.maimaiyun.model.personal.presenter;

import android.widget.Toast;

import com.mmy.maimaiyun.App;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.model.personal.model.RefundSubmitModel;
import com.mmy.maimaiyun.model.personal.view.RefundSubmitView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import okhttp3.MultipartBody;


/**
 * @创建者 lucas
 * @创建时间 2017/12/5 0005 17:52
 * @描述 TODO
 */

public class RefundSubmitPresenter extends BasePresenter<RefundSubmitView> {
    private RefundSubmitView mView;
    @Inject
    RefundSubmitModel mModel;
    @Inject
    App               mApp;

    @Inject
    public RefundSubmitPresenter(RefundSubmitView view) {
        mView = view;
    }

    public void submitData(List<MultipartBody.Part> data) {
        mModel.refund(new OnLoadDataListener<BaseBean>() {
            @Override
            public void onResponse(String body, BaseBean data, IBean iBean) {
                super.onResponse(body, data, iBean);
                if (iBean.status == 1) {
                    mView.finishSelf();
                }
                Toast.makeText(mApp, iBean.info, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String body, Throwable t) {
                Toast.makeText(mApp, "提交失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean>() {
                }.getType();
            }
        }, data);
    }
}
