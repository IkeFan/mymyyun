package com.mmy.maimaiyun.model.personal.presenter;

import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.BankCarBean;
import com.mmy.maimaiyun.model.personal.model.BankCarModel;
import com.mmy.maimaiyun.model.personal.view.BankCarView;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/18 0018 15:43
 * @描述 TODO
 */

public class BankCarPresenter extends BasePresenter<BankCarView> {
    private BankCarView mView;
    @Inject
    BankCarModel mModel;

    @Inject
    public BankCarPresenter(BankCarView view) {
        mView = view;
    }

    public void loadData() {
        mModel.getBankList(new OnLoadDataListener<BaseBean<List<BankCarBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<BankCarBean>> data, IBean iBean) {
                if (iBean.status == 1)
                    mView.refreshList(data.data);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<BankCarBean>>>() {
                }.getType();
            }
        }, mView.getUserBean().token, mView.getUserBean().member_id);
    }

    public void delCars(String selectCarId) {
        mModel.delBankCard(new OnLoadDataListener<BaseBean<List<BankCarBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<BankCarBean>> data, IBean iBean) {
                Toast.makeText(mApp, iBean.info, Toast.LENGTH_SHORT).show();
                mView.refreshList(data.data);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<BankCarBean>>>(){}.getType();
            }
        },mView.getUserBean().token,selectCarId,mView.getUserBean().member_id);
    }
}
