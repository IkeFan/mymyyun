package com.mmy.maimaiyun.model.personal.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.able.OnItemInnerClickListener;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.model.personal.adapter.GoodManageAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerGoodManageComponent;
import com.mmy.maimaiyun.model.personal.module.GoodManageModule;
import com.mmy.maimaiyun.model.personal.presenter.GoodManagePresenter;
import com.mmy.maimaiyun.model.personal.view.GoodManageView;
import com.mmy.maimaiyun.popup.ChangeMoneyPopup;
import com.mmy.maimaiyun.popup.GoodManagerClazzPopup;
import com.mmy.maimaiyun.popup.OffShelfPopup;
import com.mmy.maimaiyun.popup.adapter.GoodManageClazzPopupAdapter;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class GoodManageActivity extends BaseActivity<GoodManagePresenter> implements GoodManageView,
        OnItemInnerClickListener {

    @Bind(R.id.title_center_text)
    TextView     mTitle;
    @Bind(R.id.title_right_text)
    TextView     mRight;
    @Bind(R.id.list)
    RecyclerView mList;
    @Bind(R.id.select_yes)
    View         mSelectYes;
    @Bind(R.id.select_no)
    View         mSelectNo;


    private GoodManageAdapter     mAdapter;
    private boolean               mIsSelect;
    private GoodManagerClazzPopup mGoodManagerClazzPopup;
    private OffShelfPopup         mOffShelfPopup;
    private ChangeMoneyPopup      mChangeMoneyPopup;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerGoodManageComponent.builder()
                .appComponent(appComponent)
                .goodManageModule(new GoodManageModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("商品管理");
        mRight.setText("批量");
        mList.setLayoutManager(new LinearLayoutManager(this));
        int px = UIUtil.dp2px(this, 7);
        mList.addItemDecoration(new SpacesItemDecoration(px, 0, 0, 0));
        mAdapter = new GoodManageAdapter(this);
        mList.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mPresenter.loadData();
    }

    @Override
    public void initEvent() {
        mAdapter.setOnItemInnerClickListener(this);
    }

    @OnClick({R.id.title_right_text})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.title_right_text:
                mIsSelect = !mIsSelect;
                mRight.setTextColor(mIsSelect ? getResources().getColor(R.color.colorPrimary) :
                        getResources().getColor(R.color.normal_text_color));
                mSelectNo.setVisibility(mIsSelect ? View.GONE : View.VISIBLE);
                mSelectYes.setVisibility(mIsSelect ? View.VISIBLE : View.GONE);
                mAdapter.setSelect(mIsSelect);
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_good_manage;
    }

    @Override
    public void refreshList(List<String> data) {
        mAdapter.setData(data);
    }

    @Override
    public void onItemInnerClick(View view, int position, int type) {
        switch (type) {
            case GoodManageAdapter.OFF_SHELF:
                showOfShelfPopup(view);
                break;
            case GoodManageAdapter.CANCEL_DISTRIBUTION:
                break;
            case GoodManageAdapter.CHANGE:
                showChangeMoney(view);
                break;
            case GoodManageAdapter.CLASSIFICATION:
                showGoodClassPopup(view);
                break;
            case GoodManageAdapter.PREVIEW:
                break;
        }
    }

    private void showChangeMoney(View view) {
        if (mChangeMoneyPopup == null)
            mChangeMoneyPopup = new ChangeMoneyPopup(this);
        mChangeMoneyPopup.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    private void showOfShelfPopup(View view) {
        if (mOffShelfPopup == null)
            mOffShelfPopup = new OffShelfPopup(this);
        mOffShelfPopup.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    //显示分类至弹窗
    private void showGoodClassPopup(View view) {
        if (mGoodManagerClazzPopup == null)
            mGoodManagerClazzPopup = new GoodManagerClazzPopup(this);
        GoodManageClazzPopupAdapter adapter = mGoodManagerClazzPopup.getAdapter();
        adapter.setOnItemClickListener((view1, position1) -> {
            adapter.selectItem(position1);
        });
        mGoodManagerClazzPopup.showAtLocation(view, Gravity.CENTER, 0, 0);
    }
}
