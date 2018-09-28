package com.mmy.maimaiyun.model.personal.presenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.BuildConfig;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.ShopInfoBean;
import com.mmy.maimaiyun.helper.ShareHelper;
import com.mmy.maimaiyun.model.personal.model.ShopManagerModel;
import com.mmy.maimaiyun.model.personal.ui.activity.AuthActivity;
import com.mmy.maimaiyun.model.personal.view.ShopManagerView;
import com.mmy.maimaiyun.utils.UIUtil;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.reflect.Type;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/11/24 0024 16:18
 * @描述
 */

public class ShopManagerPresenter extends BasePresenter<ShopManagerView> {
    private ShopManagerView mView;

    @Inject
    ShopManagerModel mModel;
    private final BaseActivity mActivity;
    private BaseBean<ShopInfoBean> mData;
    @Inject
    ShareHelper mShareHelper;

    @Inject
    public ShopManagerPresenter(ShopManagerView view) {
        mView = view;
        mActivity = (BaseActivity) mView;
    }

    public void loadShopInfo() {
        mView.showLoading();
        mModel.getShopInfo(new OnLoadDataListener<BaseBean<ShopInfoBean>>() {
            @Override
            public void onResponse(String body, BaseBean<ShopInfoBean> data, IBean iBean) {
                super.onResponse(body, data, iBean);
                mView.hideLoading();
                if (iBean.status == 1) {
                    mData = data;
                    mView.refreshView(mData.data);
                    return;
                }
                Toast.makeText(mApp, iBean.info, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String body, Throwable t) {
                mView.hideLoading();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<ShopInfoBean>>() {
                }.getType();
            }
        }, mView.getUserBean().token, mView.getUserBean().member_id);
    }

    public void openAuth() {
        if (mData==null||mData.data==null)
            return;
        if (mData.data.status==0){
            Toast.makeText(mActivity, "认证中,请耐心等待", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mData.data.status==1){
            Toast.makeText(mActivity, "已认证", Toast.LENGTH_SHORT).show();
            return;
        }
        mActivity.startActivity(new Intent(mActivity, AuthActivity.class));
    }

    //分享
    public void share() {
        if (mData == null){
            Toast.makeText(mActivity, "分享失败，请检查网络", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mData.status==2){
            Toast.makeText(mActivity, "商铺未认证无法分享", Toast.LENGTH_SHORT).show();
            return;
        }
        Picasso.with(mApp).load(mData.data.logo).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                String path = UIUtil.saveBitmap(mApp, bitmap);
                mShareHelper.showShare(mData.data.shop_name, mData.data.desc, path, BuildConfig.HOST);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }
}
