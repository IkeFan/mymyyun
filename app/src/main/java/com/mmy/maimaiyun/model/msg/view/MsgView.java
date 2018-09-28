package com.mmy.maimaiyun.model.msg.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.MessageBean;

import java.util.ArrayList;


/**
 * @创建者 lucas
 * @创建时间 2017/9/18 0018 10:27
 * @描述 TODO
 */

public interface MsgView extends IView {
    void refreshList(ArrayList<MessageBean> data);
}
