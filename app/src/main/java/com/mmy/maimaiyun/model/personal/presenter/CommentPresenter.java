package com.mmy.maimaiyun.model.personal.presenter;

import android.widget.Toast;

import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.model.personal.model.CommentModel;
import com.mmy.maimaiyun.model.personal.view.CommentView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import okhttp3.MultipartBody;


/**
 * @创建者 lucas
 * @创建时间 2017/12/6 0006 11:50
 * @描述 TODO
 */

public class CommentPresenter extends BasePresenter<CommentView> {
    private CommentView mView;

    @Inject
    CommentModel mModel;

    @Inject
    public CommentPresenter(CommentView view) {
        mView = view;
    }

    public void submit(List<MultipartBody.Part> parts){
        mView.showLoading("正在提交评论..");
        mModel.goodComment(new OnLoadDataListener<BaseBean>() {
            @Override
            public void onResponse(String body, BaseBean data, IBean iBean) {
                super.onResponse(body, data, iBean);
                Toast.makeText(mApp, iBean.info, Toast.LENGTH_SHORT).show();
                mView.hideLoading();
                mView.finishSelf();
            }

            @Override
            public void onFailure(String body, Throwable t) {
                mView.hideLoading();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean>(){}.getType();
            }
        },parts);
    }
}
