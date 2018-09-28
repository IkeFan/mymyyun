package com.mmy.maimaiyun.model.personal.presenter;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.UserBean;
import com.mmy.maimaiyun.model.personal.model.MyUserModel;
import com.mmy.maimaiyun.model.personal.view.MyUsersView;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/20 0020 18:14
 * @描述 TODO
 */

public class MyUsersPresenter extends BasePresenter<MyUsersView> {
    private MyUsersView mView;
    @Inject
    MyUserModel mModel;

    @Inject
    public MyUsersPresenter(MyUsersView view) {
        mView = view;
    }

    public void loadData(int level) {
        mModel.getMyUser(new OnLoadDataListener<BaseBean<List<UserBean>>>(mView.findLoadingSmartLayout()) {
            @Override
            public void onResponse(String body, BaseBean<List<UserBean>> data, IBean iBean) {
                if (iBean.status == 1 && data.data != null)
                    mView.refreshList(data.data);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<UserBean>>>() {
                }.getType();
            }
        }, mView.getUserBean().token, mView.getUserBean().member_id, level);
    }
}
