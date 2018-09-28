package com.mmy.maimaiyun.model.personal.presenter;

import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.RefundListBean;
import com.mmy.maimaiyun.model.personal.model.RefundListModel;
import com.mmy.maimaiyun.model.personal.view.RefundListView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 11:07
 * @描述 TODO
 */

public class RefundListPresenter extends BasePresenter<RefundListView> {
    private RefundListView mView;

    @Inject
    RefundListModel mModel;
    private final BaseActivity mActivity;

    @Inject
    public RefundListPresenter(RefundListView view) {
        mView = view;
        mActivity = (BaseActivity) view;
    }

    int page = 0;

    public void loadData(boolean isLoadMore) {
        if (isLoadMore)
            page++;
        else
            page = 0;
        mModel.getList(new OnLoadDataListener<BaseBean<List<RefundListBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<RefundListBean>> data, IBean iBean) {
                if (iBean.status == 1)
                    mView.refreshList(data.data,isLoadMore);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<RefundListBean>>>() {
                }.getType();
            }
        }, mView.getUserBean().member_id, page, mView.getUserBean().token);
    }

}
