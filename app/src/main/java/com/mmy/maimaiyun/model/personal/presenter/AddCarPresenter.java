package com.mmy.maimaiyun.model.personal.presenter;

import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.model.personal.model.BankCarModel;
import com.mmy.maimaiyun.model.personal.view.AddCarView;

import java.lang.reflect.Type;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/11/15 0015 15:32
 * @描述
 */

public class AddCarPresenter extends BasePresenter<AddCarView> {
    private AddCarView mView;
    @Inject
    BankCarModel mModel;

    @Inject
    public AddCarPresenter(AddCarView view) {
        mView = view;
    }

    public void addCard(String name, String id_card, String deposit, String bank_card) {
        mModel.addBankCard(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data, IBean iBean) {
                Toast.makeText(mApp, iBean.info, Toast.LENGTH_SHORT).show();
                mView.finishSelf();
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>() {
                }.getType();
            }
        }, mView.getUserBean().token, name, id_card, deposit, bank_card, mView.getUserBean().member_id);
    }
}
