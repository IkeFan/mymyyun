package com.mmy.maimaiyun.model.main.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseRefreshActivity;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.customview.LoadingSmartLayout;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.GoodsBestBean;
import com.mmy.maimaiyun.data.bean.GoodsClazzBean;
import com.mmy.maimaiyun.data.bean.ScreeningBean;
import com.mmy.maimaiyun.model.main.adapter.GoodAdapter;
import com.mmy.maimaiyun.model.main.component.DaggerGoodsComponent;
import com.mmy.maimaiyun.model.main.module.GoodsModule;
import com.mmy.maimaiyun.model.main.presenter.GoodsPresenter;
import com.mmy.maimaiyun.model.main.view.GoodsView;
import com.mmy.maimaiyun.popup.GoodsPopup;
import com.mmy.maimaiyun.popup.SelectedPopup;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 精选商品
 */
public class GoodsActivity extends BaseRefreshActivity<GoodsPresenter> implements GoodsView, GoodAdapter
        .OnShopClickListener, TabLayout.OnTabSelectedListener, PopupWindow
        .OnDismissListener, GoodsPopup.OnMenuPopupItemClickListener {

    @Bind(R.id.loading_smart_layout)
    LoadingSmartLayout mLoadingSmartLayout;
    RecyclerView mRecyclerView;
    @Bind(R.id.tabs)
    TabLayout    mTabs;
    @Bind(R.id.clazz_root)
    LinearLayout mClazzRoot;
    @Bind(R.id.title_center_text)
    TextView     mTitle;
    private GoodsPopup    mOrderPopup;
    private GoodAdapter   mAdapter;
    private int           tabPosition;
    private int           mOrderPosition;
    private boolean       mSalesVolume;
    private SelectedPopup mSelectedPopup;
    public static final int TAG_ORDER  = 0;
    public static final int TAG_SERVER = 1;
    private GoodsPopup mServerPopup;


    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerGoodsComponent.builder()
                .appComponent(appComponent)
                .goodsModule(new GoodsModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mRecyclerView = mLoadingSmartLayout.getListView();
        mTitle.setText("精选商品");
        mOrderPopup = new GoodsPopup(this, TAG_ORDER);
        mServerPopup = new GoodsPopup(this, TAG_SERVER);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(UIUtil.dp2px(this, 10)));
        mAdapter = new GoodAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mSelectedPopup = new SelectedPopup(this);
    }

    @Override
    public void initData() {
        mPresenter.loadTabsData();
        ArrayList<String> list = new ArrayList<>();
        list.add("综合排序");
        list.add("价格由低到高");
        list.add("价格由高到低");
        mOrderPopup.setData(list);
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("有货优先");
        list2.add("促销商品");
        mServerPopup.setData(list2);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mOrderPopup.setItemClickListener(this);
        mServerPopup.setItemClickListener(this);
        mAdapter.setOnShopClickListener(this);
        mTabs.setOnTabSelectedListener(this);
        mAdapter.setOnItemClickListener(mOnItemClickListener);
        mSelectedPopup.setOnDismissListener(this);
    }

    @OnClick({R.id.order, R.id.sales_volume, R.id.selected, R.id.server})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.order:
                mOrderPopup.showAsDropDown(mClazzRoot);
                break;
            case R.id.sales_volume://销量
                mSalesVolume = !mSalesVolume;
                mPresenter.loadGoodsData(tabPosition, mSalesVolume ? 3 : 4);
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
        return R.layout.activity_selected_goods;
    }

    @Override
    public void refreshTabs(List<GoodsClazzBean> tabs) {
        for (GoodsClazzBean str : tabs) {
            mTabs.addTab(mTabs.newTab().setText(str.cat_name));
        }
    }

    //商品item点击
    public BaseRecyclerViewAdapter.OnItemClickListener mOnItemClickListener = (view, position) -> mPresenter
            .onShopItemClick(position);

    @Override
    public void refreshGoods(List<GoodsBestBean> data) {
//        hideRefreshView();
        mAdapter.setData(data);
    }

    @Override
    public void refreshAttrPopup(List<ScreeningBean> data) {
        mSelectedPopup.setData(data);
    }


    @Override
    public void onShopClick(View view, int position) {
        mPresenter.add2ShopCar(position);
        //播放添加动画
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        tabPosition = tab.getPosition();
        mPresenter.onTabSelected(tabPosition, mOrderPosition);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onDismiss() {
        //已选择属性
        mPresenter.attrsSelected(tabPosition, mOrderPosition, mSelectedPopup.getAttrsValues());
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
                mOrderPosition = position+5;
                if (mServerPopup.isShowing())
                    mServerPopup.dismiss();
                break;
        }
        mPresenter.onTabSelected(tabPosition, mOrderPosition);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.onTabSelected(tabPosition, mOrderPosition);
    }
}
