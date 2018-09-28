package com.mmy.maimaiyun.model.classity.presenter;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.ClassityBean;
import com.mmy.maimaiyun.model.classity.model.ClassInfoModel;
import com.mmy.maimaiyun.model.classity.view.ClassIInfoView;
import com.mmy.maimaiyun.model.main.ui.activity.GoodInfoActivity;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/10/14 0014 17:52
 * @描述 TODO
 */

public class ClassInfoPresenter extends BasePresenter<ClassIInfoView> {
    private ClassIInfoView mView;
    @Inject
    ClassInfoModel mModel;
    private List<ClassityBean> mData;

    @Inject
    public ClassInfoPresenter(ClassIInfoView view) {
        mView = view;
    }

    public void loadData(String cat_id){
        mModel.loadData(new OnLoadDataListener<BaseBean<List<ClassityBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<ClassityBean>> data) {
                mData = data.data;
                mView.refreshView(mData);
            }

            @Override
            public void onFailure(String body, Throwable t) {
                mView.loadFailde();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<ClassityBean>>>(){}.getType();
            }
        },mView.getUserBean().token,cat_id);
    }

    public void openInfoPage(int position) {
        mView.openActivity(GoodInfoActivity.class,"id="+mData.get(position).id);
    }
}
