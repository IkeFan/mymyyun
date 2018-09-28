package com.mmy.maimaiyun.model.main.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.customview.InnerViewPager;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.BannerBean;
import com.mmy.maimaiyun.data.bean.SubCateBean;
import com.mmy.maimaiyun.data.bean.SubGoodsInfoBean;
import com.mmy.maimaiyun.helper.AutoScrollTask;
import com.mmy.maimaiyun.model.main.adapter.OverseasAdapter;
import com.mmy.maimaiyun.model.main.component.DaggerOverseasShoppingComponent;
import com.mmy.maimaiyun.model.main.module.OverseasShoppingModule;
import com.mmy.maimaiyun.model.main.presenter.OverseasShoppingPresenter;
import com.mmy.maimaiyun.model.main.view.OverseasShoppingView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 海外购
 */
public class OverseasShoppingActivity extends BaseActivity<OverseasShoppingPresenter> implements
        OverseasShoppingView, TabLayout.OnTabSelectedListener, BaseRecyclerViewAdapter.OnItemClickListener {

    @Inject
    protected Handler        mHandler;
    @Bind(R.id.banners)
    public    InnerViewPager mViewPager;
    @Bind(R.id.points)
    public    LinearLayout   mPoints;
    @Bind(R.id.tabs)
    TabLayout    mTabLayout;
    @Bind(R.id.list)
    RecyclerView mContainer;


    private AutoScrollTask  mAutoScrollTask;
    private OverseasAdapter mAdapter;
    private int             mPosition;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerOverseasShoppingComponent.builder()
                .appComponent(appComponent)
                .overseasShoppingModule(new OverseasShoppingModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mAdapter = new OverseasAdapter(this);
        mContainer.setAdapter(mAdapter);
        mContainer.setLayoutManager(new LinearLayoutManager(this));
        mContainer.addItemDecoration(new SpacesItemDecoration(UIUtil.dp2px(this, 5)));
    }

    @Override
    public void initData() {
        mPresenter.loadBanner();
        mPresenter.loadClass();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mTabLayout.setOnTabSelectedListener(this);
        mAdapter.setOnItemClickListener(this);
    }

    @OnClick({R.id.search})
    public void click(View view){
        switch (view.getId()) {
            case R.id.search:
                Intent intent = new Intent(this, SearchActivity.class);
                intent.putExtra("type",SearchActivity.OVERSEAS);
                startActivity(intent);
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_overseas_shopping;
    }

    @Override
    public void showBanner(List<BannerBean> data) {
        mAutoScrollTask = new AutoScrollTask(mViewPager, mPoints, mHandler, this);
        mAutoScrollTask.setData(data);
        mAutoScrollTask.start();
    }

    @Override
    public void refreshGoods(List<SubGoodsInfoBean> data) {
        mAdapter.setData(data);
    }

    @Override
    public void refreshClass(List<SubCateBean> data) {
        for (SubCateBean bean : data) {
            mTabLayout.addTab(mTabLayout.newTab().setText(bean.cat_name));
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mAutoScrollTask != null && !mAutoScrollTask.isStart())
            mAutoScrollTask.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mAutoScrollTask != null)
            mAutoScrollTask.stop();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mPosition = tab.getPosition();
        mPresenter.onTabSelected(mPosition);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onItemClick(View view, int position) {
        mPresenter.openInfoPage(position);
    }

}
