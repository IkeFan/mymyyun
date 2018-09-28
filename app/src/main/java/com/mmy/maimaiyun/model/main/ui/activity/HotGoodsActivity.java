package com.mmy.maimaiyun.model.main.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseRefreshActivity;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.customview.LoadingSmartLayout;
import com.mmy.maimaiyun.data.bean.BannerBean;
import com.mmy.maimaiyun.data.bean.HotGoodsBean;
import com.mmy.maimaiyun.data.bean.HotGoodsRecommemdBean;
import com.mmy.maimaiyun.data.bean.ScreeningBean;
import com.mmy.maimaiyun.helper.PushRefreshScrollHelper;
import com.mmy.maimaiyun.model.main.adapter.HotGoodsAdapter;
import com.mmy.maimaiyun.model.main.component.DaggerHotGoodsCommend;
import com.mmy.maimaiyun.model.main.module.HotGoodsModule;
import com.mmy.maimaiyun.model.main.presenter.HotGoodsPresenter;
import com.mmy.maimaiyun.model.main.view.HotGoodsView;
import com.mmy.maimaiyun.popup.GoodsPopup;
import com.mmy.maimaiyun.popup.SelectedPopup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.mmy.maimaiyun.model.main.ui.activity.GoodsActivity.TAG_ORDER;
import static com.mmy.maimaiyun.model.main.ui.activity.GoodsActivity.TAG_SERVER;

/**
 * 热卖商品
 */
public class HotGoodsActivity extends BaseRefreshActivity<HotGoodsPresenter> implements HotGoodsView,
        BaseRecyclerViewAdapter.OnItemClickListener, HotGoodsAdapter.OnHotGoodsItemClickListener, GoodsPopup
                .OnMenuPopupItemClickListener, PopupWindow.OnDismissListener, PushRefreshScrollHelper.OnPushLoadMoreListener {
    @Bind(R.id.loading_smart_layout)
    LoadingSmartLayout mLoadingSmartLayout;
    RecyclerView mContentList;
    @Bind(R.id.clazz_root)
    LinearLayout mClazzRoot;
    private HotGoodsAdapter              mAdapter;
    private GoodsPopup                   mOrderPopup;
    private GoodsPopup                   mServerPopup;
    private boolean                      mSalesVolume;
    private SelectedPopup                mSelectedPopup;
    private int                          mOrderPosition;
    private LinearLayoutManager          mLayoutManager;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerHotGoodsCommend.builder()
                .appComponent(appComponent)
                .hotGoodsModule(new HotGoodsModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mContentList = mLoadingSmartLayout.getListView();
        mLayoutManager = new LinearLayoutManager(this);
        mContentList.setLayoutManager(mLayoutManager);
        mAdapter = new HotGoodsAdapter(this, mHandler,new PushRefreshScrollHelper(mContentList));
        mContentList.setAdapter(mAdapter);
        mAdapter.setOnHotGoodsItemClickListener(this);
        mOrderPopup = new GoodsPopup(this, TAG_ORDER);
        mServerPopup = new GoodsPopup(this, TAG_SERVER);
        mSelectedPopup = new SelectedPopup(this);
    }

    @Override
    public void initData() {
        mAdapter.setData(new ArrayList<HotGoodsBean.GoodsBean>());
        ArrayList<String> list = new ArrayList<>();
        list.add("综合排序");
        list.add("价格由低到高");
        list.add("价格由高到低");
        mOrderPopup.setData(list);
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("有货优先");
        list2.add("促销商品");
        mServerPopup.setData(list2);
        mPresenter.loadBanner();
        mPresenter.loadRecommend();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mOrderPopup.setItemClickListener(this);
        mServerPopup.setItemClickListener(this);
        mSelectedPopup.setOnDismissListener(this);
        mAdapter.setOnPushLoadMoreListener(this);
    }

    @OnClick({R.id.order, R.id.sales_volume, R.id.selected, R.id.server,R.id.search})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.search:
                Intent intent = new Intent(this, SearchActivity.class);
                intent.putExtra("type",SearchActivity.HOT);
                startActivity(intent);
                break;
            case R.id.order:
                mOrderPopup.showAsDropDown(mClazzRoot);
                break;
            case R.id.sales_volume://销量
                mSalesVolume = !mSalesVolume;
                mPresenter.loadGoodsData(mSalesVolume ? 3 : 4,true);
                break;
            case R.id.server:
                mServerPopup.showAsDropDown(mClazzRoot);
                break;
            case R.id.selected://筛选
                mSelectedPopup.showAtLocation(view, Gravity.CENTER, 0, 0);
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_hot_goods;
    }

    @Override
    public void refreshBanner(List<BannerBean> banners) {
        mAdapter.refreshBanner(banners);
    }

    @Override
    public void refreshRecommend(List<HotGoodsRecommemdBean> recommend) {
        mAdapter.refreshRecommend(recommend);
        mAdapter.setRecommendItemClickListener(this);
    }

    @Override
    public void refreshHotGoods(List<HotGoodsBean.GoodsBean> goods) {
        hideRefreshView();
        mAdapter.setData(goods);
    }

    @Override
    public void refreshAttrPopup(List<ScreeningBean> screening) {
        mSelectedPopup.setData(screening);
    }

    @Override
    public void loadMoreGoods(List<HotGoodsBean.GoodsBean> goods) {
        mAdapter.addData(goods);
    }

    @Override
    public void onHotItemClick(View view, int position) {
        mPresenter.onShopItemClick(position);
    }

    //为你推荐
    @Override
    public void onItemClick(View view, int position) {
        mPresenter.onRecommendItemClick(position);
    }

    @Override
    public void onMenuPopupClick(View view, int position, int tag) {
        switch (tag) {
            case TAG_ORDER:
                mOrderPosition = position;
                if (mOrderPopup.isShowing())
                    mOrderPopup.dismiss();
                break;
            case TAG_SERVER:
                mOrderPosition = position + 5;
                if (mServerPopup.isShowing())
                    mServerPopup.dismiss();
                break;
        }
        mPresenter.onTabSelected(mOrderPosition);
    }

    @Override
    public void onDismiss() {
        //已选择属性
        mPresenter.attrsSelected(mOrderPosition, mSelectedPopup.getAttrsValues());
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.loadRecommend();
    }

    @Override
    public void onLoadingMore() {
        mPresenter.loadGoodsData(mSalesVolume ? 3 : 4,false);
    }
}
