package com.mmy.maimaiyun.model.personal.presenter;

import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.model.personal.model.JoinApplyModel;
import com.mmy.maimaiyun.model.personal.view.JoinApplyView;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import okhttp3.MultipartBody;


/**
 * @创建者 lucas
 * @创建时间 2017/10/19 0019 11:39
 * @描述 TODO
 */

public class JoinApplyPresenter extends BasePresenter<JoinApplyView> {
    private JoinApplyView mView;
    @Inject
    JoinApplyModel mModel;

    @Inject
    public JoinApplyPresenter(JoinApplyView view) {
        mView = view;
    }

    public void pushData(List<MultipartBody.Part> parts) {
        mModel.pushData(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data) {
                if (data.status == 1){
                    Toast.makeText(mApp, "数据已提交，请耐心等待审核", Toast.LENGTH_SHORT).show();
                    mView.finishSelf();
                }else {
                    Toast.makeText(mApp, data.info, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>() {
                }.getType();
            }
        }, parts);
    }
}
