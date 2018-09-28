package com.mmy.maimaiyun.model.personal.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.model.personal.adapter.ShopAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerShopClazzComponent;
import com.mmy.maimaiyun.model.personal.module.ShopClazzModule;
import com.mmy.maimaiyun.model.personal.presenter.ShopClazzPresenter;
import com.mmy.maimaiyun.model.personal.view.ShopClazzView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;

import butterknife.Bind;

public class ShopClazzActivity extends BaseActivity<ShopClazzPresenter> implements ShopClazzView {

    @Bind(R.id.title_center_text)
    TextView mTitle;
    @Bind(R.id.list)
    RecyclerView mList;
    private ShopAdapter mAdapter;
    @Bind(R.id.tabs)
    TabLayout mTabs;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerShopClazzComponent.builder()
                .appComponent(appComponent)
                .shopClazzModule(new ShopClazzModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("XXXX商铺");
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
        mPresenter.loadData();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_shop_clazz;
    }

    @Override
    public void refreshList(List<String> data) {
        mAdapter.setData(data);
    }
}
