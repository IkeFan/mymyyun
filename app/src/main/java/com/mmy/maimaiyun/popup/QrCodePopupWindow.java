package com.mmy.maimaiyun.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.utils.UIUtil;


/**
 * @创建者 lucas
 * @创建时间 2017/8/25 0025 14:07
 * @描述 二维码 window
 */

public class QrCodePopupWindow extends PopupWindow {
    private Builder mBuilder;

    private QrCodePopupWindow(Context context, Builder builder) {
        super(context);
        mBuilder = builder;
        initView(context, builder);
    }

    private void initView(final Context context, final Builder builder) {
        View inflate = View.inflate(context, R.layout.popup_qrcode, null);
        inflate.setOnClickListener(v -> dismiss());
        ImageView qr_img = (ImageView) inflate.findViewById(R.id.qr_img);
        qr_img.setImageBitmap(UIUtil.createQRCode(builder.mQRStr,UIUtil.dp2px(context, (int) (ScreenUtils.getScreenWidth()*0.7))));
        TextView hint = (TextView) inflate.findViewById(R.id.qr_hint);
//        hint.setText(builder.mHintStr);
        //设置SelectPicPopupWindow的View
        this.setContentView(inflate);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x66000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
    }

    public static class Builder {
        private Context             mContext;
        private View                mView;
        private String mQRStr;
        private String mHintStr;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setQrText(String str){
            mQRStr = str;
            return this;
        }

        public Builder setHintText(String str) {
            mHintStr = str;
            return this;
        }

        public QrCodePopupWindow build() {
            return new QrCodePopupWindow(mContext, this);
        }
    }
}
