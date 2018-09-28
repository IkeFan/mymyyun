package com.mmy.maimaiyun.model.shopping.ui.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseFragment;
import com.mmy.maimaiyun.customview.LoadingSmartLayout;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.ShopCarListBean;
import com.mmy.maimaiyun.model.shopping.adapter.ShoppingAdapter;
import com.mmy.maimaiyun.model.shopping.component.DaggerShoppingComponent;
import com.mmy.maimaiyun.model.shopping.module.ShoppingModule;
import com.mmy.maimaiyun.model.shopping.presenter.ShoppingPresenter;
import com.mmy.maimaiyun.model.shopping.ui.activity.ConfOrderActivity;
import com.mmy.maimaiyun.model.shopping.view.ShoppingView;
import com.mmy.maimaiyun.utils.MathUtil;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @创建者 lucas
 * @创建时间 2017/8/16 0016 17:58
 * @描述 购物车
 */

public class ShoppingFragment extends BaseFragment<ShoppingPresenter> implements ShoppingView, ShoppingAdapter
        .onSelectChangeListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.loading_smart_layout)
    LoadingSmartLayout mLoadingSmartLayout;
    RecyclerView       mRecyclerView;
    @Bind(R.id.total_money)
    TextView           mTotalMoney;
    @Bind(R.id.select_all)
    CheckBox           mSelectAll;
    @Bind(R.id.title_center_text)
    TextView           mCenterText;
    @Bind(R.id.title_back)
    ImageView          mBack;
    @Bind(R.id.title_right_text)
    TextView           mRightText;
    @Bind(R.id.total_money_container)
    LinearLayout       mTotalMoenyContainer;
    @Bind(R.id.submit)
    TextView           mSubmit;
    @Bind(R.id.refresh_view)
    SwipeRefreshLayout mRefreshLayout;

    private ShoppingAdapter                        mShoppingAdapter;
    private ShoppingAdapter.OnAdd2ShoppingListener mItemListener;
    private boolean                                mStatus;

    @Override
    protected void initView() {
        mRecyclerView = mLoadingSmartLayout.getListView();
        mCenterText.setText("购物车");
        mRightText.setText("编辑");
        mBack.setVisibility(View.GONE);
        mShoppingAdapter = new ShoppingAdapter(mActivity);
        mRecyclerView.setAdapter(mShoppingAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        int px = UIUtil.dp2px(mActivity, 7);
        SpacesItemDecoration decor = new SpacesItemDecoration(0, px, px, px);
        mRecyclerView.addItemDecoration(decor);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.loadList();
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPresenter.selectAll(isChecked);
            }
        });
        mItemListener = new ShoppingAdapter.OnAdd2ShoppingListener() {
            @Override
            public void onGoodCheckedChanged(View view, boolean isChecked, int position, int goodIndex) {
                mPresenter.reCalculate();
            }

            @Override
            public void onShopCheckedChanged(View view, int position) {
                mPresenter.reCalculate();
            }

            @Override
            public void onReCalculate() {
                mPresenter.reCalculate();
            }
        };
        mShoppingAdapter.setOnAdd2ShoppingListener(mItemListener);
        mShoppingAdapter.setOnSelectChangeListener(this);
        mRefreshLayout.setOnRefreshListener(this);
    }

    @OnClick({R.id.title_right_text, R.id.submit})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.title_right_text:
                mShoppingAdapter.switchStatus();
                switchStatus();
                break;
            case R.id.submit:
                if (mStatus) {
                    //移除购物车
                    mShoppingAdapter.delete();
                } else {
                    String ids = mShoppingAdapter.getSelectIds();
                    if (TextUtils.isEmpty(ids)) {
                        return;
                    }
                    Log.d("ShoppingFragment", ids);
                    Intent intent = new Intent(mActivity, ConfOrderActivity.class);
                    intent.putExtra("ids", ids);
                    startActivity(intent);
                }
                break;
        }
    }

    private void switchStatus() {
        mStatus = !mStatus;
        if (mStatus) {
            mTotalMoenyContainer.setVisibility(View.INVISIBLE);
            mSubmit.setText("移出购物车");
            mRightText.setText("完成");
        } else {
            mTotalMoenyContainer.setVisibility(View.VISIBLE);
            mSubmit.setText("结算");
            mRightText.setText("编辑");
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_shopping;
    }

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerShoppingComponent.builder()
                .appComponent(appComponent)
                .shoppingModule(new ShoppingModule(this))
                .build().inject(this);
    }

    @Override
    public void refreshTotalMoney(float money) {
        mTotalMoney.setText("￥" + MathUtil.round2(money));
    }

    @Override
    public void refreshList(List<ShopCarListBean> data) {
        mSelectAll.setChecked(false);
        if (mRefreshLayout.isRefreshing())
            mRefreshLayout.setRefreshing(false);
        mShoppingAdapter.setData(data);
        mPresenter.reCalculate();
    }

    @Override
    public void selectAll(boolean isChecked) {
        mShoppingAdapter.selectAll(isChecked);
    }

    @Override
    public void hideRefreshView() {
        if (mRefreshLayout.isRefreshing())
            mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDeleteShop(String ids) {
        mPresenter.deleteShop(ids);
    }


    @Override
    public void onShopCountChange(String goods_id, int count) {
        mPresenter.changeShopCount(goods_id, count);
    }

    @Override
    public void onRefresh() {
        mPresenter.loadList();
    }
}
