package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.adapter.BaseQuickAdapter;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.SpreadCenterBean;
import com.mmy.maimaiyun.model.personal.adapter.SpreadCenterAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerSpreadCenterComonent;
import com.mmy.maimaiyun.model.personal.module.SpreadCenterModeule;
import com.mmy.maimaiyun.model.personal.presenter.SpreadCenterPresenter;
import com.mmy.maimaiyun.model.personal.view.SpreadCenterView;
import com.mmy.maimaiyun.utils.CommonUtil;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 推广中心
 */
public class SpreadCenterActivity extends BaseActivity<SpreadCenterPresenter> implements SpreadCenterView, BaseQuickAdapter.RequestLoadMoreListener, TabLayout.OnTabSelectedListener {

    @Bind(R.id.title_center_text)
    TextView     mTitle;
    @Bind(R.id.request_code)
    TextView     mRequestCode;
    @Bind(R.id.total_money)
    TextView     mTotalMoney;
    @Bind(R.id.list)
    RecyclerView mList;
    @Bind(R.id.user_number1)
    TextView     mUserNumber1;
    @Bind(R.id.user_number2)
    TextView     mUserNumber2;
    @Bind(R.id.frozen_distribute)
    TextView     mFrozen;
    @Bind(R.id.request_code_view)
    View         mRequestCodeView;
    @Bind(R.id.dudu_title)
    TextView     mDuduTitle;
    @Bind(R.id.copy)
    TextView     mCopy;
    @Bind(R.id.tabs)
    TabLayout mTabLayout;
    private SpreadCenterAdapter mAdapter;
    String[] status = {"冻结", "已发放", "已失效"};
    private int mPosition;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerSpreadCenterComonent.builder()
                .appComponent(appComponent)
                .spreadCenterModeule(new SpreadCenterModeule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("推广中心");
        mList.setLayoutManager(new LinearLayoutManager(this));
        int px = UIUtil.dp2px(this, 2);
        mList.addItemDecoration(new SpacesItemDecoration(px));
        mAdapter = new SpreadCenterAdapter(R.layout.item_distribution_order, new ArrayList<>());
        mList.setAdapter(mAdapter);
        //判断是否绑定了嘟嘟卡
        if (getUserBean().is_dudu != 0) {
            mDuduTitle.setText("我的邀请码");
            mRequestCode.setText(getUserBean().invite_code);
            mCopy.setVisibility(View.VISIBLE);
        } else {
            mDuduTitle.setText("您还不是VIP");
            mRequestCode.setText("请先成为VIP");
            mCopy.setText("介绍VIP");
        }

        for (String s : status) {
            mTabLayout.addTab(mTabLayout.newTab().setText(s));
        }
    }

    @Override
    public void initData() {
        mPresenter.loadData(false, mPosition);
    }

    @Override
    public void initEvent() {
        mAdapter.setOnLoadMoreListener(this);
        mTabLayout.setOnTabSelectedListener(this);
    }

    @OnClick({R.id.copy, R.id.my_users1, R.id.my_users2, R.id.request_code_view})
    public void click(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.my_users1:
                intent = new Intent(this, MyUsersActivity.class);
                intent.putExtra("level", MyUsersActivity.level_1);
                startActivity(intent);
                break;
            case R.id.my_users2:
                intent = new Intent(this, MyUsersActivity.class);
                intent.putExtra("level", MyUsersActivity.level_2);
                startActivity(intent);
                break;
            case R.id.copy:
                if (getUserBean().is_dudu != 0) {
                    CommonUtil.copy2ShearPlate(this, mRequestCode.getText().toString().trim());
                    Toast.makeText(this, "复制成功", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(this, AssertWebActivity.class);
                    i.putExtra("title", "介绍VIP");
                    i.putExtra("path", "file:///android_asset/introduceVIP/topic_q_new.html");
                    startActivity(i);
                }
                break;
            case R.id.request_code_view:
                //                if (getUserBean().is_dudu == 0) {
                //                    Intent i = new Intent(this, AssertWebActivity.class);
                //                    i.putExtra("title", "成为嘟嘟卡VIP");
                //                    i.putExtra("path", "file:///android_asset/applyVIP/topic_q_jp.html");
                //                    startActivity(i);
                //                }
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_spread_center;
    }

    @Override
    public void refreshView(SpreadCenterBean data, boolean isLoadMore) {
        mFrozen.setText("￥ " + data.frozen_distribute);
        mTotalMoney.setText("￥ " + getUserBean().distribut_money);
        mUserNumber1.setText(data.first_num + "");
        mUserNumber2.setText(data.second_num + "");
    }

    @Override
    public void refreshList(List<SpreadCenterBean.InfoBean> list, boolean isLoadMore) {
        if (isLoadMore){
            if (list==null||list.size()==0){
                mAdapter.loadMoreEnd();
                return;
            }
            mAdapter.addData(list);
        }
        else
            mAdapter.setNewData(list);
        mAdapter.loadMoreComplete();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadData(true,mPosition);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mPosition = tab.getPosition();
        mPresenter.loadData(false,mPosition);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
