package com.mmy.maimaiyun.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.mmy.maimaiyun.R;
import com.squareup.picasso.Picasso;

/**
 * @创建者 lucas
 * @创建时间 2018/1/30 0030 16:08
 * @描述 图片预览
 */

public class ReviewPicPopup extends PopupWindow implements View.OnClickListener {

    private ImageView mImageView;
    private Context mContext;

    public ReviewPicPopup(Context context) {
        super(context);
        initView(context);
    }

    public ReviewPicPopup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        int matchParent = ViewGroup.LayoutParams.MATCH_PARENT;
        setWidth(matchParent);
        int wrapContent = ViewGroup.LayoutParams.MATCH_PARENT;
        setHeight(wrapContent);
        setBackgroundDrawable(new ColorDrawable(0x30000000));
        setFocusable(true);
        setOutsideTouchable(true);
        mImageView = new ImageView(context);
        mImageView.setOnClickListener(this);
        mImageView.setLayoutParams(new ViewGroup.LayoutParams(matchParent,wrapContent));
        mImageView.setImageResource(R.mipmap.ic_launcher_trad_food);
        setContentView(mImageView);
    }

    public void review(String url, View view){
        Picasso.with(mContext)
                .load(url)
                .error(R.mipmap.ic_launcher_trad_food)
                .placeholder(R.mipmap.ic_launcher_trad_food)
                .into(mImageView);
        showAtLocation(view, Gravity.CENTER,0,0);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
