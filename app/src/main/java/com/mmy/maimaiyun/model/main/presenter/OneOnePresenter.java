package com.mmy.maimaiyun.model.main.presenter;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.ActivityInfoBean;
import com.mmy.maimaiyun.data.bean.SubCateBean;
import com.mmy.maimaiyun.data.bean.SubGoodsInfoBean;
import com.mmy.maimaiyun.model.main.model.OneOneModel;
import com.mmy.maimaiyun.model.main.ui.activity.GoodInfoActivity;
import com.mmy.maimaiyun.model.main.view.OneOneView;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/12/1 0001 15:23
 * @描述 TODO
 */

public class OneOnePresenter extends BasePresenter<OneOneView> {
    private OneOneView mView;

    @Inject
    OneOneModel mModel;
    private List<SubCateBean> mClassBean;
    private List<SubGoodsInfoBean> mSubGoodsInfo;

    @Inject
    public OneOnePresenter(OneOneView view) {
        mView = view;
    }

    public void loadClass() {
        mModel.getClazz(new OnLoadDataListener<BaseBean<List<ActivityInfoBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<ActivityInfoBean>> data, IBean iBean) {
                super.onResponse(body, data, iBean);
                if (iBean.status == 1 && data.data != null && data.data.size() > 0) {
                    mClassBean = data.data.get(0).subCate;
                    mView.refreshClassView(mClassBean);
                    List<SubCateBean> cateBeans = mClassBean;
                    if (cateBeans != null && cateBeans.size() > 0) {
                        loadGoods(cateBeans.get(0).id, 0);
                    }
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<ActivityInfoBean>>>() {
                }.getType();
            }
        });
    }

    public void loadGoods(int calssId, int position) {
        mModel.getGoods(new OnLoadDataListener<BaseBean<List<ActivityInfoBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<ActivityInfoBean>> data, IBean iBean) {
                super.onResponse(body, data, iBean);
                if (iBean.status == 1 && data.data != null && data.data.size() > 0) {
                    ActivityInfoBean activityInfoBean = data.data.get(0);
                    if (activityInfoBean != null && activityInfoBean.subCate != null && activityInfoBean.subCate.size() > 0) {
                        mSubGoodsInfo = activityInfoBean.subCate.get(0).subGoodsInfo;
                        mView.refreshGoodsView(mSubGoodsInfo,mClassBean.get(position).cat_pic);
                    }
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<ActivityInfoBean>>>() {
                }.getType();
            }
        }, calssId);
    }

    public void selectClass(int position) {
        loadGoods(mClassBean.get(position).id,position);
    }

    public void openGoodInfo(int position) {
        mView.openActivity(GoodInfoActivity.class, "id=" + mSubGoodsInfo.get(position).id);
    }
}
