package com.mmy.maimaiyun.model.main.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.OnlyNewBean;
import com.mmy.maimaiyun.model.main.adapter.OnlyNewAdapter;
import com.mmy.maimaiyun.model.main.component.DaggerOnlyNewComponent;
import com.mmy.maimaiyun.model.main.module.OnlyNewModule;
import com.mmy.maimaiyun.model.main.presenter.OnlyNewPresenter;
import com.mmy.maimaiyun.model.main.view.OnlyNewView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;

import butterknife.Bind;

/**
 * 新人特惠
 */
public class OnlyNewActivity extends BaseActivity<OnlyNewPresenter> implements OnlyNewView, BaseRecyclerViewAdapter.OnItemClickListener {


    @Bind(R.id.title_center_text)
    TextView     mTitleCenterText;
    @Bind(R.id.list)
    RecyclerView mList;
    private OnlyNewAdapter mAdapter;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerOnlyNewComponent.builder()
                .appComponent(appComponent)
                .onlyNewModule(new OnlyNewModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitleCenterText.setText("新人特惠");
        mList.setLayoutManager(new GridLayoutManager(this,2));
        int px = UIUtil.dp2px(this, 5);
        mList.addItemDecoration(new SpacesItemDecoration(0,px,0,px));
        mAdapter = new OnlyNewAdapter(this);
        mList.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mPresenter.loadData();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mAdapter.addOnItemCliclListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_only_new;
    }

    @Override
    public void refreshView(List<OnlyNewBean.GoodsBean> goods) {
        mAdapter.setData(goods);
    }


    @Override
    public void onItemClick(View view, int position) {
        mPresenter.openGoodInfo(position);
    }
}
