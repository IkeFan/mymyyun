package com.mmy.maimaiyun.model.main.presenter;

import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.BannerBean;
import com.mmy.maimaiyun.data.bean.OverseasClassBean;
import com.mmy.maimaiyun.data.bean.OverseasGoodBean;
import com.mmy.maimaiyun.data.bean.SubCateBean;
import com.mmy.maimaiyun.data.bean.SubGoodsInfoBean;
import com.mmy.maimaiyun.model.main.model.IActivityGoodsModel;
import com.mmy.maimaiyun.model.main.model.IBannersModel;
import com.mmy.maimaiyun.model.main.model.OverseasShoppingModel;
import com.mmy.maimaiyun.model.main.ui.activity.GoodInfoActivity;
import com.mmy.maimaiyun.model.main.ui.activity.OverseasShoppingActivity;
import com.mmy.maimaiyun.model.main.view.OverseasShoppingView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/1 0001 16:16
 * @描述 TODO
 */

public class OverseasShoppingPresenter extends BasePresenter<OverseasShoppingView> {
    private OverseasShoppingView mView;
    @Inject
    OverseasShoppingModel mModel;
    @Inject
    IBannersModel         mIBannersModel;


    private final OverseasShoppingActivity mActivity;
    private       List<SubCateBean>        mClassBeen;
    private       List<SubGoodsInfoBean>   mData;

    @Inject
    public OverseasShoppingPresenter(OverseasShoppingView view) {
        mView = view;
        mActivity = (OverseasShoppingActivity) view;
    }

    public void loadBanner() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", mActivity.getUserBean().token);
        map.put("position", IBannersModel.BannerType.OVERSEAS.value);
        mIBannersModel.getBanners(new OnLoadDataListener<BaseBean<List<BannerBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<BannerBean>> data) {
                if (data.data.size() != 0)
                    mView.showBanner(data.data);
            }

            @Override
            public void onFailure(String body, Throwable t) {
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<BannerBean>>>() {
                }.getType();
            }
        }, map);
    }

    public void loadClass() {
        IActivityGoodsModel.ActivityRequestBody body = new IActivityGoodsModel.ActivityRequestBody();
        body.status = IActivityGoodsModel.Status.OPEN_AND_SHOW_HOME;
        body.activityId = 18;
        mModel.getActivityGoods(new OnLoadDataListener<BaseBean<List<OverseasClassBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<OverseasClassBean>> data, IBean iBean) {
                if (iBean.status == 1) {
                    if (!data.data.isEmpty()) {
                        mClassBeen = data.data.get(0).subCate;
                        mView.refreshClass(mClassBeen);
                        if (mClassBeen.size() > 0)
                            loadGoodsData(mClassBeen.get(0));
                    }
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {
            }


            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<OverseasClassBean>>>() {
                }.getType();
            }
        }, body);

    }

    public void loadGoodsData(SubCateBean cat_id) {
        IActivityGoodsModel.ActivityRequestBody body = new IActivityGoodsModel.ActivityRequestBody();
        body.status = IActivityGoodsModel.Status.OPEN_AND_SHOW_HOME;
        body.activityId = 18;
        body.activityCateId = cat_id.id;
        body.isShowGoods = 1;
        mModel.getActivityGoods(new OnLoadDataListener<BaseBean<List<OverseasGoodBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<OverseasGoodBean>> data, IBean iBean) {
                if (iBean.status == 1) {
                    mData = data.data.get(0).subCate.get(0).subGoodsInfo;
                    mView.refreshGoods(mData);
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {
            }


            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<OverseasGoodBean>>>() {
                }.getType();
            }
        }, body);
    }

    public void onTabSelected(int position) {
        if (mClassBeen == null) {
            loadClass();
            return;
        }
        loadGoodsData(mClassBeen.get(position));
    }

    public void openInfoPage(int position) {
        mView.openActivity(GoodInfoActivity.class, "id=" + mData.get(position).id);
    }
}
