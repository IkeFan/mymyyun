package com.mmy.maimaiyun.model.main.presenter;

import android.app.Activity;
import android.content.Intent;

import com.mmy.maimaiyun.model.main.ui.activity.NewsInfoActivity;
import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.GoodNewsClassBean;
import com.mmy.maimaiyun.data.bean.GoodNewsItemBean;
import com.mmy.maimaiyun.model.main.model.GoodNewsModel;
import com.mmy.maimaiyun.model.main.view.GoodNewsView;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/4 0004 9:51
 * @描述 TODO
 */

public class GoodNewsPresenter extends BasePresenter<GoodNewsView> {
    private GoodNewsView mView;

    @Inject
    GoodNewsModel mModel;
    private List<GoodNewsClassBean> mClassBeen;
    private List<GoodNewsItemBean> mData;
    private final Activity mActivity;

    @Inject
    public GoodNewsPresenter(GoodNewsView  view) {
        mView = view;
        mActivity = (Activity) view;
    }

    public void loadClass(){
        mModel.loadClass(new OnLoadDataListener<BaseBean<List<GoodNewsClassBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<GoodNewsClassBean>> data) {
                mClassBeen = data.data;
                mView.refreshClass(mClassBeen);
                if (data.data.size()>0)
                    loadNewsData(0);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }
            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<GoodNewsClassBean>>>(){}.getType();
            }
        },mView.getUserBean().token);
    }

    public void loadNewsData(int position){
      mModel.loadList(new OnLoadDataListener<BaseBean<List<GoodNewsItemBean>>>() {
          @Override
          public void onResponse(String body, BaseBean<List<GoodNewsItemBean>> data) {
              mData = data.data;
              mView.refreshList(mData,position);
          }

          @Override
          public void onFailure(String body, Throwable t) {

          }

          @Override
          public Type getBeanType() {
              return new TypeToken<BaseBean<List<GoodNewsItemBean>>>(){}.getType();
          }
      },mView.getUserBean().token,mClassBeen.get(position).cat_id);
    }

    public void openNewsInfoPage(int index,int position){
        if (position==0)return;
        if (mData!=null){
            GoodNewsItemBean bean = mData.get(position-1);
            Intent intent = new Intent(mActivity, NewsInfoActivity.class);
            intent.putExtra("bean",bean);
            mActivity.startActivity(intent);
        }
    }
}
