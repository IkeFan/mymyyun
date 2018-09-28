package com.mmy.maimaiyun.model.main.presenter;

import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.customview.LoadingSmartLayout;
import com.mmy.maimaiyun.data.bean.ActivityInfoBean;
import com.mmy.maimaiyun.data.bean.SubCateBean;
import com.mmy.maimaiyun.data.bean.SubGoodsInfoBean;
import com.mmy.maimaiyun.model.main.model.SpecialOfferModel;
import com.mmy.maimaiyun.model.main.ui.activity.GoodInfoActivity;
import com.mmy.maimaiyun.model.main.view.SpecialOfferView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/25 0025 15:30
 * @描述
 */

public class SpecialOfferPresenter extends BasePresenter<SpecialOfferView> {
    private SpecialOfferView mView;

    @Inject
    SpecialOfferModel mModel;

    private List<SubCateBean>      mClassData;
    private List<SubGoodsInfoBean> mData;

    @Inject
    public SpecialOfferPresenter(SpecialOfferView view) {
        mView = view;
    }

    public void loadListData(int cat_id) {
        mModel.getGoods(new OnLoadDataListener<BaseBean<List<ActivityInfoBean>>>(mView.findLoadingSmartLayout()){
            @Override
            public void onResponse(String body, BaseBean<List<ActivityInfoBean>> data,IBean iBean) {
                if (iBean.status == 1 && data.data != null && data.data.size() > 0) {
                    ActivityInfoBean activityInfoBean = data.data.get(0);
                    if (activityInfoBean != null && activityInfoBean.subCate != null && activityInfoBean.subCate.size() > 0) {
                        mData = activityInfoBean.subCate.get(0).subGoodsInfo;
                        mView.refreshList(mData);
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
        }, cat_id);
    }

    public void loadMenuData() {
        mModel.getClazz(new OnLoadDataListener<BaseBean<List<ActivityInfoBean>>>(){
            @Override
            public void onResponse(String body, BaseBean<List<ActivityInfoBean>> data, IBean iBean) {
                if (data.data == null || data.data.size() == 0){
                    mView.findLoadingSmartLayout().switchStatus(LoadingSmartLayout.Status.EMPTY);
                    return;
                }
                if (iBean.status == 1 && data.data != null) {
                    mClassData = data.data.get(0).subCate;
                }
                mView.refreshMenu(mClassData);
                if (data.data.size() > 0)
                    loadListData(data.data.get(0).id);
                else
                    mView.findLoadingSmartLayout().switchStatus(LoadingSmartLayout.Status.EMPTY);
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

    public void openInfoPage(int position) {
        mView.openActivity(GoodInfoActivity.class, "id=" + mData.get(position).id);
    }

    public void selectTab(int position) {
        loadListData(mClassData.get(position).id);
    }

    public void setActivityType(String activityType) {
        mModel.resetActivityName(activityType);
    }
}
