package com.mmy.maimaiyun.model.main.view;

import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.TimeLimitBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/25 0025 17:42
 * @描述 TODO
 */

public interface TimeLimitView extends IView {
    void refresTodayhList(List<TimeLimitBean.GoodInfo> today);
    void refresTomorrowhList(List<TimeLimitBean.GoodInfo> tomorrow);
    void refreshTime(long data);
}
