package com.mmy.maimaiyun;

import android.os.Handler;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.google.gson.Gson;
import com.mmy.maimaiyun.db.DaoMaster;
import com.mmy.maimaiyun.db.DaoSession;
import com.mmy.maimaiyun.helper.PayHelper;
import com.mmy.maimaiyun.helper.ShareHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/8/11 0011 16:18
 * @描述 全局常用对象
 */
@Module
public class AppModule {

    private App mApp;
    private String dbName = "mmy.db";

    public AppModule(App app) {
        mApp = app;
    }

    @Singleton
    @Provides
    PayHelper providePayHelper() {
        return new PayHelper();
    }


    @Singleton
    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Singleton
    @Provides
    Handler provideHandler() {
        return new Handler();
    }

    @Singleton
    @Provides
    App provideApp() {
        return mApp;
    }

    @Singleton
    @Provides
    AMapLocationClient provideAMapLocationClient(App app) {
        //初始化定位
        AMapLocationClient mLocationClient = new AMapLocationClient(app);
        AMapLocationClientOption locationOption = new AMapLocationClientOption();
        // 设置是否需要显示地址信息
        locationOption.setNeedAddress(true);
         /* 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位
        * 注意：只有在高精度模式下的单次定位有效，其他方式无效
        */
        locationOption.setGpsFirst(true);
        // 设置发送定位请求的时间间隔,最小值为1000，如果小于1000，按照1000算
        locationOption.setInterval(60 * 1000);
        return mLocationClient;
    }

    @Singleton
    @Provides
    DaoSession provideDaoSession(App app) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(app, dbName, null);
        DaoMaster daoMaster = new DaoMaster(helper.getReadableDb());
        return daoMaster.newSession();
    }

    @Singleton
    @Provides
    ShareHelper provideShareHelper(App app){
        return new ShareHelper(app);
    }

}
