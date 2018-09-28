package com.mmy.maimaiyun.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.customview.FlowLayout;
import com.mmy.maimaiyun.data.bean.ScreeningBean;
import com.mmy.maimaiyun.helper.FlowLayoutHelper;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @创建者 lucas
 * @创建时间 2017/10/13 0013 9:46
 * @描述 商品筛选属性选择
 */

public class SelectedPopup extends PopupWindow {
    @Bind(R.id.attrs_container)
    LinearLayout mAttrsContainer;
    @Bind(R.id.root)
    View         mRoot;
    private Context mContext;
    private int     mPx;
    HashMap<String, String> mAttrValues = new HashMap<>();
    private int mTag;

    public SelectedPopup(Context context) {
        super(context);
        initView(context);
    }

    public SelectedPopup(Context context,int tag) {
        super(context);
        mTag = tag;
        initView(context);
    }

    public SelectedPopup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public int getTag(){
        return mTag;
    }

    private void initView(Context context) {
        mPx = UIUtil.dp2px(context, 8);
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.popup_selected, null);
        ButterKnife.bind(this, view);
        setContentView(view);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(0x00ffffff));
        setOutsideTouchable(false);
        setFocusable(true);
    }

    public void setData(List<ScreeningBean> data) {
        mAttrsContainer.removeAllViews();
        for (ScreeningBean bean : data) {
            View item = LayoutInflater.from(mContext).inflate(R.layout.item_popup_item, null);
            TextView name = (TextView) item.findViewById(R.id.class_name);
            name.setText(bean.name);
            FlowLayout flowLayout = (FlowLayout) item.findViewById(R.id.attrs);
            List<ScreeningBean.ContentBean> content = bean.content;
            for (ScreeningBean.ContentBean s : content) {
                TextView textView = new TextView(mContext);
                textView.setPadding(mPx, mPx, mPx, mPx);
                textView.setGravity(Gravity.CENTER);
                textView.setText(s.name);
                textView.setTextColor(mContext.getResources().getColor(R.color.hint_text_color));
                textView.setBackgroundResource(R.drawable.stroke_black_bg);
                flowLayout.addView(textView);
            }
            FlowLayoutHelper helper = new FlowLayoutHelper(mContext, bean.type == 0, flowLayout, (view1, position) -> {
                String attr_id = content.get(position).id;
                if (bean.type == 0)
                    mAttrValues.put("screening[" + bean.key + "]", attr_id);
                else {
                    String s = mAttrValues.get("screening[" + bean.name + "]");
                    mAttrValues.put("screening[" + bean.key + "]", TextUtils.isEmpty(attr_id) ? "" : (s+"," +
                            attr_id));
                }
            });
            mAttrsContainer.addView(item);
        }
    }

    public HashMap<String, String> getAttrsValues(){
        return mAttrValues;
    }

    @OnClick({R.id.root})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.root:
                dismiss();
                break;
        }
    }


}
