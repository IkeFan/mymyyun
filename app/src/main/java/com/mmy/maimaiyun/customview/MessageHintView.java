package com.mmy.maimaiyun.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.utils.UIUtil;


/**
 * @创建者 lucas
 * @创建时间 2017/9/22 0022 17:27
 * @描述 未读消息提醒  --只能包含一个子控件
 */

public class MessageHintView extends RelativeLayout {

    private TextView mTextView;
    private int      mTextSize;
    private int      typedArrayInt;

    public static final int POINT  = 0;
    public static final int NUMBER = 1;

    public MessageHintView(Context context) {
        super(context);
        initView(context);
    }


    public MessageHintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MessageHintView);
        mTextSize = typedArray.getInteger(R.styleable.MessageHintView_text_size_sp, 15);
        typedArrayInt = typedArray.getInt(R.styleable.MessageHintView_type, POINT);
        typedArray.recycle();
        initView(context);
    }

    private void initView(Context context) {
        //添加小红点
        mTextView = new TextView(context);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setTextSize(mTextSize);
        mTextView.setBackgroundResource(R.drawable.circle_red);
        int dp15 = UIUtil.dp2px(context, 14);
        mTextView.setTextColor(getResources().getColor(R.color.white));
        float sp = UIUtil.px2sp(context, mTextSize);
        mTextView.setTextSize(sp);
        LayoutParams params = new LayoutParams(dp15, dp15);
        addView(mTextView);
        if (typedArrayInt == POINT) {
            int dp10 = UIUtil.dp2px(context, 7);
            params = new LayoutParams(dp10, dp10);
        }
        //向右对齐
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mTextView.setLayoutParams(params);
    }

    public void show() {
        setVisibility(VISIBLE);
    }

    public void hide() {
        setVisibility(INVISIBLE);
    }

    public void setMessageCount(int messageCount) {
        if (typedArrayInt == POINT)
            return;
        mTextView.setText(messageCount + "");
    }
}
