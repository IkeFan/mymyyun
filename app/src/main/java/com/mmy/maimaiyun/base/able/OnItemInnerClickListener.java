package com.mmy.maimaiyun.base.able;

import android.view.View;

/**
 * @创建者 lucas
 * @创建时间 2017/9/16 0016 16:55
 * @描述 监听item内部的事件
 */

public interface OnItemInnerClickListener {
    void onItemInnerClick(View view, int position, int type);
}
