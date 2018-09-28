package com.mmy.maimaiyun.model.personal.presenter;

import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.customview.LoadingSmartLayout;
import com.mmy.maimaiyun.data.bean.TradeRecordBean;
import com.mmy.maimaiyun.model.personal.model.TradeRecordModel;
import com.mmy.maimaiyun.model.personal.view.TradeRecordView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/19 0019 11:44
 * @描述 TODO
 */

public class TradeRecordPresenter extends BasePresenter<TradeRecordView> {
    private TradeRecordView mView;
    @Inject
    TradeRecordModel mModel;
    private List<TradeRecordBean> mData;
    private LinkedHashMap<Integer, String> mMap = new LinkedHashMap<>();

    @Inject
    public TradeRecordPresenter(TradeRecordView view) {
        mView = view;
    }

    int page = 0;

    public void loadData(boolean isLoadMore) {
        LoadingSmartLayout layout = mView.findLoadingSmartLayout();
        if (isLoadMore){
            page++;
            layout = null;
        }
        else
            page = 0;
        mModel.loadBillData(new OnLoadDataListener<BaseBean<List<TradeRecordBean>>>(layout) {
            @Override
            public void onResponse(String body, BaseBean<List<TradeRecordBean>> data, IBean iBean) {
                mData = data.data;
                mMap.put(9999,"全部记录");
                for (TradeRecordBean bean : mData) {
                    mMap.put(bean.type,bean.desc);
                }
                mView.refreshMenuList(mMap);
                mView.refreshList(mData,isLoadMore);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<TradeRecordBean>>>() {
                }.getType();
            }
        }, mView.getUserBean().token,page, mView.getUserBean().member_id);
    }

}
