package com.mmy.maimaiyun.model.personal.presenter;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.model.personal.model.AddNewsAddressModel;
import com.mmy.maimaiyun.model.personal.view.AddNewsAddressView;

import java.lang.reflect.Type;
import java.util.HashMap;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/10/6 0006 16:58
 * @描述 TODO
 */

public class AddNewsAddressPresenter extends BasePresenter<AddNewsAddressView> {
    private AddNewsAddressView mView;

    @Inject
    AddNewsAddressModel mModel;

    @Inject
    public AddNewsAddressPresenter(AddNewsAddressView view) {
        mView = view;
    }

    public void submit(HashMap<String, String> map) {
        map.put("token", mView.getUserBean().token);
        map.put("member_id", mView.getUserBean().member_id);

        mModel.pushData(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data) {
                mView.success(data.info);

            }

            @Override
            public void onFailure(String body, Throwable t) {
                mView.fail("添加失败");
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
        }, map);
    }
}
