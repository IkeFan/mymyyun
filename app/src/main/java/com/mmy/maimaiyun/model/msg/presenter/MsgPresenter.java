package com.mmy.maimaiyun.model.msg.presenter;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.MessageBean;
import com.mmy.maimaiyun.model.msg.view.MsgView;

import java.util.ArrayList;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/18 0018 10:28
 * @描述 TODO
 */

public class MsgPresenter extends BasePresenter<MsgView> {
    private MsgView mView;
    @Inject
    public MsgPresenter(MsgView view) {
        mView = view;
    }

    public void loadData(){
        long millis = System.currentTimeMillis();
        ArrayList<MessageBean> list = new ArrayList<>();
        list.add(new MessageBean(R.mipmap.msg_01,"订单通知","",millis,0));
        list.add(new MessageBean(R.mipmap.msg_02,"系统消息","",millis,0));
        list.add(new MessageBean(R.mipmap.msg_03,"客服消息","",millis,0));
        mView.refreshList(list);
    }
}
