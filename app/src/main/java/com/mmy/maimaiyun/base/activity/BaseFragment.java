package com.mmy.maimaiyun.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.App;
import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.customview.LoadingSmartLayout;
import com.mmy.maimaiyun.data.bean.UserBean;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @创建者 lucas
 * @创建时间 2017/8/17 0017 11:19
 * @描述 碎片基类
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IView {

    @Inject
    protected P mPresenter;
    private SweetAlertDialog mLoadingDialog;
    protected BaseActivity mActivity;
    @Inject
    protected App mApp;
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        if (registerEventBus())
            EventBus.getDefault().register(this);
        initDagger(App.getAppComponent());
        mRootView = View.inflate(getActivity(), getLayoutID(), null);
        ButterKnife.bind(this, mRootView);
        mActivity = (BaseActivity) getActivity();
        commonInit();
        initView();
        initData();
        initEvent();
        return mRootView;
    }

    public boolean registerEventBus(){
        return false;
    }

    private void commonInit() {
        mLoadingDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("拼命加载中..");
    }

    protected abstract void initView();

    protected void initEvent() {
    }

    protected abstract void initData();

    protected abstract int getLayoutID();

    protected abstract void initDagger(AppComponent appComponent);

    @Override
    public void showLoading() {
        if (!mLoadingDialog.isShowing())
            mLoadingDialog.show();
    }

    @Override
    public void showLoading(String msg) {
        if (!mLoadingDialog.isShowing())
            mLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog.isShowing())
            mLoadingDialog.dismiss();
    }

    @Override
    public UserBean getUserBean() {
        return mActivity.getUserBean();
    }

    @Override
    public void setUserBean(UserBean bean) {
        mActivity.setUserBean(bean);
    }

    //打开一个activity
    @Override
    public <A extends BaseActivity> void openActivity(Class<A> activityClass){
        startActivity(new Intent(mActivity,activityClass));
    }

    //打开一个activity并且传入参数 格式 ：key=value,key=value...
    @Override
    public <A extends BaseActivity> void openActivity(Class<A> activityClass,String param){
        Intent intent = new Intent(mActivity, activityClass);
        String[] p = param.split(",");
        for (String s : p) {
            String[] split = s.split("=");
            String key = split[0];
            String value = split[1];
            intent.putExtra(key,value);
        }
        startActivity(intent);
    }

    @Override
    public void finishSelf() {}

    @Override
    public LoadingSmartLayout findLoadingSmartLayout() {
        View view = mRootView.findViewById(R.id.loading_smart_layout);
        if (view != null)
            if (view instanceof LoadingSmartLayout)
                return (LoadingSmartLayout) view;
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (registerEventBus())
            EventBus.getDefault().unregister(this);
    }
}
