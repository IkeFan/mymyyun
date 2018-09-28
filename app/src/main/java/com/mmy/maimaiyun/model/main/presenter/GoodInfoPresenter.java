package com.mmy.maimaiyun.model.main.presenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.Toast;

import com.mmy.maimaiyun.BuildConfig;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.BannerBean;
import com.mmy.maimaiyun.data.bean.CommentBean;
import com.mmy.maimaiyun.data.bean.GoodInfoBannerBean;
import com.mmy.maimaiyun.data.bean.GoodsInfoAttrBean;
import com.mmy.maimaiyun.data.bean.GoodsInfoBean;
import com.mmy.maimaiyun.data.bean.NewPriceBean;
import com.mmy.maimaiyun.helper.ShareHelper;
import com.mmy.maimaiyun.model.main.model.GoodInfoModel;
import com.mmy.maimaiyun.model.main.ui.activity.AllCommentActivity;
import com.mmy.maimaiyun.model.main.ui.activity.MainWebActivity;
import com.mmy.maimaiyun.model.main.view.GoodInfoView;
import com.mmy.maimaiyun.model.shopping.ui.activity.ConfOrderActivity;
import com.mmy.maimaiyun.utils.Constants;
import com.mmy.maimaiyun.utils.UIUtil;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.CSCustomServiceInfo;


/**
 * @创建者 lucas
 * @创建时间 2017/9/5 0005 11:14
 * @描述 TODO
 */

public class GoodInfoPresenter extends BasePresenter<GoodInfoView> {
    private       GoodInfoView mView;
    private final BaseActivity mActivity;
    @Inject
    GoodInfoModel mModel;
    @Inject
    ShareHelper   mShareHelper;

    private GoodsInfoBean mData;

    @Inject
    public GoodInfoPresenter(GoodInfoView view) {
        mView = view;
        mActivity = (BaseActivity) view;
    }

    public void loadBanner(int id) {
        mModel.loadBanner(new OnLoadDataListener<BaseBean<List<GoodInfoBannerBean>>>() {

            @Override
            public void onResponse(String body, BaseBean<List<GoodInfoBannerBean>> data, IBean iBean) {
                if (iBean.status == 1 && data.data != null) {
                    ArrayList<BannerBean> beans = new ArrayList<>();
                    for (GoodInfoBannerBean datum : data.data) {
                        BannerBean bannerBean = new BannerBean();
                        bannerBean.logo = datum.pic;
                        beans.add(bannerBean);
                    }
                    mView.showBanner(beans);
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<GoodInfoBannerBean>>>() {
                }.getType();
            }
        }, mView.getUserBean().token, id);
    }

    public void loadDetail(int id) {
        mModel.loadGoodsDetailData(new OnLoadDataListener<BaseBean<GoodsInfoBean>>() {
            @Override
            public void onResponse(String body, BaseBean<GoodsInfoBean> data) {
                mData = data.data;
                if (mData != null && mData.coupons != null)
                    mView.initCoupon(mData.coupons);
                loadComment(data.data.id, true);
                mView.showDetail(mData);
                mView.initColorLogo(mData.logo);
                getGoodsAttr();
            }

            @Override
            public void onFailure(String body, Throwable t) {
                //                mView.hideLoading();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<GoodsInfoBean>>() {
                }.getType();
            }
        }, mView.getUserBean().token, mView.getUserBean().member_id, id);
    }

