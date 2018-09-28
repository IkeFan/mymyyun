package com.mmy.maimaiyun.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/8/25 0025 14:07
 * @描述 选择器 window
 */

public class SelectPopupWindow extends PopupWindow {
    private Builder mBuilder;

    private SelectPopupWindow(Context context, Builder builder) {
        super(context);
        mBuilder = builder;
        initView(context, builder);
    }

    private void initView(final Context context, final Builder builder) {
        final LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundResource(R.drawable.bt_layer_gray);
        int px = UIUtil.dp2px(context, 2);
        linearLayout.setPadding(px, px, px, px);
        builder.mView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    builder.mView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                int measuredWidth = builder.mView.getMeasuredWidth();
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(measuredWidth, ViewGroup
                        .LayoutParams.WRAP_CONTENT);
                linearLayout.setLayoutParams(params);
                for (int i = 0; i < builder.data.size(); i++) {
                    View mMenuView = View.inflate(context, R.layout.popup_item_select, null);
                    TextView text = (TextView) mMenuView.findViewById(R.id.text);
                    text.setText(builder.data.get(i));
                    //添加分割线
                    linearLayout.addView(mMenuView);
                    final int finalI = i;
                    mMenuView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (builder.mListener != null) {
                                String s = builder.data.get(finalI);
                                builder.mListener.onItemClick(v, s,builder.data.indexOf(s));
                            }
                        }
                    });
                    if (i != builder.data.size() - 1) {
                        View view = new View(context);
                        view.setLayoutParams(new LinearLayout.LayoutParams(measuredWidth, 1));
                        view.setBackgroundColor(context.getResources().getColor(R.color.devider));
                        linearLayout.addView(view);
                    }
                }
            }
        });

        //设置SelectPicPopupWindow的View
        this.setContentView(linearLayout);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, String str, int index);
    }

    public void showPopup() {
        showAsDropDown(mBuilder.mView, 0, 0);
    }

    public static class Builder {
        List<String> data = new ArrayList<>();
        private Context             mContext;
        private View                mView;
        private OnItemClickListener mListener;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setOnItemClickListener(OnItemClickListener listener) {
            mListener = listener;
            return this;
        }

        //显示在该view下，宽度与该view一致
        public Builder relationView(View view) {
            mView = view;
            return this;
        }

        public Builder addItemText(String str) {
            data.add(str);
            return this;
        }

        public SelectPopupWindow build() {
            return new SelectPopupWindow(mContext, this);
        }
    }
}
