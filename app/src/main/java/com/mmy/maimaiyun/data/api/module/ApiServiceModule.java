package com.mmy.maimaiyun.data.api.module;

import android.os.Handler;

import com.mmy.maimaiyun.App;
import com.mmy.maimaiyun.AppModel;
import com.mmy.maimaiyun.data.api.ApiService;
import com.mmy.maimaiyun.helper.MessageManager;
import com.mmy.maimaiyun.utils.Constants;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @创建者 lucas
 * @创建时间 2017/8/11 0011 16:48
 * @描述 网络配置
 */
@Module
public class ApiServiceModule {


    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(Cache cache) {
        return new OkHttpClient.Builder()
                .connectTimeout(Constants.CONN_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .readTimeout(Constants.READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .writeTimeout(Constants.READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .cache(cache)
                .build();
    }

    @Singleton
    @Provides
    Cache provideCache(App app){
        return new Cache(app.getCacheDir(), Constants.CACHE_SIZE);
    }


    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Constants.BASE_TEST_URL)
                .build();
    }

    @Singleton
    @Provides
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Singleton
    @Provides
    MessageManager provideMessageManager(App app, Handler handler, AppModel model) {
        return new MessageManager(app, handler,model);
    }
}