    public void getGoodsAttr() {
        mModel.loadGoodsAttr(new OnLoadDataListener<BaseBean<GoodsInfoAttrBean>>() {
            @Override
            public void onResponse(String body, BaseBean<GoodsInfoAttrBean> data, IBean iBean) {
                if (iBean.status == 1 && data.data != null) {
                    mView.initColorPopup(data.data.spec);
                    mView.initParameterPopup(data.data.attribute);
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<GoodsInfoAttrBean>>() {
                }.getType();
            }
        }, mView.getUserBean().token, mData.id + "");
    }

    public void openConfOrderPage(String goodsAttrs, String goodsCount) {
        Intent intent = new Intent(mActivity, ConfOrderActivity.class);
        String id = mData.id;
        intent.putExtra("goods_id", id);
        String goodsCount1 = goodsCount;
        intent.putExtra("goods_number", goodsCount1);
        String goodsAttrs1 = goodsAttrs;
        intent.putExtra("goods_attr_id", goodsAttrs1);
        String member_id = mView.getUserBean().member_id;
        intent.putExtra("member_id", member_id);
        mActivity.startActivity(intent);
    }

    int pageCount = 1;

    public void loadComment(String goods_id, boolean isShowAll) {
        String limit = Constants.LIMIT + "";
        String pageNum = pageCount++ + "";
        String reply = 1 + "";
        if (isShowAll) {
            limit = "";
            pageNum = "";
        }
        mModel.getComment(new OnLoadDataListener<BaseBean<List<CommentBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<CommentBean>> data) {
                if (data.status == 1)
                    mView.showComment(data.data);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<CommentBean>>>() {
                }.getType();
            }
        }, mView.getUserBean().token, goods_id, null, reply, limit, pageNum);
    }

    public void add2Shop(String id, String goodsAttrs, String goodsCount) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", mView.getUserBean().token);
        map.put("member_id", mView.getUserBean().member_id);
        map.put("goods_id", id);
        map.put("attr", goodsAttrs);
        map.put("goods_number", goodsCount);
        mModel.add2ShopCar(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data) {
                Toast.makeText(mActivity, data.info, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>() {
                }.getType();
            }
        }, map);
    }

    //收藏
    public void addCollect(String id) {
        mModel.addCollect(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data) {
                Toast.makeText(mActivity, data.info, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>() {
                }.getType();
            }
        }, mView.getUserBean().token, mView.getUserBean().member_id, id);
    }

    public void openAllCommentPage() {
        mView.openActivity(AllCommentActivity.class, "goods_id=" + mData.id);
    }

    //分享
    public void share() {
        Picasso.with(mApp).load(mData.logo).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                String path = UIUtil.saveBitmap(mApp, bitmap);
                String url = Constants.SHARE_GOOD_URL + mData.id + ".html?invite_code=" + mView.getUserBean()
                        .invite_code;
                mShareHelper.showShare(mData.goods_name, mData.goods_name, path, url);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    //打开会话
    public void openConversation() {
        Intent intent = new Intent(mActivity, MainWebActivity.class);
        intent.putExtra("url", Constants.CUSTOMER_SERVICE);
        intent.putExtra("title", "客服");
        mActivity.startActivity(intent);
        //        if (mData == null || mData.member_id == null)
        //            return;
        //        //判断是否是自营
        //        if ("0".equals(mData.shop_id)) {
        //            openService();
        //            return;
        //        }
        //        RongIM.getInstance().startConversation(mActivity, Conversation.ConversationType.PRIVATE, mData
        // .member_id,
        //                mData.member_name);
    }

    private void openService() {//首先需要构造使用客服者的用户信息
        String nickname = mView.getUserBean().nickname;
        CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
        CSCustomServiceInfo csInfo = csBuilder.nickName(TextUtils.isEmpty(nickname) ? "游客" : nickname)
                .birthday(mView.getUserBean().head_pic)
                .build();
        /**
         * 启动客户服聊天界面。
         *
         * @param context           应用上下文。
         * @param customerServiceId 要与之聊天的客服 Id。
         * @param title             聊天的标题，如果传入空值，则默认显示与之聊天的客服名称。
         * @param customServiceInfo 当前使用客服者的用户信息。{@link CSCustomServiceInfo}
         */
        RongIM.getInstance().startCustomerServiceChat(mActivity, BuildConfig.RONG_SERVICE, "在线客服", csInfo);
    }

    public void getNewPrice(String goodsAttrs) {
        String z = mData.id;
        String goodsAttrs1 = goodsAttrs;
        mModel.getNewPrice(new OnLoadDataListener<BaseBean<NewPriceBean>>() {
            @Override
            public void onResponse(String body, BaseBean<NewPriceBean> data, IBean iBean) {
                super.onResponse(body, data, iBean);
                if (iBean.status == 1)
                    mView.refreshNesPrice(data.data.goods_price);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<NewPriceBean>>() {
                }.getType();
            }
        }, mView.getUserBean().token, z, goodsAttrs1);
    }

    //选择优惠券
    public void selectCoupon(int position) {
        if (mData != null && mData.coupons != null) {
            String id = mData.coupons.get(position).id;
            mModel.receiveCoupon(new OnLoadDataListener<BaseBean>() {
                @Override
                public void onResponse(String body, BaseBean data, IBean iBean) {
                    super.onResponse(body, data, iBean);
                    Toast.makeText(mActivity, iBean.info, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(String body, Throwable t) {
                    Toast.makeText(mActivity, "领取失败", Toast.LENGTH_SHORT).show();
                }

                @Override
                public Type getBeanType() {
                    return new TypeToken<BaseBean>() {
                    }.getType();
                }
            }, mView.getUserBean().token, id, mView.getUserBean().member_id);
        }
    }
}
