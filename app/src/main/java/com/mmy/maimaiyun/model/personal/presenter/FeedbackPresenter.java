package com.mmy.maimaiyun.model.personal.presenter;

import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.model.personal.model.FeedbackModel;
import com.mmy.maimaiyun.model.personal.view.FeedbackView;

import java.lang.reflect.Type;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/11/1 0001 15:15
 * @描述
 */

public class FeedbackPresenter extends BasePresenter<FeedbackView> {
    private FeedbackView mView;

    @Inject
    FeedbackModel mModel;

    @Inject
    public FeedbackPresenter(FeedbackView view) {
        mView = view;
    }

    public void submit(String content){
        mModel.feedback(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data) {
                if (data.status==1){
                    Toast.makeText(mApp, "提交成功", Toast.LENGTH_SHORT).show();
                    mView.finishSelf();
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {
                Toast.makeText(mApp, "提交失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>(){}.getType();
            }
        },mView.getUserBean().token,mView.getUserBean().member_id,content);
    }
}
