package com.mmy.maimaiyun.model.personal.presenter;


import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.OrderBean;
import com.mmy.maimaiyun.data.bean.SubOrderBean;
import com.mmy.maimaiyun.model.personal.adapter.OrderAdapter;
import com.mmy.maimaiyun.model.personal.model.OrderModel;
import com.mmy.maimaiyun.model.personal.ui.activity.CommentActivity;
import com.mmy.maimaiyun.model.personal.ui.activity.LogActivity;
import com.mmy.maimaiyun.model.personal.ui.activity.OrderInfoActivity;
import com.mmy.maimaiyun.model.personal.ui.activity.RefundActivity;
import com.mmy.maimaiyun.model.personal.view.OrderView;
import com.mmy.maimaiyun.model.shopping.ui.activity.PayActivity;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @创建者 lucas
 * @创建时间 2017/9/7 0007 18:04
 * @描述 TODO
 */

public class OrderPresenter extends BasePresenter<OrderView> {
    private OrderView mView;

    public static final int REPAY   = 0x1100;//支付
    public static final int COMMENT = 0x1101;//评论

    @Inject
    OrderModel mModel;
    private List<OrderBean> mData = new ArrayList<>();
    private final Activity mActivity;

    @Inject
    public OrderPresenter(OrderView view) {
        mView = view;
        mActivity = (Activity) view;
    }

    public void loadData() {
        mModel.loadData(new OnLoadDataListener<BaseBean<List<OrderBean>>>(mView.findLoadingSmartLayout()) {
            @Override
            public void onResponse(String body, BaseBean<List<OrderBean>> data) {
                //订单状态赋值
                //清楚已删除的订单
                if (data.status != 1)
                    return;
                mData.clear();
                for (OrderBean bean : data.data) {
                    if (bean.order_shop_status != 99)
                        mData.add(bean);
                }
                for (OrderBean bean : mData) {
                    //判断是否是待付款
                    if (bean.order_shop_status == 0) {
                        bean.my_order_status = OrderAdapter.PENDING_PAYMENT;
                        continue;
                    }
                    //判断是否是待发货
                    if (bean.order_shop_status == 2) {
                        bean.my_order_status = OrderAdapter.SHIPMENT_PENDING;
                        continue;
                    }
                    //判断是否是待收货
                    if (bean.order_shop_status == 3) {
                        bean.my_order_status = OrderAdapter.GOODS_RECEIVED;
                        continue;
                    }
                    //判断是否是待评分
                    if (bean.order_shop_status == 4) {
                        bean.my_order_status = OrderAdapter.GRADING;
                    }
                }
                mView.refreshOrderList(data.data);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<OrderBean>>>() {
                }.getType();
            }
        }, mView.getUserBean().token, mView.getUserBean().member_id);
    }

    //筛选
    public void swithOrder(int status) {
        List<OrderBean> list = new ArrayList<>();
        for (OrderBean bean : mData) {
            if (bean.order_shop_status == status)
                list.add(bean);
        }
        mView.refreshOrderList(status == OrderAdapter.ALL ? mData : list);
    }

    public void openOrderInfo(OrderBean bean) {
        Intent intent = new Intent(mActivity, OrderInfoActivity.class);
        intent.putExtra("bean", bean);
        mActivity.startActivity(intent);
    }

    public void delOrder(int position, String action, OrderBean bean) {
        mModel.delOrder(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data, IBean iBean) {
                Toast.makeText(mActivity, iBean.info, Toast.LENGTH_SHORT).show();
                if (iBean.status == 1)
                    //刷新数据
                    loadData();
                mView.hideLoading();
            }

            @Override
            public void onFailure(String body, Throwable t) {
                mView.hideLoading();
                Toast.makeText(mActivity, "操作失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStart() {
                mView.showLoading();
            }

            @Override
            public void onCompleted() {
                mView.hideLoading();
                mView.deleteOrderSuccess();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>() {
                }.getType();
            }
        }, mView.getUserBean().token, mView.getUserBean().member_id, bean.order_shop_sn, action);
    }


    public void confirmOrder(int position, OrderBean bean) {
        String order_shop_sn = bean.order_shop_sn;
        String member_id = mView.getUserBean().member_id;
        mModel.confirmOrder(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data, IBean iBean) {
                Toast.makeText(mActivity, iBean.info, Toast.LENGTH_SHORT).show();
                if (iBean.status == 1)
                    //刷新数据
                    loadData();
            }

            @Override
            public void onFailure(String body, Throwable t) {
                Toast.makeText(mActivity, "操作失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>() {
                }.getType();
            }
        }, mView.getUserBean().token, member_id, order_shop_sn);
    }

    //支付
    public void rePay(OrderBean bean) {
        float money = 0;
        for (OrderBean.GoodsInfoBean good : bean.goodsInfo) {
            money += good.price;
        }
        SubOrderBean orderBean = new SubOrderBean();
        orderBean.order_amount = money;
        orderBean.order_sn = bean.order_sn;
        orderBean.order_id = bean.order_id;
        Intent intent = new Intent(mActivity, PayActivity.class);
        intent.putExtra("bean", orderBean);
        intent.putExtra("money", money + "");
        mActivity.startActivityForResult(intent, REPAY);
    }

    //评价
    public void commentGoods(int position, int index, OrderBean bean) {
        Intent intent = new Intent(mActivity, CommentActivity.class);
        intent.putExtra("goods", bean.goodsInfo.get(index));
        intent.putExtra("order_sn", bean.order_shop_sn);
        intent.putExtra("shop_name", bean.shop_name);
        mActivity.startActivityForResult(intent, COMMENT);
    }

    public void openRefund(int position, int index, OrderBean bean) {
        Intent intent = new Intent(mActivity, RefundActivity.class);
        //将商品信息传递过去
        intent.putExtra("good", bean.goodsInfo.get(index));
        intent.putExtra("shop_id", bean.shop_id);
        intent.putExtra("order_goods_id", bean.goodsInfo.get(index).id);
        mActivity.startActivity(intent);
    }

    //查看物流
    public void openLogPage(int position, int index, OrderBean bean) {
        Intent intent = new Intent(mActivity, LogActivity.class);
        intent.putExtra("code",bean.shipping_code);
        intent.putExtra("name",bean.shipping_name);
        intent.putExtra("shipping",bean.shipping);
        mActivity.startActivity(intent);
    }
}
