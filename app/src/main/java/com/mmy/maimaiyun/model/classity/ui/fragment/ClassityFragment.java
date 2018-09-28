package com.mmy.maimaiyun.model.classity.ui.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseFragment;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.ClassBean;
import com.mmy.maimaiyun.model.classity.adapter.ClassityLeftAdapter;
import com.mmy.maimaiyun.model.classity.adapter.ClassityRightAdapter;
import com.mmy.maimaiyun.model.classity.component.DaggerClassityComponent;
import com.mmy.maimaiyun.model.classity.module.ClassityModule;
import com.mmy.maimaiyun.model.classity.presenter.ClassityPresenter;
import com.mmy.maimaiyun.model.classity.ui.activity.ClassInfoActivity;
import com.mmy.maimaiyun.model.classity.view.ClassityView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.LinkedHashMap;
import java.util.List;

import butterknife.Bind;

/**
 * @创建者 lucas
 * @创建时间 2017/8/16 0016 17:57
 * @描述 分类
 */

public class ClassityFragment extends BaseFragment<ClassityPresenter> implements ClassityView,
        BaseRecyclerViewAdapter.OnItemClickListener, ClassityRightAdapter.OnInnerClassItemClickListener {


    @Bind(R.id.title_center_text)
    TextView     mTitleCenterText;
    @Bind(R.id.title_back)
    View mBack;
    @Bind(R.id.left_list)
    RecyclerView mLeftList;
    @Bind(R.id.right_list)
    RecyclerView mRightList;
    private ClassityLeftAdapter  mLeftAdapter;
    private ClassityRightAdapter mRightAdapter;

    @Override
    protected void initView() {
        mBack.setVisibility(View.GONE);
        mTitleCenterText.setText("分类");
        int px = UIUtil.dp2px(mActivity, 2);
        mLeftList.setLayoutManager(new LinearLayoutManager(mActivity));
        mRightList.setLayoutManager(new LinearLayoutManager(mActivity));
        mLeftAdapter = new ClassityLeftAdapter(mActivity);
        mLeftList.setAdapter(mLeftAdapter);
        mLeftList.addItemDecoration(new SpacesItemDecoration(px, 0, 0, 0));
        mRightAdapter = new ClassityRightAdapter(mActivity);
        mRightList.setAdapter(mRightAdapter);
        mRightList.addItemDecoration(new SpacesItemDecoration(px, 0, 0, 0));
    }

    @Override
    protected void initData() {
        mPresenter.loadLeftData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mLeftAdapter.setOnItemClickListener(this);
        mRightAdapter.setOnInnerClassItemClickListener(this);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_classity;
    }

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerClassityComponent
                .builder()
                .appComponent(appComponent)
                .classityModule(new ClassityModule(this))
                .build().inject(this);
    }

    @Override
    public void refreshLeft(List<ClassBean> data) {
        mLeftAdapter.setData(data);
    }

    @Override
    public void refreshRight(LinkedHashMap<ClassBean, List<ClassBean>> data) {
        mRightAdapter.setData(data);
    }

    @Override
    public void onItemClick(View view, int position) {
        mLeftAdapter.selectItem(position);
        mPresenter.loadRightData(position);
    }

    @Override
    public void onClassItemClick(View view, String id, String name) {
        openActivity(ClassInfoActivity.class, "cat_id=" + id + ",name=" + name);
    }
}
