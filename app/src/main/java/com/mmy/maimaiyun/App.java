package com.mmy.maimaiyun;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.Utils;
import com.mmy.maimaiyun.data.api.module.ApiServiceModule;
import com.mmy.maimaiyun.helper.MessageManager;
import com.mob.MobSDK;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import java.util.ArrayList;

/**
 * @创建者 lucas
 * @创建时间 2017/8/7 0007 14:32
 * @描述 应用入口
 */

public class App extends MultiDexApplication implements AMapLocationListener {

    //应用级组件
    private static AppComponent       mAppComponent;
    private        AMapLocationClient mAMapLocationClient;
    ArrayList<OnAppLocationListenner> mLocationListeners = new ArrayList<>();

    public MessageManager getMessageManager() {
        return mMessageManager;
    }

    private MessageManager mMessageManager;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
        Utils.init(this);
        MobSDK.init(this);
        //bugly
        CrashReport.initCrashReport(getApplicationContext(), BuildConfig.BUGLY_KEY, false);
        umengInit();
        mMessageManager = mAppComponent.getMessageManager();
        mAppComponent.getPayHelper().init(this);
    }

    private void umengInit() {
        //友盟统计
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "76a5695d8fe02b34845627ce4271db3a");
        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(true);
        /**
         * 设置日志加密
         * 参数：boolean 默认为false（不加密）
         */
        UMConfigure.setEncryptEnabled(true);
        //推送
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.d("App", "友盟推送 token:" + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
    }

    public int locationCount = 6;//定位次数

    //开启定位
    public void registerLocationListener(@NonNull OnAppLocationListenner listener) {
        if (mAMapLocationClient == null) {
            mAMapLocationClient = mAppComponent.getAMapLocationClient();
            AMapLocationClientOption locationOption = new AMapLocationClientOption();
            mAMapLocationClient.setLocationOption(locationOption);
            // 设置是否需要显示地址信息
            locationOption.setNeedAddress(true);
            /* 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位
            * 注意：只有在高精度模式下的单次定位有效，其他方式无效
            */
            locationOption.setGpsFirst(true);
            // 设置发送定位请求的时间间隔,最小值为1000，如果小于1000，按照1000算
            locationOption.setInterval(1000 * 3);
            mAMapLocationClient.setLocationListener(this);
        }
        //每次添加事件都重新启动定位
        if (mAMapLocationClient.isStarted())
            mAMapLocationClient.stopLocation();
        mAMapLocationClient.startLocation();
        locationCount = 6;

        mLocationListeners.add(listener);
    }

    public void unregisterLocationListener(@NonNull OnAppLocationListenner listener) {
        mLocationListeners.remove(listener);
    }

    private void initDagger() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule(new ApiServiceModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        MultiDex.install(this);
        super.attachBaseContext(base);
    }

    public interface OnAppLocationListenner {
        void onLocationSuccess(String city);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        String city = aMapLocation.getCity();
        city = TextUtils.isEmpty(city) ? "定位中.." : city;
        Log.d("App", "city:" + city);
        if (--locationCount < 0) {
            if (mAMapLocationClient.isStarted())
                mAMapLocationClient.stopLocation();
            city = "定位失败";
        } else {
            //定位成功，停止定位
            if (!TextUtils.isEmpty(aMapLocation.getCity()))
                if (mAMapLocationClient.isStarted())
                    mAMapLocationClient.stopLocation();
        }
        for (OnAppLocationListenner listener : mLocationListeners) {
            listener.onLocationSuccess(city);
        }
    }
}
