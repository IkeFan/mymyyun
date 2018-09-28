package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.data.bean.ShopInfoBean;
import com.mmy.maimaiyun.model.personal.component.DaggerShopManagerComponent;
import com.mmy.maimaiyun.model.personal.module.ShopManagerModule;
import com.mmy.maimaiyun.model.personal.presenter.ShopManagerPresenter;
import com.mmy.maimaiyun.model.personal.view.ShopManagerView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 店铺管理
 */
public class ShopManagerActivity extends BaseActivity<ShopManagerPresenter> implements ShopManagerView {

    @Bind(R.id.title_center_text)
    TextView mTitle;
    @Bind(R.id.toolbar)
    Toolbar  mToolbar;
    @Bind(R.id.auth)
    TextView mAuth;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerShopManagerComponent.builder()
                .appComponent(appComponent)
                .shopManagerModule(new ShopManagerModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("店铺管理");
    }

    @Override
    public void initData() {
        mPresenter.loadShopInfo();
    }

    @OnClick({R.id.auth, R.id.personal_shop, R.id.distribution, R.id.good_manage, R.id.shop, R.id.shop_qr,
            R.id.find_source, R.id.transaction, R.id.share})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.share://分享店铺
                mPresenter.share();
                break;
            case R.id.transaction:
                startActivity(new Intent(this, TransactionManagerActivity.class));
                break;
            case R.id.find_source:
                startActivity(new Intent(this, FindSourceOfGoodsActivity.class));
                break;
            case R.id.shop_qr:
                startActivity(new Intent(this, QrCodeActivity.class));
                break;
            case R.id.shop:
                startActivity(new Intent(this, ShopActivity.class));
                break;
            case R.id.good_manage:
                startActivity(new Intent(this, GoodManageActivity.class));
                break;
            case R.id.distribution:
                startActivity(new Intent(this, DistributionManagerActivity.class));
                break;
            case R.id.auth:
                mPresenter.openAuth();
                break;
            case R.id.personal_shop://商铺设置
                startActivity(new Intent(this, PersonalShopActivity.class));
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_shop_manager;
    }

    @Override
    public void refreshView(ShopInfoBean bean) {
        switch (bean.status) {
            case 0://审核中
                mAuth.setText("审核中");
                mAuth.setEnabled(false);
                break;
            case 1://已通过
                mAuth.setText("已通过");
                mAuth.setEnabled(false);
                break;
            case 2://已删除
                mAuth.setText("未审核");
                mAuth.setEnabled(true);
                break;
            case 3://未通过
                mAuth.setText("未通过");
                mAuth.setEnabled(true);
                break;
        }
    }
}
