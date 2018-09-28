package com.mmy.maimaiyun.model.main.ui.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.mmy.maimaiyun.App;
import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.helper.BottomNavigationViewHelper;
import com.mmy.maimaiyun.helper.MessageManager;
import com.mmy.maimaiyun.model.classity.ui.fragment.ClassityFragment;
import com.mmy.maimaiyun.model.main.component.DaggerMainComponent;
import com.mmy.maimaiyun.model.main.module.MainModule;
import com.mmy.maimaiyun.model.main.presenter.MainPresenter;
import com.mmy.maimaiyun.model.main.ui.fragment.HomeFragment;
import com.mmy.maimaiyun.model.main.view.MainView;
import com.mmy.maimaiyun.model.personal.ui.fragment.PersonalFragment;
import com.mmy.maimaiyun.model.shopping.ui.fragment.ShoppingFragment;
import com.mmy.maimaiyun.server.DownloadServer;
import com.mmy.maimaiyun.utils.CommonUtil;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    static final String HOME     = "home";
    static final String CLASSITY = "Classity";
    static final String SHOPPING = "Shopping";
    static final String PERSONAL = "Personal";
    String[] fragmentTags = {HOME, CLASSITY, SHOPPING, PERSONAL};
    private SweetAlertDialog mUpdateSweetAlertDialog;

    private DownloadServer.DownloadBind mBind;
    private String                      mPakPath;
    ServiceConnection mUpdateConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBind = (DownloadServer.DownloadBind) service;
            mBind.start(mPakPath);
            mBind.setOnDownloadListener(new DownloadServer.OnDownloadListener() {
                @Override
                public void onProgressUpdate(int progress) {

                }

                @Override
                public void onStart() {
                    showToast("进入后台更新中");
                }

                @Override
                public void onComplete(File apkFile) {
                    Log.d("MainActivity", "下载完成");
                    AppUtils.installApp(apkFile.getAbsoluteFile(), "com.mmy.maimaiyun.fileprovider");
                }

                @Override
                public void onError(Exception e) {
                    showToast("更新失败!");
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void showToast(String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        String tag = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                tag = HOME;
                break;
            case R.id.navigation_discover:
                tag = CLASSITY;
                break;
            case R.id.navigation_shopping:
                tag = SHOPPING;
                break;
            case R.id.navigation_my:
                tag = PERSONAL;
                mPresenter.refreshUserInfo();
                break;
        }
        for (String fragmentTag : fragmentTags) {
            Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(fragmentTag);
            if (!fragmentTag.equals(tag)) {
                //                Log.d("MainActivity", fragmentTag + " hide");
                getSupportFragmentManager().beginTransaction().hide(fragmentByTag).commit();
            } else {
                //                Log.d("MainActivity", fragmentTag + " show");
                getSupportFragmentManager().beginTransaction().show(fragmentByTag).commit();
            }
        }
        return true;
    };

    @Inject
    MessageManager mMessageManager;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build().inject(this);
    }

    //权限申请
    String[] permiss = {
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    public void initView() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().add(R.id.content, new ClassityFragment(), CLASSITY).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.content, new ShoppingFragment(), SHOPPING).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.content, new PersonalFragment(), PERSONAL).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.content, new HomeFragment(), HOME).commit();
        RxPermissions.getInstance(this)
                .request(Manifest.permission.CAMERA)
                .subscribe(s -> {
                    if (s) {

                    } else {
                        //拒绝
                    }
                });
        //申请GPS权限
        //        if (Build.VERSION.SDK_INT >= 23) {
        //如果超过6.0才需要动态权限，否则不需要动态权限
        //如果同时申请多个权限，可以for循环遍历
        //            for (String s : permiss) {
        //                int check = ContextCompat.checkSelfPermission(this, s);
        //                // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
        //                if (check == PackageManager.PERMISSION_GRANTED) {
        //                    //写入你需要权限才能使用的方法
        //                    //                run();
        //                } else {
        //手动去请求用户打开权限(可以在数组中添加多个权限) 1 为请求码 一般设置为final静态变量
        //                    requestPermissions(permiss, 1);
        //                }
        //            }
        //        }
    }

    private void checkVersion() {
        if (mUpdateSweetAlertDialog == null)
            mUpdateSweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("发现新版本")
                    .setContentText("发现新版本，是否立即更新？")
                    .setConfirmText("立即更新")
                    .setCancelText("取消更新")
                    .setCancelClickListener(sweetAlertDialog -> {
                        sweetAlertDialog.dismiss();
                        exitApp();
                    })
                    .setConfirmClickListener(sweetAlertDialog -> {
                        sweetAlertDialog.dismiss();
                        bindService(new Intent(MainActivity.this, DownloadServer.class), mUpdateConn, BIND_AUTO_CREATE);
                    });
        mUpdateSweetAlertDialog.show();
    }

    @Override
    public void initData() {
        //初始化融云
        mMessageManager.init(App.getAppComponent().getApp(), getUserBean());
        //判断渠道
        String metaData = CommonUtil.getMetaData(this, "UMENG_CHANNEL");
        LogUtils.d("当前渠道：" + metaData);
//        if ("_360".equals(metaData)) {
//            //360自更新--强制更新
//            UpdateHelper.getInstance().init(this, R.color.colorPrimary);
//            UpdateHelper.getInstance().autoUpdate(getPackageName(), true, 0 * 1000L);
//        } else {
//            mPresenter.checkVersion();
//        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void onLocationCompleted() {

    }

    @Override
    public void onVersionChange(String pakPath) {
        mPakPath = pakPath;
        checkVersion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (resultCode) {
                case 0x101://扫描返回结果
                    String qr_code = data.getStringExtra("qr_code");
                    Intent intent = new Intent(this, WebActivity.class);
                    intent.putExtra("qr_code", qr_code);
                    startActivity(intent);
                    break;
            }
        }
    }
}