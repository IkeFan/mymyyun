package com.mmy.maimaiyun.helper;

import com.google.gson.Gson;
import com.mmy.maimaiyun.data.bean.MsgBean;

import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;

/**
 * @创建者 lucas
 * @创建时间 2017/9/20 0020 14:26
 * @描述 即时通讯会话帮助
 */

public class ConversationHelper {

    private static ConversationHelper helper;

    private ConversationHelper() {
    }

    public static ConversationHelper getInstance() {
        if (helper == null)
            synchronized (ConversationHelper.class) {
                if (helper == null)
                    helper = new ConversationHelper();
            }
        return helper;
    }

    //解析消息
    public static String parseMsg(Conversation conversation){
        MessageContent message = conversation.getLatestMessage();
        String msg = new String(message.encode());
        MsgBean msgBean = new Gson().fromJson(msg, MsgBean.class);
        return msgBean.content;
    }

    //解析消息
    public static String parseMsg(Message message){
        String msg = new String(message.getContent().encode());
        MsgBean msgBean = new Gson().fromJson(msg, MsgBean.class);
        return msgBean.content;
    }
}
