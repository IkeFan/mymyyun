package com.mmy.maimaiyun.model.main.model;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;
import com.mmy.maimaiyun.data.bean.ActivityInfoBean;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017/10/20 0020 9:41
 * @描述 活动类的数据模型，I开头代表是公用的
 */

public abstract class IActivityGoodsModel extends BaseModel {

    public abstract String getActivityName();
    //参数表

    //活动控制
    public enum Status {
        CLOSE(0),//关闭活动
        OPEN(1),//开启活动
        OPEN_AND_SHOW_HOME(2);//开启活动并推荐到首页
        int value;

        Status(int value) {
            this.value = value;
        }
    }

    public static class ActivityRequestBody {
        public Status status         = Status.OPEN_AND_SHOW_HOME;
        public int    activityId     = 0;//活动id，默认，不获取活动数据
        public int    activityCateId = 0;//活动下分类ID，默认，不获取活动数据
        public int    isShowGoods    = 0;//是否显示活动，0不显示，1显示
        public int    limit          = 0;//商品数量
        public int    limitEnd       = 0;//分页
    }

    /**
     * 获取活动数据
     *
     * @param listener    回调
     * @param requestBody 参数表
     */
    public void getActivityGoods(OnLoadDataListener listener, ActivityRequestBody requestBody) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", requestBody.status.value);
        map.put("activity_id", requestBody.activityId);
        map.put("activity_cate_id", requestBody.activityCateId);
        map.put("is_goods", requestBody.isShowGoods);
        map.put("limit", requestBody.limit);
        map.put("limitEnd", requestBody.limitEnd);
        Call<ResponseBody> call = mApi.getActivity(map);
        wrapRequest(listener, call);
    }

    private List<ActivityInfoBean> mData;

    public void getActivityList(@NonNull OnLoadActivityIdListener listener) {
        getActivityList(listener, getActivityName());
    }

    public void getActivityList(@NonNull OnLoadActivityIdListener listener, String activityName) {
        getActivityList(listener, activityName, false);
    }

    /*
    在main里调用此方法，加载所有活动列表，并将列表信息存储在内存中
     */
    public void getActivityList(@NonNull OnLoadActivityIdListener listener, String activityName, boolean isReload) {
        if (TextUtils.isEmpty(activityName))
            throw new RuntimeException("activity name  = null?");

        if (!isReload)
            if (mData != null) {
                for (ActivityInfoBean bean : mData) {
                    if (activityName.equals(bean.title)) {
                        listener.onSuccess(bean.id);
                        return;
                    }
                }
                Log.d("lucas", "未找到活动ID");
            } else {
                Log.d("lucas", "未加载活动列表");
            }

        HashMap<String, Object> map = new HashMap<>();
        map.put("status", 2);
        Call<ResponseBody> call = mApi.getActivity(map);
        wrapRequest(new OnLoadDataListener<BaseBean<List<ActivityInfoBean>>>() {

            @Override
            public void onResponse(String body, BaseBean<List<ActivityInfoBean>> data, IBean iBean) {
                super.onResponse(body, data, iBean);
                if (iBean.status == 1 && data.data != null) {
                    mData = data.data;
                    for (ActivityInfoBean bean : mData) {
                        if (activityName.equals(bean.title)) {
                            listener.onSuccess(bean.id);
                            break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<ActivityInfoBean>>>() {
                }.getType();
            }
        }, call);
    }


    public interface OnLoadActivityIdListener {
        void onSuccess(int id);
    }
}
