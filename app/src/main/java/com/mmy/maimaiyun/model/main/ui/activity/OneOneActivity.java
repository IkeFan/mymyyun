package com.mmy.maimaiyun.model.main.ui.activity;

import android.Manifest;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.SubCateBean;
import com.mmy.maimaiyun.data.bean.SubGoodsInfoBean;
import com.mmy.maimaiyun.model.main.adapter.OneOneClassAdapter;
import com.mmy.maimaiyun.model.main.adapter.OneOneGoodsAdapter;
import com.mmy.maimaiyun.model.main.component.DaggerOneOneComponent;
import com.mmy.maimaiyun.model.main.module.OneOneModule;
import com.mmy.maimaiyun.model.main.presenter.OneOnePresenter;
import com.mmy.maimaiyun.model.main.view.OneOneView;
import com.squareup.picasso.Picasso;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.List;

import butterknife.Bind;

/**
 * 一品一县
 */
public class OneOneActivity extends BaseActivity<OneOnePresenter> implements OneOneView, BaseRecyclerViewAdapter.OnItemClickListener {

    @Bind(R.id.title_center_text)
    TextView     mTitleCenterText;
    @Bind(R.id.clazz)
    RecyclerView mClazz;
    @Bind(R.id.icon)
    ImageView    mIcon;
    @Bind(R.id.goods)
    RecyclerView mGoods;
    private OneOneClassAdapter mOneClassAdapter;
    private OneOneGoodsAdapter mOneGoodsAdapter;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerOneOneComponent.builder()
                .appComponent(appComponent)
                .oneOneModule(new OneOneModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitleCenterText.setText("一品一县");
        mClazz.setLayoutManager(new LinearLayoutManager(this));
        mGoods.setLayoutManager(new LinearLayoutManager(this));
        mOneClassAdapter = new OneOneClassAdapter(this);
        mClazz.setAdapter(mOneClassAdapter);
        mOneGoodsAdapter = new OneOneGoodsAdapter(this);
        mGoods.setAdapter(mOneGoodsAdapter);
        RxPermissions.getInstance(this)
                .request(Manifest.permission.CAMERA)
                .subscribe(s->{
                    if (s){

                    }else {
                        //拒绝
                    }
                });
    }

    @Override
    public void initData() {
        mPresenter.loadClass();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mOneClassAdapter.addOnItemCliclListener(this);
        mOneGoodsAdapter.addOnItemCliclListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mPresenter.openGoodInfo(position);
            }
        });
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_one_one;
    }

    @Override
    public void refreshClassView(List<SubCateBean> classBean) {
        mOneClassAdapter.setData(classBean);
    }

    @Override
    public void refreshGoodsView(List<SubGoodsInfoBean> subGoodsInfo, String cat_pic) {
        mOneGoodsAdapter.setData(subGoodsInfo);
        Picasso.with(this).load(cat_pic).error(R.mipmap.ic_def).placeholder(R.mipmap.ic_def).into(mIcon);
    }

    @Override
    public void onItemClick(View view, int position) {
        mPresenter.selectClass(position);
        mOneClassAdapter.selectItem(position);
    }
}
