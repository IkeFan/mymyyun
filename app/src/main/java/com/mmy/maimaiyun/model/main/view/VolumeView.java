package com.mmy.maimaiyun.model.main.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.ActivitySubCateBean;
import com.mmy.maimaiyun.data.bean.ActvityGoodInfoBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 15:21
 * @描述 TODO
 */

public interface VolumeView extends IView {
    void refreshList(List<ActivitySubCateBean<List<ActvityGoodInfoBean>>> data);

}
