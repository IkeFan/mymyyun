package com.mmy.maimaiyun.model.shopping.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.AddressItemBean;
import com.mmy.maimaiyun.data.bean.ConfOrdersBean;
import com.mmy.maimaiyun.data.bean.OrderAttr;
import com.mmy.maimaiyun.model.personal.ui.activity.AddressManagerActivity;
import com.mmy.maimaiyun.model.shopping.adapter.ConfOrdersAdapter;
import com.mmy.maimaiyun.model.shopping.component.DaggerConfOrderComponent;
import com.mmy.maimaiyun.model.shopping.module.ConfOrderModule;
import com.mmy.maimaiyun.model.shopping.presenter.ConfOrderPresenter;
import com.mmy.maimaiyun.model.shopping.view.ConfOrderView;
import com.mmy.maimaiyun.utils.CommonUtil;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * 确认订单
 */
public class ConfOrderActivity extends BaseActivity<ConfOrderPresenter> implements ConfOrderView, ConfOrdersAdapter
        .OnConfOrderListInnerClickListener {

    @Bind(R.id.title_center_text)
    TextView       mTitleCenterText;
    @Bind(R.id.add_new_address)
    TextView       mAddNewAddress;
    @Bind(R.id.receiver_name)
    TextView       mReceiverName;
    @Bind(R.id.phone)
    TextView       mPhone;
    @Bind(R.id.address)
    TextView       mAddress;
    @Bind(R.id.address_container)
    RelativeLayout mAddressContainer;
    @Bind(R.id.freight)
    TextView       mFreight;
    @Bind(R.id.invoice)
    EditText       mInvoice;
    @Bind(R.id.is_integral_pay)
    CheckBox       mIsIntegralPayView;
    @Bind(R.id.integral_hint)
    EditText       mIntegralHint;
    @Bind(R.id.goods_count)
    TextView       mGoodsCount;
    @Bind(R.id.subtotal)
    TextView       mSubtotal;
    @Bind(R.id.all_money)
    TextView       mAllMoney;
    @Bind(R.id.submit)
    TextView       mSubmit;
    @Bind(R.id.list)
    RecyclerView   mList;
    @Bind(R.id.remark)
    EditText       mRemark;
    private ConfOrdersAdapter                      mAdapter;
    private boolean                                mIsIntegralPay;
    private boolean                                mIsBalancePay;
    private ConfOrdersBean.ShopBean.CouponInfoBean mCoupon;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerConfOrderComponent.builder()
                .appComponent(appComponent)
                .confOrderModule(new ConfOrderModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitleCenterText.setText("确认订单");
        mList.setLayoutManager(new LinearLayoutManager(this));
        int px = UIUtil.dp2px(this, 5);
        mList.addItemDecoration(new SpacesItemDecoration(px, 0, 0, 0));
        mAdapter = new ConfOrdersAdapter(this);
        mList.setAdapter(mAdapter);
        mIsIntegralPayView.setText(String.format(getResources().getString(R.string.is_integral_pay), getUserBean()
                .pay_points));
        //禁止嵌套滑动,处理滑动没有惯性的问题
        mList.setNestedScrollingEnabled(false);
    }

    @Override
    public void initData() {
        String ids = getIntent().getStringExtra("ids");
        if (TextUtils.isEmpty(ids))
            mPresenter.loadData(getIntent(), null);
        else
            mPresenter.getConfOrdersInfo(ids, null);
        mPresenter.loadDefaultAddress();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mAdapter.setOnConfOrderListInnerCLickListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_conf_order;
    }

    @Override
    public void showAddNewAddress(boolean isShow) {
        if (isShow) {
            mAddNewAddress.setVisibility(View.VISIBLE);
            mAddressContainer.setVisibility(View.GONE);
        } else {
            mAddNewAddress.setVisibility(View.GONE);
            mAddressContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showOrderList(List<ConfOrdersBean.ShopBean> data) {
        mAdapter.setData(data);
        float money = 0;
        int count = 0;
        float logistics_money = 0;
        for (ConfOrdersBean.ShopBean shopBean : data) {
            money += shopBean.totalPrice - shopBean.cutPrice;
            for (ConfOrdersBean.ShopBean.GoodsInfoBean infoBean : shopBean.goodsInfo) {
                count += infoBean.goods_number;
            }
            //计算优惠券
            if (shopBean.selectShopCoupon != null)
                money -= shopBean.selectShopCoupon.money;
            for (ConfOrdersBean.ShopBean.GoodsInfoBean goodsInfoBean : shopBean.goodsInfo) {
                logistics_money += goodsInfoBean.logistics_money;
            }
            //计算嘟嘟卡
            money -= shopBean.selectDudu ? 100 : 0;
        }
        if (money < 0)
            money = 0;
        //去除后两位
        mSubtotal.setText("￥" + CommonUtil.round2f(money));
        mAllMoney.setText("￥" + CommonUtil.round2f(money));
        mGoodsCount.setText("共 " + count + " 件商品      小计：");
        mFreight.setText("￥" + logistics_money);
    }

    @Override
    public void showDefaultAddress(BaseBean<List<AddressItemBean>> data) {
        boolean isShowAdd = true;//是否显示添加按钮
        //判断是否地址是否为空
        if (data.data.size() == 0) {
            isShowAdd = true;
        }
        //显示默认地址
        for (AddressItemBean bean : data.data) {
            if (bean.is_default.equals("1")) {
                isShowAdd = false;
                refreshAddressInfo(bean);
                mPresenter.updataAddressInfo(bean);
            }
        }
        showAddNewAddress(isShowAdd);
    }

    @Override
    public void refreshAddressInfo(AddressItemBean bean) {
        mReceiverName.setText("收货人：" + bean.consignee);
        mPhone.setText(bean.mobile);
        mAddress.setText(bean.address);
        showAddNewAddress(false);
    }

    @Override
    public void checkDudu(int shopPosition, boolean isSelect) {
        mAdapter.chechDudu(shopPosition, isSelect);
    }

    @OnClick({R.id.add_new_address, R.id.submit, R.id.address_container})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.address_container:
                openAddressManagerPage();
                break;
            case R.id.add_new_address:
                openAddressManagerPage();
                break;
            case R.id.submit:
                submit();
                break;
        }
    }

    private void openAddressManagerPage() {
        Intent intent = new Intent(this, AddressManagerActivity.class);
        intent.putExtra("is_select", true);
        startActivityForResult(intent, 1);
    }

    @OnCheckedChanged({R.id.is_integral_pay})
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.is_integral_pay:
                mIsIntegralPay = isChecked;
                break;
//            case R.id.balance:
//                mIsBalancePay = isChecked;
//                break;
        }
    }

    private void submit() {
        String trim = mIntegralHint.getText().toString().trim();
        int integral = Integer.parseInt(TextUtils.isEmpty(trim) ? "0" : trim);
//        String trim1 = mBalanceDeductible.getText().toString().trim();
//        float balance = Float.parseFloat(TextUtils.isEmpty(trim1) ? "0.0" : trim1);
        int pay_points = getUserBean().pay_points;
        if (mIsIntegralPay && (integral < 0 || integral > pay_points)) {
            Toast.makeText(this, "请输入有效积分值", Toast.LENGTH_SHORT).show();
            return;
        }
        float member_money = getUserBean().member_money;
//        if (mIsBalancePay && (balance < 0 || balance > member_money)) {
//            Toast.makeText(this, "请输入有效余额值", Toast.LENGTH_SHORT).show();
//            return;
//        }
        String remark = mRemark.getText().toString().trim();
        OrderAttr attr = new OrderAttr();
        attr.member_id = getUserBean().member_id;
//        attr.integral = balance;
        attr.member_money = member_money;
        mPresenter.submitOrder(attr, remark);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null)
            return;
        switch (resultCode) {
            case ShopCouponActivity.SHOP_COUPON://优惠券回调
                mCoupon = (ConfOrdersBean.ShopBean.CouponInfoBean) data.getSerializableExtra("bean");
                int shop_position = data.getIntExtra("shop_position", 0);
                mAdapter.refreshCoupon(shop_position, mCoupon);
                //修改价格
                mPresenter.selectCoupon(shop_position, mCoupon);
                break;
            default:
                AddressItemBean bean = (AddressItemBean) data.getSerializableExtra("bean");
                mPresenter.updataAddressInfo(bean);
                refreshAddressInfo(bean);
                //重新获取邮费
                String ids = getIntent().getStringExtra("ids");
                if (TextUtils.isEmpty(ids))
                    mPresenter.loadData(getIntent(), bean.address_id);
                else
                    mPresenter.getConfOrdersInfo(ids, bean.address_id);
                break;
        }
    }

    //list item 点击回调
    @Override
    public void onOrderListInnerClick(View view, int shopPosition, int goodPosition, int type) {
        mPresenter.onOrderItemClick(shopPosition, goodPosition, type, view);
    }
}
