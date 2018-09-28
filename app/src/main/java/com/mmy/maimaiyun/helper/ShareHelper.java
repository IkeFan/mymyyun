package com.mmy.maimaiyun.helper;

import android.content.Context;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.wxapi.WXEntryActivity;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * @创建者 lucas
 * @创建时间 2017/11/9 0009 10:35
 * @描述 分享帮助类
 */

public class ShareHelper {
    private Context mContext;

    public ShareHelper(Context context) {
        mContext = context;
    }

    public void showShare(String title, String content, String imgPath, String url) {
        //分享结束自动关闭微信分享授权界面--只针对微信
        WXEntryActivity.ACTION = WXEntryActivity.WX_SHARE;
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(title);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(content);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(imgPath);//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        //        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(mContext.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);

        // 启动分享GUI
        oks.show(mContext);
    }
}
