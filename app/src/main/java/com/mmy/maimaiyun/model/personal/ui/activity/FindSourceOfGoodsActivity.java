package com.mmy.maimaiyun.model.personal.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.model.personal.adapter.FindSourceOfGoodAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerFindSourceOfGoodsComponent;
import com.mmy.maimaiyun.model.personal.module.FindSourceOfGoodsModule;
import com.mmy.maimaiyun.model.personal.presenter.FindSourceOfGoodsPresenter;
import com.mmy.maimaiyun.model.personal.view.FindSourceOfGoodsView;
import com.mmy.maimaiyun.popup.GoodsPopup;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 查找货源
 */
public class FindSourceOfGoodsActivity extends BaseActivity<FindSourceOfGoodsPresenter> implements
        FindSourceOfGoodsView, FindSourceOfGoodAdapter
        .OnShopClickListener, GoodsPopup.OnMenuPopupItemClickListener {

    @Bind(R.id.list)
    RecyclerView mRecyclerView;
    @Bind(R.id.tabs)
    TabLayout    mTabs;
    @Bind(R.id.clazz_root)
    LinearLayout mClazzRoot;
    @Bind(R.id.title_center_text)
    TextView     mTitle;
    private GoodsPopup              mGoodsPopup;
    private FindSourceOfGoodAdapter mAdapter;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerFindSourceOfGoodsComponent.builder()
                .appComponent(appComponent)
                .findSourceOfGoodsModule(new FindSourceOfGoodsModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("精选商品");
        mGoodsPopup = new GoodsPopup(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(UIUtil.dp2px(this, 10)));
        mAdapter = new FindSourceOfGoodAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mPresenter.loadTabsData();
//        ArrayList<String> list = new ArrayList<>();
//        list.add("综合排序");
//        list.add("价格由低到高");
//        list.add("价格由高到低");
//        mGoodsPopup.setData(list);
        mPresenter.loadGoodsData();
    }


    @Override
    public void initEvent() {
        super.initEvent();
        mGoodsPopup.setItemClickListener(this);
        mAdapter.setOnShopClickListener(this);
    }

    @OnClick({R.id.order})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.order:
                mGoodsPopup.showAsDropDown(mClazzRoot);
                break;
        }
    }

    @Override
    public void refreshTabs(List<String> tabs) {
        for (String str : tabs) {
            mTabs.addTab(mTabs.newTab().setText(str));
        }
    }

    @Override
    public void refreshGoods(List<Integer> data) {
        mAdapter.setData(data);
    }

    @Override
    public void onShopClick(View view, int position) {
    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_find_source_of_goods;
    }

    @Override
    public void onMenuPopupClick(View view, int position, int tag) {
        if (mGoodsPopup.isShowing())
            mGoodsPopup.dismiss();
    }
}
