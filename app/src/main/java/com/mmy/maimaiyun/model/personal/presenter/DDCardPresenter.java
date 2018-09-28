package com.mmy.maimaiyun.model.personal.presenter;

import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.DDCardBean;
import com.mmy.maimaiyun.model.personal.model.DDCardModel;
import com.mmy.maimaiyun.model.personal.view.DDCardView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/12/14 0014 14:29
 * @描述 TODO
 */

public class DDCardPresenter extends BasePresenter<DDCardView> {
    private DDCardView mView;
    @Inject
    DDCardModel mModel;

    @Inject
    public DDCardPresenter(DDCardView view) {
        mView = view;
    }

    public void loadData(){
        mModel.getDDCardData(new OnLoadDataListener<BaseBean<DDCardBean>>() {
            @Override
            public void onResponse(String body, BaseBean<DDCardBean> data, IBean iBean) {
                super.onResponse(body, data, iBean);
                if (iBean.status==1){
                    mView.refreshView(data.data);
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<DDCardBean>>(){}.getType();
            }
        },mView.getUserBean().token,mView.getUserBean().member_id);
    }
}
