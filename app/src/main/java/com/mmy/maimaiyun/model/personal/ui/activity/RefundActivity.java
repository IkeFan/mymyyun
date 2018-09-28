package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.data.bean.OrderBean;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 售后/退款
 */
public class RefundActivity extends BaseActivity {

    public static final int RETURN_GOOD   = 0;//退货
    public static final int RETURN_MONEY  = 1;//退款
    public static final int EXCHANGE_GOOD = 2;//换货
    @Bind(R.id.title_center_text)
    TextView  mTitleCenterText;
    @Bind(R.id.order_icon)
    ImageView mOrderIcon;
    @Bind(R.id.order_name)
    TextView  mOrderName;
    @Bind(R.id.order_clazz)
    TextView  mOrderClazz;
    @Bind(R.id.order_money)
    TextView  mOrderMoney;

    @Override
    protected void initDagger(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        mTitleCenterText.setText("售后/退款");
        OrderBean.GoodsInfoBean good = (OrderBean.GoodsInfoBean) getIntent().getSerializableExtra("good");
        if (good==null)return;
        if (!TextUtils.isEmpty(good.logo))
            Picasso.with(this).load(good.logo).error(R.mipmap.ic_def).into(mOrderIcon);
        mOrderName.setText(good.goods_name);
        mOrderClazz.setText(good.goods_attr_value);
        int color = getResources().getColor(R.color.colorPrimary);
        mOrderMoney.setText(Html.fromHtml("合计￥<font color='" + color + "'>" + good.price + "</font>(含运费￥" + good
                .shipping_price + ") " + "数量 " + good.goods_number + " 件"));
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.return_good, R.id.return_money, R.id.exchange_good})
    public void click(View view) {
        Intent intent = getIntent();
        intent.setClass(this,RefundSubmitActivity.class);
        switch (view.getId()) {
            case R.id.return_good:
                intent.putExtra("type", RETURN_GOOD);
                startActivity(intent);
                break;
            case R.id.return_money:
                intent.putExtra("type", RETURN_MONEY);
                startActivity(intent);
                break;
            case R.id.exchange_good:
                intent.putExtra("type", EXCHANGE_GOOD);
                startActivity(intent);
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_refund;
    }

}
