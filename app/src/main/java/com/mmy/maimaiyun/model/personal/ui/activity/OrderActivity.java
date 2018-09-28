package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseRefreshActivity;
import com.mmy.maimaiyun.customview.LoadingSmartLayout;
import com.mmy.maimaiyun.data.bean.OrderBean;
import com.mmy.maimaiyun.model.personal.adapter.OrderAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerOrderComponent;
import com.mmy.maimaiyun.model.personal.module.OrderModule;
import com.mmy.maimaiyun.model.personal.presenter.OrderPresenter;
import com.mmy.maimaiyun.model.personal.view.OrderView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;

import static com.mmy.maimaiyun.model.personal.adapter.OrderAdapter.ADD_EVALUATION;
import static com.mmy.maimaiyun.model.personal.adapter.OrderAdapter.CANCEL_ORDER;
import static com.mmy.maimaiyun.model.personal.adapter.OrderAdapter.COMFIRM;
import static com.mmy.maimaiyun.model.personal.adapter.OrderAdapter.IMMEDIATE_ASSESSMENT;
import static com.mmy.maimaiyun.model.personal.adapter.OrderAdapter.ITEM_DELETE;
import static com.mmy.maimaiyun.model.personal.adapter.OrderAdapter.PAY;
import static com.mmy.maimaiyun.model.personal.adapter.OrderAdapter.REFUND;
import static com.mmy.maimaiyun.model.personal.adapter.OrderAdapter.REMIND_SHIPMENT;
import static com.mmy.maimaiyun.model.personal.adapter.OrderAdapter.VIEW_LOG;


/**
 * 订单
 */
public class OrderActivity extends BaseRefreshActivity<OrderPresenter> implements OrderView, OrderAdapter
        .OnOrderItemClickListener, TabLayout.OnTabSelectedListener {

    @Bind(R.id.title_center_text)
    TextView           mTitle;
    @Bind(R.id.loading_smart_layout)
    LoadingSmartLayout mLoadingSmartLayout;
    RecyclerView mList;
    @Bind(R.id.tabs)
    TabLayout    mTabs;
    private OrderAdapter              mAdapter;
    private String                    mType;
    private HashMap<Integer, Integer> mMap;

    public static final  String ACTION_DELETE = "delete";
    public static final  String ACTION_CANCEL = "cancel";

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerOrderComponent.builder()
                .appComponent(appComponent)
                .orderModule(new OrderModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mList = mLoadingSmartLayout.getListView();
        mTitle.setText("我的订单");
        mList.setLayoutManager(new LinearLayoutManager(this));
        int dp10 = UIUtil.dp2px(this, 7);
//        mList.addItemDecoration(new SpacesItemDecoration(dp10, 0, 0, 0));
        mAdapter = new OrderAdapter(this);
        mList.setAdapter(mAdapter);
        mTabs.addTab(mTabs.newTab().setText("全部"));
        mTabs.addTab(mTabs.newTab().setText("待付款"));
        mTabs.addTab(mTabs.newTab().setText("待发货"));
        mTabs.addTab(mTabs.newTab().setText("待收货"));
        mTabs.addTab(mTabs.newTab().setText("待评论"));
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mAdapter.setOnOrderItemClickListener(this);
        mTabs.addOnTabSelectedListener(this);
    }

    @Override
    public void initData() {
        mPresenter.loadData();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_order;
    }

    @Override
    public void refreshOrderList(List<OrderBean> data) {
        mRefreshLayout.setRefreshing(false);
        mAdapter.setData(data);
        if (mType == null) {
            mType = getIntent().getStringExtra("type");
            if (!TextUtils.isEmpty(mType)) {
                mTabs.getTabAt(Integer.parseInt(mType) + 1).select();
            }
        }
    }

    @Override
    public void deleteOrderSuccess() {
        mPresenter.loadData();
    }

    @Override
    public void onOrderInnerItemClick(View view, OrderBean bean, int position,int order_id, int index, int type) {
        switch (type) {
            case REFUND://售后/退款
                mPresenter.openRefund(position,index,bean);
                break;
            case VIEW_LOG://查看物流
                mPresenter.openLogPage(position,index,bean);
                break;
            case COMFIRM://确认收获
                mPresenter.confirmOrder(position,bean);
                break;
            case CANCEL_ORDER://取消订单
                mPresenter.delOrder(position,ACTION_CANCEL,bean);
                break;
            case PAY://付款
                mPresenter.rePay(bean);
                break;
            case REMIND_SHIPMENT://提醒卖家发货
                mHandler.postDelayed(() -> {
                    Toast.makeText(this, "已提醒卖家", Toast.LENGTH_SHORT).show();
                }, 500);
                break;
            case IMMEDIATE_ASSESSMENT://立即评价
                mPresenter.commentGoods(position,index,bean);
                break;
            case ADD_EVALUATION://追加评价
                break;
            case ITEM_DELETE://删除
                mPresenter.delOrder(position,ACTION_DELETE, bean);
                break;
        }
    }

    @Override
    public void onOrderItemClick(View v, OrderBean bean, int position) {
        mPresenter.openOrderInfo(bean);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == OrderPresenter.REPAY) {
            //刷新数据
            initData();
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mPresenter.swithOrder(mMap.get(tab.getPosition()));
        mAdapter.setCurrentSelect(mMap.get(tab.getPosition()));
    }

    {
        mMap = new HashMap<>();
        mMap.put(0, -1);
        mMap.put(1, 0);
        mMap.put(2, 2);
        mMap.put(3, 3);
        mMap.put(4, 4);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.loadData();
        mTabs.getTabAt(0).select();
    }
}
