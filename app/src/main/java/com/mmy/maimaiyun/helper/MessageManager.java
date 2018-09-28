package com.mmy.maimaiyun.helper;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.App;
import com.mmy.maimaiyun.AppModel;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.data.bean.UserBean;
import com.mmy.maimaiyun.data.bean.UserTokenBean;
import com.mmy.maimaiyun.utils.Constants;
import com.mmy.maimaiyun.utils.MathUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.inject.Inject;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

/**
 * @创建者 lucas
 * @创建时间 2017/9/22 0022 15:17
 * @描述 消息管理 ：用于管理即时聊天消息通知
 */

public class MessageManager {

    private ArrayList<OnReceiverMessageListener> mListeners;
    private Handler                              mHandler;

    AppModel mModel;

    @Inject
    public MessageManager(App context, Handler handler, AppModel model) {
        mModel = model;
        mHandler = handler;
        mListeners = new ArrayList<>();
    }

    //此方法必须在application中调用
    public void init(Context context, UserBean userBean) {
        String nonce = new Random().nextInt(10000) + "";
        String timestamp = System.currentTimeMillis() / 1000 + "";
        String signature = MathUtil.encryptToSHA(Constants.RONG_SECRET + nonce + timestamp);
        HashMap<String, String> map = new HashMap<>();
        map.put("App-Key", Constants.RONG_APPKEY);
        map.put("Nonce", nonce);
        map.put("Timestamp", timestamp);
        map.put("Signature", signature);
        //获取用户token
        mModel.getUserMsgToken(new OnLoadDataListener<UserTokenBean>() {
            @Override
            public void onResponse(String body, UserTokenBean data, IBean iBean) {
                super.onResponse(body, data, iBean);
                LogUtils.d("rong yun token:"+data.token);
                conn(context, data.token);
            }

            @Override
            public void onFailure(String body, Throwable t) {
                LogUtils.d("rong yun token: 获取失败");
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<UserTokenBean>() {
                }.getType();
            }
        }, map, userBean.member_id, userBean.nickname, TextUtils.isEmpty(userBean.head_pic) ? Constants.IC_DEF_USER :
                userBean.head_pic);
    }

    //链接
    private void conn(Context context, String token) {//创建容器
//        RongIM.setServerInfo("nav.cn.ronghub.com", "up.qbox.me");
        RongIM.init(context);
        //链接融云
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                LogUtils.d("token 错误");
            }

            @Override
            public void onSuccess(String s) {
                LogUtils.d("通讯链接成功");
//                Toast.makeText(context, "通讯链接成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                LogUtils.d("通讯链接失败");
//                Toast.makeText(context, "通讯链接失败", Toast.LENGTH_SHORT).show();
            }
        });
        //接受消息
        RongIM.setOnReceiveMessageListener((message, i) -> {
            mHandler.post(() -> notifyMessage(message));
            Log.d("App", "收到新消息:" + new String(message.getContent().encode()));
            Log.d("App", "SenderUserId:" + message.getSenderUserId());
            Log.d("App", "TargetId:" + message.getTargetId());
            return false;
        });
    }

    //通知
    private void notifyMessage(Message message) {
        for (OnReceiverMessageListener listener : mListeners) {
            listener.onReceiverMessage(message);
        }
    }

    public void register(OnReceiverMessageListener listener) {
        mListeners.add(listener);
    }

    public void unregister(OnReceiverMessageListener listener) {
        mListeners.remove(listener);
    }

    public interface OnReceiverMessageListener {
        void onReceiverMessage(Message message);
    }
}
