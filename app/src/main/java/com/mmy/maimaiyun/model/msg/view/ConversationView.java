package com.mmy.maimaiyun.model.msg.view;

import com.mmy.maimaiyun.base.mvp.IView;

import java.util.List;

import io.rong.imlib.model.Message;


/**
 * @创建者 lucas
 * @创建时间 2017/9/21 0021 16:37
 * @描述 TODO
 */

public interface ConversationView extends IView {
    void refreshList(List<Message> data);
}
