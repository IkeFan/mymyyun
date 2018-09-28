package com.mmy.maimaiyun.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.able.OnPopupMenuItemClickListener;
import com.mmy.maimaiyun.customview.FlowLayout;
import com.mmy.maimaiyun.data.bean.GoodsInfoAttrBean;
import com.mmy.maimaiyun.helper.FlowLayoutHelper;
import com.mmy.maimaiyun.utils.UIUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @创建者 lucas
 * @创建时间 2017/9/5 0005 15:14
 * @描述 TODO
 */

public class GoodInfoColorPopup extends PopupWindow {
    private List<List<GoodsInfoAttrBean.SpecBean>> mData;
    @Bind(R.id.count)
    TextView  mCount;
    @Bind(R.id.reduce)
    View      mReduce;
    @Bind(R.id.add)
    View      mAdd;
    @Bind(R.id.good_money)
    TextView  mGoodMoney;
    @Bind(R.id.good_icon)
    ImageView mGoodsIcon;

    HashMap<String, String> mAttrIds = new HashMap<>();
    private Context      mContext;
    private LinearLayout mContainer;
    public static final int ADD_TO_SHOP = 0;
    public static final int TO_BUY      = 1;
    public static final int ATTRS       = 2;
    private OnPopupMenuItemClickListener mListener;
    private String                       mMoney;
    private Set<String> unCheckItem = new HashSet<>();//未选择的属性

    public boolean isShowde() {
        return mIsShowde;
    }

    private boolean mIsShowde;//是否显示过

    public GoodInfoColorPopup(Context context) {
        super(context);
        mContext = context;
        initView(context);
    }

    public void setLogo(String logo){
        if (!TextUtils.isEmpty(logo))
            Picasso.with(mContext).load(logo).error(R.mipmap.ic_def).placeholder(R.mipmap.ic_def).into(mGoodsIcon);
    }

    public void setAttrData(List<List<GoodsInfoAttrBean.SpecBean>> data) {
        mData = data;
        int px8 = UIUtil.dp2px(mContext, 8);
        //添加item
        for (List<GoodsInfoAttrBean.SpecBean> specBeen : mData) {
            String key = specBeen.get(0).attr_name;
            View item = LayoutInflater.from(mContext).inflate(R.layout.item_good_info_color, null);
            TextView name = (TextView) item.findViewById(R.id.clazz_name);
            name.setText(key);
            FlowLayout flowLayout = (FlowLayout) item.findViewById(R.id.color_list);
            for (GoodsInfoAttrBean.SpecBean bean : specBeen) {
                TextView textView = new TextView(mContext);
                textView.setPadding(px8, px8, px8, px8);
                textView.setGravity(Gravity.CENTER);
                textView.setText(bean.attr_value);
                textView.setTextColor(mContext.getResources().getColor(R.color.hint_text_color));
                textView.setBackgroundResource(R.drawable.stroke_black_bg);
                flowLayout.addView(textView);
                //将所有属性名称添加进去
                unCheckItem.add(bean.attr_name);
            }
            FlowLayoutHelper helper = new FlowLayoutHelper(mContext, true, flowLayout, (view1, position) -> {
                String attr_id = specBeen.get(position).id;
                String attr_name = specBeen.get(position).attr_name;
                mAttrIds.put(attr_name, attr_id);
                //选中属性后去除
                unCheckItem.remove(attr_name);
                if (mListener != null) {
                    //所有属性被选中才能去查询价格
                    if (mAttrIds.size() == mData.size())
                        mListener.onPopupItemClick(view1, ATTRS);
                }
            });
            mContainer.addView(item);
        }
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.popup_good_info_color, null);
        ButterKnife.bind(this, view);
        mContainer = (LinearLayout) view.findViewById(R.id.clazz_container);
        setContentView(view);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(0x00ffffff));

        view.findViewById(R.id.add2shop).setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onPopupItemClick(v, ADD_TO_SHOP);
            }
        });
        view.findViewById(R.id.to_buy).setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onPopupItemClick(v, TO_BUY);
            }
        });

        mReduce.setOnClickListener(v -> {
            int count = Integer.parseInt(mCount.getText().toString());
            if (count == 0)
                return;
            mCount.setText(--count + "");
        });
        mAdd.setOnClickListener(v -> {
            int count = Integer.parseInt(mCount.getText().toString());
            mCount.setText(++count + "");
        });
    }

    public String getGoodsAttrs() {
        ArrayList<String> list = new ArrayList<>();
        for (String key : mAttrIds.keySet()) {
            String s = mAttrIds.get(key);
            list.add(s);
        }
        //排序
        Collections.sort(list);
        String ids = "";
        for (String s : list) {
            ids = ids + (TextUtils.isEmpty(ids) ? "" : ",") + s;
        }
        return ids;
    }

    public void setOnPopupMenuClickListener(OnPopupMenuItemClickListener listener) {
        mListener = listener;
    }

    public String getGoodsCount() {
        return mCount.getText().toString();
    }

    @OnClick({R.id.close})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.close:
                dismiss();
                break;
        }
    }

    //是否选中所有属性
    public boolean isSelectAllAttr() {
        return unCheckItem.isEmpty();
    }

    //获取第一个未选择的属性
    public String getUnSelectAttrName() {
        return unCheckItem.iterator().next();
    }


    public void setMoney(String money) {
        mMoney = money;
        mGoodMoney.setText("￥ " + money);
    }

    public void show(View points, int center){
        mIsShowde = true;
        showAtLocation(points,center,0,0);
    }

}
