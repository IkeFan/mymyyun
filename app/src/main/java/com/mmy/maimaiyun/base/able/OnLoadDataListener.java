package com.mmy.maimaiyun.base.able;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mmy.maimaiyun.customview.LoadingSmartLayout;

import java.lang.reflect.Type;

/**
 * @创建者 lucas
 * @创建时间 2017/8/11 0011 17:46
 * @描述 异步数据监听
 */

public abstract class OnLoadDataListener<T> {


    private LoadingSmartLayout mLayout;

    public OnLoadDataListener() {
    }

    public OnLoadDataListener(LoadingSmartLayout layout) {
        mLayout = layout;
    }

    @Deprecated
    public void onResponse(String body, T data) {
    }

    public void onResponse(String body, T data, IBean iBean) {
    }

    public abstract void onFailure(String body, Throwable t);

    public void onStart() {
    }

    public void onCompleted() {
    }

    /**
     * 获取Bean解析类型
     * 由于泛型擦除机制,Gson解析无法正确获取到泛型.
     *
     * @return
     */
    public abstract Type getBeanType();

    /**
     * 解析服务器返回的json数据
     * 为什么要放到监听里面来 -> 原先是放在BaseModel里的，由于获取Bean的泛型太过复杂，所以已至此处。
     *
     * @param body
     * @param gson
     * @param context
     */
    public void parseJson(String body, Gson gson, Context context) {
        T bean = null;
        String msg = null;
        IBean iBean = null;
        try {
            try {
                //使用IBean接口解析
                iBean = gson.fromJson(body, IBean.class);
            } catch (Exception e1) {
                LogUtils.d("lucas_net", "IBean 解析错误");
            }
            //原生解析出status
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(body);
            JsonObject parse = element.getAsJsonObject();
            //解析data
            JsonElement dataKey = parse.get("data");
            if (mLayout != null)
                if (iBean == null) {
                    //服务器错误
                    mLayout.switchStatus(LoadingSmartLayout.Status.ERROR);
                } else {
                    JsonArray data = null;
                    if (dataKey instanceof JsonArray)
                        data = (JsonArray) dataKey;
                    if (data == null) {
                        //非数组型数据
                        if (dataKey != null)
                            //有数据
                            mLayout.switchStatus(LoadingSmartLayout.Status.SUCCESS);
                        else
                            //没有数据
                            mLayout.switchStatus(LoadingSmartLayout.Status.EMPTY);
                    } else {
                        if (data.size() == 0) {
                            //没有数据
                            mLayout.switchStatus(LoadingSmartLayout.Status.EMPTY);
                        } else {
                            //有数据
                            mLayout.switchStatus(LoadingSmartLayout.Status.SUCCESS);
                        }
                    }
                }
            Type beanType = getBeanType();
            //对泛型进行解析
            try {
                if (beanType != null) {
                    bean = gson.fromJson(body, beanType);
                } else {
                    LogUtils.e("lucas_net", "getBeanType() retun null");
                }
            } catch (Exception e) {
                LogUtils.d("lucas_net", "gson 泛型 解析错误 json:" + body);
            }
            this.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.d("lucas_net", "gson解析错误");
            this.onFailure(body, e);
        }

        onResponse(body, bean);
        onResponse(body, bean, iBean);

    }

    public LoadingSmartLayout getLayout() {
        return mLayout;
    }

}
