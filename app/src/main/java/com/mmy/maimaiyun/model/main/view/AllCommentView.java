package com.mmy.maimaiyun.model.main.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.CommentBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 11:47
 * @描述 TODO
 */

public interface AllCommentView extends IView {
    void refreshList(List<CommentBean> data);
}
