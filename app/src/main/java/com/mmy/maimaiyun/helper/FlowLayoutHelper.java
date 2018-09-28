package com.mmy.maimaiyun.helper;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.customview.FlowLayout;

/**
 * @创建者 lucas
 * @创建时间 2017/9/6 0006 15:26
 * @描述 流式布局帮助类
 */

public class FlowLayoutHelper {
    private boolean                 mIsSingle;//是否单选
    private FlowLayout              mLayout;
    private OnFlowItemClickListener mListener;

    public FlowLayoutHelper(Context context, boolean isSingle, FlowLayout layout, OnFlowItemClickListener listener) {
        mIsSingle = isSingle;
        mLayout = layout;
        mListener = listener;
        initEvent(context);
    }

    private void initEvent(Context context) {
        int childCount = mLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            int finalI = i;
            mLayout.getChildAt(i).setOnClickListener(v -> {
                if (mListener != null) {
                    mListener.onFlowItemClick(v, finalI);
                }
                v.setBackgroundResource(R.drawable.shape_orange_solid);
                TextView textView = (TextView) v;
                //多选
                if (!mIsSingle) {
                    boolean select = v.getTag()==null?false:(boolean) v.getTag();
                    select =!select;
                    v.setTag(select);
                    textView.setTextColor(context.getResources().getColor(select ? R.color.white : R.color
                            .hint_text_color));
                    v.setBackgroundResource(select ? R.drawable.shape_orange_solid : R.drawable.stroke_black_bg);
                    return;
                }
                //单选
                textView.setTextColor(context.getResources().getColor(R.color.white));
                //遍历其他item修改样式
                for (int j = 0; j < childCount; j++) {
                    if (j != finalI) {
                        View childAt = mLayout.getChildAt(j);
                        childAt.setBackgroundResource(R.drawable.stroke_black_bg);
                        ((TextView) childAt).setTextColor(context.getResources().getColor(R.color.hint_text_color));
                    }
                }
            });
        }
    }

    public interface OnFlowItemClickListener {
        void onFlowItemClick(View view, int position);
    }

}
