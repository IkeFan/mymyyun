package com.mmy.maimaiyun.model.main.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.customview.LoadingSmartLayout;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.GoodNewsItemBean;
import com.mmy.maimaiyun.data.bean.SearchBean;
import com.mmy.maimaiyun.model.main.adapter.SearchGoodsAdapter;
import com.mmy.maimaiyun.model.main.adapter.SearchNewsAdapter;
import com.mmy.maimaiyun.model.main.component.DaggerSearchComponent;
import com.mmy.maimaiyun.model.main.module.SearchModule;
import com.mmy.maimaiyun.model.main.presenter.SearchPresenter;
import com.mmy.maimaiyun.model.main.view.SearchView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * @创建者 lucas
 * @创建时间 2017-11-10 03-27-54
 * @描述 搜索界面
 */
public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchView, BaseRecyclerViewAdapter
        .OnItemClickListener {

    @Bind(R.id.search_name)
    EditText mSearchName;

    RecyclerView mList;
    private SearchGoodsAdapter mGoodsAdapter;
    private LoadingSmartLayout mLoadingSmartLayout;
    private String             mName;
    private boolean            mIsShowEmpty;
    public static final int GOOD         = 1;//首页
    public static final int NEWS         = 2;//新闻
    public static final int HOT          = 3;//热卖
    public static final int OVERSEAS     = 4;//海外
    public static final int VOLUME       = 5;//量贩优选
    public static final int EVERY_DAY    = 6;//每日优选
    public static final int HOT_TYPE     = 7;//品牌热卖
    public static final int SPECIAL     = 8;//品牌热卖
    private             int mCurrentType = GOOD;
    private SearchNewsAdapter mNewsAdapter;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerSearchComponent.builder()
                .appComponent(appComponent)
                .searchModule(new SearchModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mIsShowEmpty = getIntent().getBooleanExtra("isShowEmpty", false);
        mCurrentType = getIntent().getIntExtra("type", GOOD);
        mLoadingSmartLayout = findLoadingSmartLayout();
        mLoadingSmartLayout.switchStatus(LoadingSmartLayout.Status.EMPTY);
        mList = mLoadingSmartLayout.getListView();
        int px = UIUtil.dp2px(this, 5);
        mList.addItemDecoration(new SpacesItemDecoration(0, px, 0, px));

        //goods
        if (mCurrentType == GOOD || mCurrentType == HOT || mCurrentType == OVERSEAS) {
            mGoodsAdapter = new SearchGoodsAdapter(this);
            mList.setLayoutManager(new GridLayoutManager(this, 2));
            mList.setAdapter(mGoodsAdapter);
        }
        //news
        if (mCurrentType == NEWS) {
            mNewsAdapter = new SearchNewsAdapter(this);
            mList.setLayoutManager(new LinearLayoutManager(this));
            mList.setAdapter(mNewsAdapter);
        }
    }

    @Override
    public void initData() {
    }

    @Override
    public void initEvent() {
        super.initEvent();
        if (mGoodsAdapter != null)
            mGoodsAdapter.addOnItemCliclListener(this);
        if (mNewsAdapter != null)
            mNewsAdapter.addOnItemCliclListener(this);
    }

    @OnClick({R.id.search, R.id.back})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.search:
                if (mIsShowEmpty)
                    return;
                searchData();
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    private void searchData() {
        mName = mSearchName.getText().toString().trim();
        if (TextUtils.isEmpty(mName)) {
            mLoadingSmartLayout.switchStatus(LoadingSmartLayout.Status.EMPTY);
            return;
        }
        switch (mCurrentType) {
            case GOOD:
            case HOT:
            case OVERSEAS:
                mPresenter.searchGoods(mName, mCurrentType);
                break;
            case NEWS:
                mPresenter.searchNews(mName);
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    public void refreshGoodsView(List<SearchBean> data) {
        mGoodsAdapter.setData(data);
    }

    @Override
    public void refreshNewsView(List<GoodNewsItemBean> dataGoods) {
        mNewsAdapter.setData(dataGoods);
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (mCurrentType) {
            case GOOD:
                mPresenter.openGoodInfo(position);
                break;
            case NEWS:
                mPresenter.openNewsInfo(position);
                break;
        }
    }
}
