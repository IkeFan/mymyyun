package com.mmy.maimaiyun.model.personal.presenter;


import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.model.personal.model.BranchOfficeModel;
import com.mmy.maimaiyun.model.personal.view.BranchOfficeView;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import okhttp3.MultipartBody;

/**
 * @创建者 lucas
 * @创建时间 2017/10/25 0025 17:57
 * @描述
 */

public class BranchOfficePresenter extends BasePresenter<BranchOfficeView> {
    private BranchOfficeView mView;

    @Inject
    BranchOfficeModel mModel;

    @Inject
    public BranchOfficePresenter(BranchOfficeView view) {
        mView = view;
    }

    public void subBranchOfficeData(List<MultipartBody.Part> data){
        mView.showLoading();
        mModel.subBranchOfficeData(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data, IBean iBean) {
                mView.hideLoading();
                if (iBean.status==1){
                    mView.finishSelf();
                    Toast.makeText(mApp, "资料提交成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mApp, data.info, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(String body, Throwable t) {
                mView.hideLoading();
            }
            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>(){}.getType();
            }
        },data);
    }

}
