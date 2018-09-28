package com.mmy.maimaiyun.base.mvp;

import android.os.Handler;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.google.gson.Gson;
import com.mmy.maimaiyun.App;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.customview.LoadingSmartLayout;
import com.mmy.maimaiyun.data.api.ApiService;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @创建者 lucas
 * @创建时间 2017/8/11 0011 17:44
 * @描述 数据访问基本封装
 */

public abstract class BaseModel {

    @Inject
    Handler mHandler;

    @Inject
    protected ApiService mApi;
    @Inject
    protected Gson       mGson;
    @Inject
    protected App        mApp;

    //加载数据
    public void load(OnLoadDataListener listener) {}

    //其他数据类型上传
    public void pushData2(OnLoadDataListener listener, HashMap<String, Object> data) {
    }

    //普通数据上传
    public void pushData(OnLoadDataListener listener, HashMap<String, String> data) {}

    //请求网络细节
    protected String getResponseBody(Call<ResponseBody> call, OnLoadDataListener listener) {
        String body = null;
        try {
            Response<ResponseBody> execute = call.execute();
            int code = execute.code();
            //判断状态码
            if (code != 200) {
                LogUtils.d("lucas_net", "code:" + code);
                if (code >= 400)
                    Toast.makeText(mApp, "服务器错误 " + code, Toast.LENGTH_SHORT).show();
                listener.onFailure(null, new RuntimeException("code:" + code));
                return body;
            }
            ResponseBody responseBody = execute.body();
            body = responseBody.string();
            LogUtils.d("lucas_net", "url:" + call.request().toString() + "\n json:" + body);
        } catch (SocketTimeoutException e) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mApp, "服务器无响应", Toast.LENGTH_SHORT).show();
                    listener.onFailure(null, e);
                }
            });
            LogUtils.e("lucas_net", "服务器无响应");
            call.cancel();
            return body;
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.e("lucas_net", "服务器错误");
            listener.onFailure(null, e);
        }
        return body;
    }

    //通用网络请求封装
    protected void wrapRequest(final OnLoadDataListener listener, Call<ResponseBody> call) {
        //显示加载中
        LoadingSmartLayout layout = listener.getLayout();
        if (layout != null) {
            layout.switchStatus(LoadingSmartLayout.Status.LOADING);
            layout.getRetry().setOnClickListener((v) -> wrapRequest(listener, call));
        }
        //判断网络是否可用
        if (!NetworkUtils.isConnected()) {
            if (layout != null) {
                layout.switchStatus(LoadingSmartLayout.Status.NOT_NETWORK);
            }
            LogUtils.d("lucas_net", "网络不可用");
            listener.onFailure(null, new Exception("网络不可用"));
            Toast.makeText(mApp, "无网络", Toast.LENGTH_LONG).show();
            return;
        }
        Observable.just(call)
                .map(new Func1<Call<ResponseBody>, String>() {
                    @Override
                    public String call(Call<ResponseBody> requestCall) {
                        listener.onStart();
                        String responseBody = getResponseBody(requestCall, listener);
                                return responseBody;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String body) {
                        listener.parseJson(body, mGson, mApp);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        listener.onFailure(null, throwable);
                        LogUtils.e("lucas_net", "rxjava内部函数异常");
                        throwable.printStackTrace();
                    }
                });
    }

}
