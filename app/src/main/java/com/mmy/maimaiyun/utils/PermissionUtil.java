package com.mmy.maimaiyun.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

/**
 * @创建者 lucas
 * @创建时间 2017/12/22 0022 10:25
 * @描述 6.0以上申请权限
 */

public class PermissionUtil {

    public static void requestPermission(Activity activity, String permissionName) {
        //申请权限
        if (Build.VERSION.SDK_INT >= 23) {
            //如果超过6.0才需要动态权限，否则不需要动态权限
            //如果同时申请多个权限，可以for循环遍历
            int check = ContextCompat.checkSelfPermission(activity, permissionName);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (check == PackageManager.PERMISSION_GRANTED) {
                //写入你需要权限才能使用的方法
                //                run();
            } else {
                //手动去请求用户打开权限(可以在数组中添加多个权限) 1 为请求码 一般设置为final静态变量
                activity.requestPermissions(new String[]{permissionName}, 1);
            }
        }
    }

}
