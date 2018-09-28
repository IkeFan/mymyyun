package com.mmy.maimaiyun.model.personal.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.db.RecordBean;

import java.util.ArrayList;


/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 9:45
 * @描述 TODO
 */

public interface BrowseHistoryView extends IView {
    void refreshList(ArrayList<RecordBean> data);
}
