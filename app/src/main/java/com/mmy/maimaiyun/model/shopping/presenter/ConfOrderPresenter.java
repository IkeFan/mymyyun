package com.mmy.maimaiyun.model.shopping.presenter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.AddressItemBean;
import com.mmy.maimaiyun.data.bean.ConfOrdersBean;
import com.mmy.maimaiyun.data.bean.OrderAttr;
import com.mmy.maimaiyun.data.bean.SubOrderBean;
import com.mmy.maimaiyun.model.main.ui.activity.GoodInfoActivity;
import com.mmy.maimaiyun.model.shopping.adapter.ConfOrdersAdapter;
import com.mmy.maimaiyun.model.shopping.model.ConfOrderModel;
import com.mmy.maimaiyun.model.shopping.ui.activity.PayActivity;
import com.mmy.maimaiyun.model.shopping.ui.activity.ShopCouponActivity;
import com.mmy.maimaiyun.model.shopping.view.ConfOrderView;
import com.mmy.maimaiyun.utils.CommonUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/10/6 0006 15:10
 * @描述 TODO
 */

public class ConfOrderPresenter extends BasePresenter<ConfOrderView> {
    private ConfOrderView mView;
    @Inject
    ConfOrderModel mModel;
    @Inject
    Gson           mGson;
    private       String                        mAddressId;
    private       List<ConfOrdersBean.ShopBean> mOrderList;
    private final Activity                      mActivity;

    @Inject
    public ConfOrderPresenter(ConfOrderView view) {
        mView = view;
        mActivity = (Activity) mView;
    }

    public void loadData(Intent intent, String addressId) {
        mModel.loadData(new OnLoadDataListener<BaseBean<ConfOrdersBean>>() {
            @Override
            public void onResponse(String body, BaseBean<ConfOrdersBean> data, IBean iBean) {
                //处理刷新的数据，防止已选中的状态被改变，比如优惠券和嘟嘟卡
                if (mOrderList != null)
                    for (int i = 0; i < data.data.shop.size(); i++) {
                        data.data.shop.get(i).selectShopCoupon = mOrderList.get(i).selectShopCoupon;
                        data.data.shop.get(i).selectDudu = mOrderList.get(i).selectDudu;
                    }
                mOrderList = data.data.shop;
                mView.showOrderList(data.data.shop);
            }

            @Override
            public void onFailure(String body, Throwable t) {
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<ConfOrdersBean>>() {
                }.getType();
            }
        }, mView.getUserBean().token, mView.getUserBean().member_id, addressId, intent);
    }

    public void getConfOrdersInfo(String ids, String addressId) {
        mModel.confOrders(new OnLoadDataListener<BaseBean<ConfOrdersBean>>() {
            @Override
            public void onResponse(String body, BaseBean<ConfOrdersBean> data, IBean iBean) {
                //处理刷新的数据，防止已选中的状态被改变，比如优惠券和嘟嘟卡
                if (mOrderList != null)
                    for (int i = 0; i < data.data.shop.size(); i++) {
                        data.data.shop.get(i).selectShopCoupon = mOrderList.get(i).selectShopCoupon;
                        data.data.shop.get(i).selectDudu = mOrderList.get(i).selectDudu;
                    }
                mOrderList = data.data.shop;
                mView.showOrderList(data.data.shop);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<ConfOrdersBean>>() {
                }.getType();
            }
        }, mView.getUserBean().token, mView.getUserBean().member_id, ids, addressId);
    }

