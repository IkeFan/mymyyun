package com.mmy.maimaiyun.model.main.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.adapter.BaseQuickAdapter;
import com.mmy.maimaiyun.adapter.BaseViewHolder;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.MainShopBean;
import com.mmy.maimaiyun.model.main.adapter.ShopAdapter;
import com.mmy.maimaiyun.model.main.component.DaggerShopComponent;
import com.mmy.maimaiyun.model.main.module.ShopModule;
import com.mmy.maimaiyun.model.main.presenter.ShopPresenter;
import com.mmy.maimaiyun.model.main.view.ShopView;
import com.mmy.maimaiyun.utils.UIUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 店铺
 */
public class ShopActivity extends BaseActivity<ShopPresenter> implements ShopView, BaseQuickAdapter
        .RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.list)
    RecyclerView mList;
    @Bind(R.id.title_center_text)
    TextView mTitle;
    private ShopAdapter mAdapter;
    private String      mShopId;
    private ImageView mLogo;
    private TextView mGoodCount;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerShopComponent.builder()
                .appComponent(appComponent)
                .shopModule(new ShopModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mList.setLayoutManager(new GridLayoutManager(this,2));
        mAdapter = new ShopAdapter(R.layout.item_special_offer, new ArrayList<>());
        mList.setAdapter(mAdapter);
        View header = LayoutInflater.from(this).inflate(R.layout.header_shop, null, false);
        mLogo = (ImageView) header.findViewById(R.id.logo);
        mGoodCount = (TextView) header.findViewById(R.id.good_count);
        mAdapter.addHeaderView(header);
        mList.addItemDecoration(new SpacesItemDecoration(UIUtil.dp2px(this,3)));

    }

    @Override
    public void initData() {
        mShopId = getIntent().getStringExtra("shop_id");
        mPresenter.loadData(mShopId, false);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_shop2;
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadData(mShopId, true);
    }

    @Override
    public void refreshHeader(MainShopBean shop) {
        Picasso.with(this)
                .load(shop.shop.logo)
                .placeholder(R.mipmap.ic_launcher_trad_food)
                .error(R.mipmap.ic_launcher_trad_food)
                .into(mLogo);
        mTitle.setText(shop.shop.shop_name);
        mGoodCount.setText("全部商品 ("+shop.count+")");
    }

    @Override
    public void refreshList(List<MainShopBean.GoodsBean> goods, boolean isLoadMore) {
        if (isLoadMore)
            if (goods.size() == 0){
                mAdapter.loadMoreEnd();
                return;
            }
            else
                mAdapter.addData(goods);
        else
            mAdapter.setNewData(goods);
        mAdapter.loadMoreComplete();
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, BaseViewHolder baseViewHolder, int position) {
        MainShopBean.GoodsBean item = (MainShopBean.GoodsBean) adapter.getItem(position);
        mPresenter.openGoodInfo(item);
    }
}
