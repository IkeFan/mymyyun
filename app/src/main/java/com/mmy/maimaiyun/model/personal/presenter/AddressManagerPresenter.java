package com.mmy.maimaiyun.model.personal.presenter;

import android.content.Intent;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.AddressItemBean;
import com.mmy.maimaiyun.model.personal.model.AddressManagerModel;
import com.mmy.maimaiyun.model.personal.ui.activity.AddressModifyActivity;
import com.mmy.maimaiyun.model.personal.view.AddressManagerView;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/8 0008 10:12
 * @描述 TODO
 */

public class AddressManagerPresenter extends BasePresenter<AddressManagerView> {
    private AddressManagerView mView;

    @Inject
    AddressManagerModel mModel;

    private final BaseActivity mActivity;

    @Inject
    public AddressManagerPresenter(AddressManagerView view) {
        mView = view;
        mActivity = (BaseActivity) view;
    }

    private List<AddressItemBean> mData;

    public void loadData() {
        mModel.getAddressListData(new OnLoadDataListener<BaseBean<List<AddressItemBean>>>(mView.findLoadingSmartLayout()) {
            @Override
            public void onResponse(String body, BaseBean<List<AddressItemBean>> data) {
                mData = data.data;
                mView.refreshView(mData);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<AddressItemBean>>>() {
                }.getType();
            }
        }, mView.getUserBean().token, mView.getUserBean().member_id);
    }

    public void mdifDefaultAddress(int position) {
        String address_id = mData.get(position).address_id;
        mModel.mdifDefaultAddress(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data) {
                loadData();
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>() {
                }.getType();
            }
        }, mView.getUserBean().token, mView.getUserBean().member_id, address_id);
    }

    public void delAddress(int position) {
        mModel.delAddress(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data) {
                loadData();
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>() {
                }.getType();
            }
        }, mView.getUserBean().token, mView.getUserBean().member_id, mData.get(position).address_id);
    }

    public void openModifyPage(int position) {
        Intent intent = new Intent(mActivity, AddressModifyActivity.class);
        intent.putExtra("bean", mData.get(position));
        mActivity.startActivity(intent);
    }
}
