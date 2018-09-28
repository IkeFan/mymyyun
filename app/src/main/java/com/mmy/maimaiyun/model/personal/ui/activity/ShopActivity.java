package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.customview.InnerViewPager;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.BannerBean;
import com.mmy.maimaiyun.helper.AutoScrollTask;
import com.mmy.maimaiyun.model.personal.adapter.ShopAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerShopComponent;
import com.mmy.maimaiyun.model.personal.module.ShopModule;
import com.mmy.maimaiyun.model.personal.presenter.ShopPresenter;
import com.mmy.maimaiyun.model.personal.view.ShopView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 商铺
 */
public class ShopActivity extends BaseActivity<ShopPresenter> implements ShopView {

    @Bind(R.id.banners)
    public  InnerViewPager mViewPager;
    @Bind(R.id.points)
    public  LinearLayout   mPoints;
    private AutoScrollTask mAutoScrollTask;
    @Bind(R.id.list)
    RecyclerView mList;
    private ShopAdapter mAdapter;
    @Bind(R.id.tabs)
    TabLayout mTabs;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerShopComponent.builder()
                .appComponent(appComponent)
                .shopModule(new ShopModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        int px = UIUtil.dp2px(this, 2);
        mList.setLayoutManager(new GridLayoutManager(this, 2));
        mList.addItemDecoration(new SpacesItemDecoration(px, px, px, px));
        mAdapter = new ShopAdapter(this);
        mList.setAdapter(mAdapter);
        mTabs.addTab(mTabs.newTab().setText("全部商品"));
        mTabs.addTab(mTabs.newTab().setText("促销商品"));
        View view = LayoutInflater.from(this).inflate(R.layout.tab_icon_2_right, null);
        TextView text = (TextView) view.findViewById(R.id.text);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        text.setText("默认排序");
        icon.setImageResource(R.mipmap.shopping_bottom_normal);
        TabLayout.Tab tab = mTabs.newTab().setCustomView(view);
        mTabs.addTab(tab);
    }

    @Override
    public void initData() {
        mPresenter.loadBanner();
        mPresenter.loadListData();
    }

    @Override
    public void showBanner(List<BannerBean> data) {
        mAutoScrollTask = new AutoScrollTask(mViewPager, mPoints, mHandler, this);
        mAutoScrollTask.setData(data);
        mAutoScrollTask.start();
    }

    @Override
    public void refreshList(List<String> data) {
        mAdapter.setData(data);
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

    @OnClick({R.id.clazz})
    public void click(View view){
        switch (view.getId()) {
            case R.id.clazz:
                startActivity(new Intent(this,ShopClazzChoiceActivity.class));
//                startActivity(new Intent(this,ShopClazzActivity.class));
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_shop;
    }
}