    public void loadDefaultAddress() {
        mModel.getAddressData(new OnLoadDataListener<BaseBean<List<AddressItemBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<AddressItemBean>> data) {
                mView.showDefaultAddress(data);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<AddressItemBean>>>() {
                }.getType();
            }
        }, mView.getUserBean().token, mView.getUserBean().member_id);
    }

    public void submitOrder(OrderAttr attr, String remark) {
        attr.address_id = mAddressId;
        if (TextUtils.isEmpty(mAddressId)) {
            Toast.makeText(mActivity, "请选择收货地址", Toast.LENGTH_SHORT).show();
            return;
        }
        List<OrderAttr.Shop> shops = new ArrayList<>();
        attr.shop = shops;
        float money = 0;
        if (mOrderList != null)
            for (ConfOrdersBean.ShopBean shopBean : mOrderList) {
                OrderAttr.Shop shop = new OrderAttr.Shop();
                attr.shop.add(shop);
                shop.shop_id = shopBean.shopId + "";
                if (shopBean.selectShopCoupon != null) {
                    shop.coupon = shopBean.selectShopCoupon;
                }
                shop.remark = remark;
                shop.duducard = shopBean.selectDudu ? "1" : "0";
                shop.goods_info = new ArrayList<>();
                float temp_money = 0;
                for (ConfOrdersBean.ShopBean.GoodsInfoBean infoBean : shopBean.goodsInfo) {
                    OrderAttr.Shop.GoodsInfo goodInfo = new OrderAttr.Shop.GoodsInfo();
                    shop.goods_info.add(goodInfo);
                    goodInfo.goods_attr_id = infoBean.goods_attr_id;
                    goodInfo.shop_price = infoBean.shop_price;
                    goodInfo.goods_id = infoBean.id;
                    goodInfo.goods_number = infoBean.goods_number;
                    temp_money += Float.parseFloat(infoBean.shop_price) * goodInfo.goods_number;
                    temp_money += infoBean.logistics_money;
                }
                //满减
                shop.total = temp_money - shopBean.cutPrice;
                //减掉优惠券价格
                if (shop.coupon != null) {
                    for (ConfOrdersBean.ShopBean.CouponInfoBean couponInfoBean : shopBean.couponInfo) {
                        if (couponInfoBean.id.equals(shop.coupon.id)) {
                            shop.total -= couponInfoBean.money;
                            if (shop.total < 0)
                                shop.total = 0;
                            break;
                        }
                    }
                }
                //减去嘟嘟卡
                shop.total -= shopBean.selectDudu ? 100 : 0;
                //修复精度
                shop.total = CommonUtil.fixAccuracy(shop.total);
                //单品价格减去优惠价格后如果小于0.则最后单品总价格也等于0
                money += shop.total < 0 ? 0 : shop.total;
            }

        float finalMoney = money;
        mModel.submitOrder(new OnLoadDataListener<BaseBean<SubOrderBean>>() {
            @Override
            public void onResponse(String body, BaseBean<SubOrderBean> data, IBean iBean) {
                mView.hideLoading();
                if (iBean.status == 1) {
                    mView.finishSelf();
                    Intent intent = new Intent(mActivity, PayActivity.class);
                    intent.putExtra("bean", data.data);
                    intent.putExtra("money", finalMoney + "");
                    mActivity.startActivity(intent);
                }
                Toast.makeText(mActivity, iBean.info, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public void onStart() {
                mView.showLoading();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<SubOrderBean>>() {
                }.getType();
            }
        }, mGson.toJson(attr, OrderAttr.class));
    }

    public void updataAddressInfo(AddressItemBean bean) {
        mAddressId = bean.address_id;
    }

    public void onOrderItemClick(int shopPosition, int goodPosition, int type, View view) {
        switch (type) {
            case ConfOrdersAdapter.TYPE_COUPON:
                ConfOrdersBean.ShopBean shopBean = mOrderList.get(shopPosition);
                if (shopBean != null && shopBean.couponInfo.size() != 0) {
                    Intent intent = new Intent(mActivity, ShopCouponActivity.class);
                    intent.putExtra("data", shopBean);
                    intent.putExtra("shop_position", shopPosition);
                    mActivity.startActivityForResult(intent, 0);
                }
                break;
            case ConfOrdersAdapter.TYPE_GOOD:
                mView.openActivity(GoodInfoActivity.class, "id=" + mOrderList.get(shopPosition).goodsInfo.get
                        (goodPosition).id);
                break;
            case ConfOrdersAdapter.TYPE_DUDU:
                //判断是否取消选中嘟嘟卡
                CheckBox checkBox = (CheckBox) view;
                if (checkBox.isChecked()) {
                    //取消选中
                    checkBox.setChecked(false);
                    mOrderList.get(shopPosition).selectDudu = false;
                    //刷新列表
                    mView.showOrderList(mOrderList);
                    return;
                }
                if (mOrderList.get(shopPosition).selectShopCoupon != null) {
                    Toast.makeText(mActivity, "嘟嘟卡与优惠券只能二选一", Toast.LENGTH_SHORT).show();
                    mOrderList.get(shopPosition).selectShopCoupon = null;
                }
                ConfOrdersBean.ShopBean.DuducardInfoBean duducardInfo = mOrderList.get(shopPosition).duducardInfo;
                if (duducardInfo != null) {
                    //优惠券和嘟嘟卡智能二选一，并且商品金额大于100
                    float moeny = 0;
                    for (ConfOrdersBean.ShopBean.GoodsInfoBean infoBean : mOrderList.get(shopPosition).goodsInfo) {
                        moeny += infoBean.goods_number * Float.parseFloat(infoBean.shop_price);
                    }
                    if (moeny < 100) {
                        Toast.makeText(mActivity, "商品金额不满100元无法使用嘟嘟卡", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mOrderList.get(shopPosition).selectDudu = true;
                    mView.checkDudu(shopPosition, true);
                }
                //刷新列表
                mView.showOrderList(mOrderList);
                break;
        }

    }


    //选择优惠券后更新价格
    public void selectCoupon(int shop_position, ConfOrdersBean.ShopBean.CouponInfoBean coupon) {
        if (mOrderList.get(shop_position).selectDudu) {
            Toast.makeText(mActivity, "嘟嘟卡与优惠券只能二选一", Toast.LENGTH_SHORT).show();
            mOrderList.get(shop_position).selectDudu = false;
            mView.checkDudu(shop_position, false);
        }
        mOrderList.get(shop_position).selectShopCoupon = coupon;
        mView.showOrderList(mOrderList);
    }
}
