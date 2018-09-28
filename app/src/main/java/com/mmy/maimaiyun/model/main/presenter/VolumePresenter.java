package com.mmy.maimaiyun.model.main.presenter;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.ActivityCateBean;
import com.mmy.maimaiyun.data.bean.ActivitySubCateBean;
import com.mmy.maimaiyun.data.bean.ActvityGoodInfoBean;
import com.mmy.maimaiyun.model.main.model.IActivityGoodsModel;
import com.mmy.maimaiyun.model.main.model.VolumeModel;
import com.mmy.maimaiyun.model.main.ui.activity.GoodInfoActivity;
import com.mmy.maimaiyun.model.main.view.VolumeView;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 15:21
 * @描述 TODO
 */

public class VolumePresenter extends BasePresenter<VolumeView> {
    private VolumeView mView;
    @Inject
    VolumeModel         mModel;

    private List<ActivitySubCateBean<List<ActvityGoodInfoBean>>> mData;

    @Inject
    public VolumePresenter(VolumeView view) {
        mView = view;
    }

    public void loadData(){
        IActivityGoodsModel.ActivityRequestBody body = new IActivityGoodsModel.ActivityRequestBody();
        body.status = IActivityGoodsModel.Status.OPEN_AND_SHOW_HOME;
        body.activityId = 10;
        body.isShowGoods = 1;
        mModel.getActivityGoods(new OnLoadDataListener<BaseBean<List<ActivityCateBean<List<ActivitySubCateBean<List<ActvityGoodInfoBean>>>>>>>(mView.findLoadingSmartLayout()) {
            @Override
            public void onResponse(String body, BaseBean<List<ActivityCateBean<List<ActivitySubCateBean<List<ActvityGoodInfoBean>>>>>> data) {
                mData = data.data.get(0).subCate;
                mView.refreshList(mData);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<ActivityCateBean<List<ActivitySubCateBean<List<ActvityGoodInfoBean>>>>>>>(){}.getType();
            }
        },body);
    }

    public void openInfoPage(String id) {
        mView.openActivity(GoodInfoActivity.class,"id="+id);
    }
}
