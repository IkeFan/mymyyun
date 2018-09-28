package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.BankCarBean;
import com.mmy.maimaiyun.model.personal.adapter.BankCarAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerBankCarComponent;
import com.mmy.maimaiyun.model.personal.module.BankCarModule;
import com.mmy.maimaiyun.model.personal.presenter.BankCarPresenter;
import com.mmy.maimaiyun.model.personal.view.BankCarView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的银行卡
 */
public class BankCarActivity extends BaseActivity<BankCarPresenter> implements BankCarView, BaseRecyclerViewAdapter
        .OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.title_center_text)
    TextView           mTitle;
    @Bind(R.id.title_right_text)
    TextView           mRight;
    @Bind(R.id.list)
    RecyclerView       mList;
    @Bind(R.id.bottom_view)
    View               mBottomView;
    @Bind(R.id.select_all_view)
    CheckBox           mSelectAllView;
    @Bind(R.id.refresh_view)
    SwipeRefreshLayout mRefreshLayout;

    private BankCarAdapter mAdapter;
    private boolean        mIsSelect;
    private boolean        mIsSelectAll;
    public static final int ACTION_SELECT_BANK_CARD = 0x100;
    private int mAction;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerBankCarComponent.builder()
                .appComponent(appComponent)
                .bankCarModule(new BankCarModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("我的银行卡");
        mRight.setText("编辑");
        mAction = getIntent().getIntExtra("action", -1);
        mList.setLayoutManager(new LinearLayoutManager(this));
        int px = UIUtil.dp2px(this, 7);
        mList.addItemDecoration(new SpacesItemDecoration(px, 0, px * 2, px * 2));
        mAdapter = new BankCarAdapter(this);
        mList.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mAdapter.setData(null);
        mPresenter.loadData();
    }

    @Override
    public void initEvent() {
        mAdapter.setOnItemClickListener(this);
        mRefreshLayout.setOnRefreshListener(this);
    }

    @OnClick({R.id.title_right_text, R.id.delete, R.id.select_all})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.delete:
                mPresenter.delCars(mAdapter.getSelectCarId());
                break;
            case R.id.select_all:
                mIsSelectAll = !mIsSelectAll;
                mSelectAllView.setChecked(mIsSelectAll);
                mAdapter.selectAll(mSelectAllView.isChecked());
                break;
            case R.id.title_right_text:
                mIsSelect = !mIsSelect;
                mRight.setTextColor(getResources().getColor(mIsSelect ? R.color.colorPrimary :
                        R.color.normal_text_color));
                mAdapter.select(mIsSelect);
                mBottomView.setVisibility(mIsSelect ? View.VISIBLE : View.GONE);
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_bank_car;
    }

    @Override
    public void refreshList(List<BankCarBean> data) {
        mAdapter.setData(data);
    }

    @Override
    public void onItemClick(View view, int position) {
        if (position == mAdapter.getItemCount() - 1) {
            if (getUserBean().is_audit == 2)
                startActivity(new Intent(this, AddCarActivity.class));
            else
                Toast.makeText(this, "您还未实名或者是正在实名中", Toast.LENGTH_SHORT).show();
        }
        if (mAction == ACTION_SELECT_BANK_CARD) {
            BankCarBean bean = mAdapter.getItemBean(position);
            Intent data = new Intent();
            data.putExtra("bean", bean);
            setResult(ACTION_SELECT_BANK_CARD, data);
            finishSelf();
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        mPresenter.loadData();
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(() -> mRefreshLayout.setRefreshing(false), 1000);
    }
}
