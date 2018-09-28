package com.mmy.maimaiyun;

import android.os.Handler;

import com.amap.api.location.AMapLocationClient;
import com.google.gson.Gson;
import com.mmy.maimaiyun.data.api.ApiService;
import com.mmy.maimaiyun.data.api.module.ApiServiceModule;
import com.mmy.maimaiyun.db.DaoSession;
import com.mmy.maimaiyun.helper.MessageManager;
import com.mmy.maimaiyun.helper.PayHelper;
import com.mmy.maimaiyun.helper.ShareHelper;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * @创建者 lucas
 * @创建时间 2017/8/11 0011 16:58
 * @描述 TODO
 */
@Singleton
@Component(modules = {AppModule.class, ApiServiceModule.class})
public interface AppComponent {
    App getApp();
    Gson getGson();
    ApiService getApiService();
    Handler getHandler();
    AMapLocationClient getAMapLocationClient();
    MessageManager getMessageManager();
    PayHelper getPayHelper();
    DaoSession getDaoSession();
    OkHttpClient getOkHttpClient();
    Cache getCache();
    ShareHelper getShareHelper();
}
