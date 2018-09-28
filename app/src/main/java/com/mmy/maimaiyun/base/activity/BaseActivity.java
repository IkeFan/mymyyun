package com.mmy.maimaiyun.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.customview.LoadingSmartLayout;
import com.mmy.maimaiyun.data.bean.UserBean;
import com.mmy.maimaiyun.model.login.activity.LoginActivity;
import com.mmy.maimaiyun.utils.GlobalUtil;
import com.umeng.message.PushAgent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.mmy.maimaiyun.App.getAppComponent;


/**
 * @创建者 lucas
 * @创建时间 2017/8/7 0007 14:43
 * @描述 activity 基类
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IView {

    private static ArrayList<BaseActivity> mActivities = new ArrayList<>();
    private SweetAlertDialog mReloginAlert;
    private UserBean         mUserBean;

    @Inject
    protected P       mPresenter;
    @Inject
    protected Handler mHandler;

    private SweetAlertDialog mLoadingDialog;
    private boolean          mIsCanShowWindow;
    private SweetAlertDialog mExitAlert;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (registerEventBus())
            EventBus.getDefault().register(this);
        PushAgent.getInstance(this).onAppStart();
        if (isFullscreen()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        if (!initRefreshView()) {
            int contentViewId = getContentViewId();
            if (contentViewId != 0)
                setContentView(contentViewId);
        }
        ButterKnife.bind(this);
        initDagger(getAppComponent());
        if (!mActivities.contains(this)) {
            mActivities.add(this);
        }
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
        commonInit();
        initView();
        initData();
        initEvent();
    }

    public boolean registerEventBus(){
        return false;
    }

    /**
     * 刷新控件的初始化
     *
     * @return 决定setContentView在哪调用
     */
    protected boolean initRefreshView() {
        return false;
    }

    public boolean isFullscreen() {
        return false;
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        for (BaseActivity a : mActivities) {
            a.finish();
        }
        System.exit(0);
    }

    private void commonInit() {
        if (mExitAlert == null)
            mExitAlert = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("提示")
                    .setContentText("您是否确认推出中国传统食品")
                    .setCancelText("取消")
                    .setConfirmText("确认")
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                        }
                    })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            finish();
                            System.exit(0);
                        }
                    });

        if (mReloginAlert == null)
            mReloginAlert = new SweetAlertDialog(getAppComponent().getApp(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("警告!")
                    .setContentText("您的账号在其他设备上登录,是否重新登录或修改密码?")
                    .setConfirmText("修改密码")
                    .setCancelText("修改密码")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            // TODO: 2017/8/18 0018 修改密码
                            BaseActivity.this.finish();
                        }
                    })
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            //重新登录
                            GlobalUtil.getInstance().clearUserInfo();
                            BaseActivity.this.startActivity(new Intent(BaseActivity.this, LoginActivity.class));
                            BaseActivity.this.finish();
                        }
                    });
        if (mLoadingDialog == null)
            mLoadingDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        //title返回键退出
        View back = findViewById(R.id.title_back);
        if (back != null) {
            back.setOnClickListener(v -> finish());
        }
    }

    protected abstract void initDagger(AppComponent appComponent);

    public abstract void initView();

    public abstract void initData();

    public void initEvent() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivities.remove(this);
        if (mPresenter != null) {
            mPresenter.detach();
        }

        if (mLoadingDialog != null && mLoadingDialog.isShowing())
            mLoadingDialog.dismiss();

        if (registerEventBus())
            EventBus.getDefault().unregister(this);
    }

    private long mExitTime;

    /**
     * 按两次退出应用
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mActivities.size() == 1) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
//                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();
                } else {
                    if (mExitAlert != null) {
                        mExitAlert.show();
                    }
//                    finish();
//                    System.exit(0);
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public abstract int getContentViewId();

    @Override
    public void showLoading(String msg) {
        if (!mIsCanShowWindow) {
            return;
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (!mLoadingDialog.isShowing() && !isFinishing())
                    mLoadingDialog.setTitleText(msg);
                mLoadingDialog.show();
            }
        });
    }

    @Override
    public void showLoading() {
        if (!mIsCanShowWindow) {
            return;
        }
        mLoadingDialog.setTitleText("加载中..");
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (!mLoadingDialog.isShowing() && !isFinishing())
                    mLoadingDialog.show();
            }
        });
    }

    @Override
    public void hideLoading() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mLoadingDialog != null && mLoadingDialog.isShowing() && !isFinishing())
                    mLoadingDialog.dismiss();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        mIsCanShowWindow = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mIsCanShowWindow = false;
    }

    @Override
    public UserBean getUserBean() {
        if (!GlobalUtil.getInstance().isExistUserInfo(this) && mUserBean == null)
            mHandler.post(() -> mReloginAlert.show());
        return mUserBean;
    }

    @Override
    public void setUserBean(UserBean bean) {
        mUserBean = bean;
    }

    public void closeAllActivity() {
        for (BaseActivity a : mActivities) {
            a.finish();
        }
    }

    //打开一个activity
    @Override
    public <A extends BaseActivity> void openActivity(Class<A> activityClass) {
        startActivity(new Intent(this, activityClass));
    }

    //打开一个activity并且传入参数 格式 ：key=value,key=value...
    @Override
    public <A extends BaseActivity> void openActivity(Class<A> activityClass, String param) {
        Intent intent = new Intent(this, activityClass);
        String[] p = param.split(",");
        for (String s : p) {
            String[] split = s.split("=");
            String key = split[0];
            String value = split[1];
            intent.putExtra(key, value);
        }
        startActivity(intent);
    }

    @Override
    public void finishSelf() {
        finish();
    }

    @Override
    public LoadingSmartLayout findLoadingSmartLayout() {
        View view = findViewById(R.id.loading_smart_layout);
        if (view != null)
            if (view instanceof LoadingSmartLayout)
                return (LoadingSmartLayout) view;
        return null;
    }

    //    @Override
    //    protected void onResume() {
    //        super.onResume();
    //        MobclickAgent.onResume(this);
    //    }
    //
    //    @Override
    //    protected void onPause() {
    //        super.onPause();
    //        MobclickAgent.onPause(this);
    //    }
}
