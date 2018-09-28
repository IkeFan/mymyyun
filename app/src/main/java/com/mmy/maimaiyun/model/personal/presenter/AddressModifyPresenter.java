package com.mmy.maimaiyun.model.personal.presenter;

import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.model.personal.model.AddressModifyModel;
import com.mmy.maimaiyun.model.personal.view.AddressModifyView;

import java.lang.reflect.Type;
import java.util.HashMap;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/10/7 0007 14:22
 * @描述 TODO
 */

public class AddressModifyPresenter extends BasePresenter<AddressModifyView> {
    private AddressModifyView mView;

    @Inject
    AddressModifyModel mModel;


    @Inject
    public AddressModifyPresenter(AddressModifyView view) {
        mView = view;
    }

    public void modifyData(HashMap<String,String> map){
        mModel.pushData(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data) {
                Toast.makeText(mApp, data.info, Toast.LENGTH_SHORT).show();
                mView.finishSelf();
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted() {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>(){}.getType();
            }
        },map);
    }
    public void delAddress(String address_id){
        mModel.delAddress(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data) {
                Toast.makeText(mApp, data.info, Toast.LENGTH_SHORT).show();
                mView.finishSelf();
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted() {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>(){}.getType();
            }
        },mView.getUserBean().token,mView.getUserBean().member_id,address_id);
    }

}
