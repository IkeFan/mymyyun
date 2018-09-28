package com.mmy.maimaiyun.model.classity.presenter;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.ClassBean;
import com.mmy.maimaiyun.model.classity.model.ClassityModel;
import com.mmy.maimaiyun.model.classity.view.ClassityView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/10/24 0024 16:20
 * @描述 TODO
 */

public class ClassityPresenter extends BasePresenter<ClassityView> {
    private ClassityView mView;

    @Inject
    ClassityModel mModel;
    private List<ClassBean> mData;

    @Inject
    public ClassityPresenter(ClassityView view) {
        mView = view;
    }

    public void loadLeftData() {
        mModel.loadLeftData(new OnLoadDataListener<BaseBean<List<ClassBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<ClassBean>> data) {
                mData = data.data;
                mView.refreshLeft(mData);
                if (mData.size() > 0)
                    loadRightData(0);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<ClassBean>>>() {
                }.getType();
            }
        }, mView.getUserBean().token);
    }

    public void loadRightData(int position) {
        mModel.loadRightData(new OnLoadDataListener<BaseBean<List<ClassBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<ClassBean>> data) {
                List<ClassBean> list = data.data;
                //取出二级分类
                ArrayList<ClassBean> beens = new ArrayList<>();
                for (ClassBean classBean : list) {
                    String id = mData.get(position).id;
                    if (id.equals(classBean.parent_id))
                            beens.add(classBean);
                }
                //取出三级分类
                LinkedHashMap<ClassBean, List<ClassBean>> map = new LinkedHashMap<>();
                for (ClassBean classBean : beens) {
                    ArrayList<ClassBean> arrayList = new ArrayList<>();
                    map.put(classBean, arrayList);
                    for (ClassBean bean : list) {
                        if (classBean.id.equals(bean.parent_id))
                            arrayList.add(bean);
                    }
                }
                mView.refreshRight(map);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<ClassBean>>>() {
                }.getType();
            }
        }, mView.getUserBean().token, mData.get(position).id);
    }
}
