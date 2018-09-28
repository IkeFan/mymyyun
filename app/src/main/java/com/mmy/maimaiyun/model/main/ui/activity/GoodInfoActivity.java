package com.mmy.maimaiyun.model.main.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.able.OnPopupMenuItemClickListener;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.customview.InnerViewPager;
import com.mmy.maimaiyun.data.bean.BannerBean;
import com.mmy.maimaiyun.data.bean.CommentBean;
import com.mmy.maimaiyun.data.bean.GoodsInfoAttrBean;
import com.mmy.maimaiyun.data.bean.GoodsInfoBean;
import com.mmy.maimaiyun.db.DaoSession;
import com.mmy.maimaiyun.db.RecordBean;
import com.mmy.maimaiyun.helper.AutoScrollTask;
import com.mmy.maimaiyun.model.main.component.DaggerGoodInfoComponent;
import com.mmy.maimaiyun.model.main.module.GoodInfoModule;
import com.mmy.maimaiyun.model.main.presenter.GoodInfoPresenter;
import com.mmy.maimaiyun.model.main.view.GoodInfoView;
import com.mmy.maimaiyun.popup.CouponPopup;
import com.mmy.maimaiyun.popup.GoodInfoColorPopup;
import com.mmy.maimaiyun.popup.ParameterPopup;
import com.mmy.maimaiyun.popup.ReviewPicPopup;
import com.mmy.maimaiyun.utils.CommonUtil;
import com.mmy.maimaiyun.utils.UIUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 商品详情
 */
public class GoodInfoActivity extends BaseActivity<GoodInfoPresenter> implements GoodInfoView,
        OnPopupMenuItemClickListener, CouponPopup.OnCouponItemClickListener {

    @Bind(R.id.banners)
    public InnerViewPager mViewPager;
    @Bind(R.id.points)
    LinearLayout mPoints;
    @Bind(R.id.good_name)
    TextView     mGoodName;
    @Bind(R.id.good_desc)
    TextView     mGoodDesc;
    @Bind(R.id.collection_status)
    CheckBox     mCollectionStatus;
    @Bind(R.id.price)
    TextView     mPrice;
    @Bind(R.id.detail_img)
    ImageView    mDetailImg;
    @Bind(R.id.add_collect)
    CheckBox     mCollect;
    @Bind(R.id.collection_count)
    TextView     mCollectCount;
    @Bind(R.id.sales)
    TextView     mSales;
    @Bind(R.id.earn)
    TextView     mEarn;
    @Bind(R.id.comment_all)
    TextView     mCommentAll;
    @Bind(R.id.comment_a)
    TextView     mCommentA;
    @Bind(R.id.comment_b)
    TextView     mCommentB;
    @Bind(R.id.comment_c)
    TextView     mCommentC;
    @Bind(R.id.comment_icon1)
    ImageView    mCommentIcon1;
    @Bind(R.id.comment_name1)
    TextView     mCommentName1;
    @Bind(R.id.comment_time1)
    TextView     mCommentTime1;
    @Bind(R.id.comment_content1)
    TextView     mCommentContent1;
    @Bind(R.id.comment_icon2)
    ImageView    mCommentIcon2;
    @Bind(R.id.comment_name2)
    TextView     mCommentName2;
    @Bind(R.id.comment_time2)
    TextView     mCommentTime2;
    @Bind(R.id.comment_content2)
    TextView     mCommentContent2;
    @Bind(R.id.see_all_comment)
    TextView     mSeeAllComment;
    @Bind(R.id.comment_container1)
    View         mCommentContainer1;
    @Bind(R.id.comment_container2)
    View         mCommentContainer2;
    @Bind(R.id.add2shop)
    TextView     mAdd2shop;
    @Bind(R.id.go_buy)
    TextView     mGoBuy;
    @Bind(R.id.detail)
    WebView      mDetail;
    @Bind(R.id.comm_pic_container1)
    LinearLayout mCommPicContainer1;
    @Bind(R.id.comm_pic_container2)
    LinearLayout mCommPicContainer2;
    private AutoScrollTask     mAutoScrollTask;
    private GoodInfoColorPopup mGoodInfoColorPopup;
    private ParameterPopup     mParameterPopup;
    private int                mId;
    private boolean mIsNotAttr = true;


    @Inject
    DaoSession mDaoSession;
    private String            mRecord_time;
    private List<CommentBean> mData;
    private CouponPopup       mCouponPopup;
    private String            mShopId;
    private ReviewPicPopup    mReviewPicPopup;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerGoodInfoComponent.builder()
                .appComponent(appComponent)
                .goodInfoModule(new GoodInfoModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mGoodInfoColorPopup = new GoodInfoColorPopup(this);
        mCouponPopup = new CouponPopup(this);
        mReviewPicPopup = new ReviewPicPopup(this);
    }

    @Override
    public void initData() {
        mId = getIntent().getIntExtra("id", -1);
        if (mId == -1)
            mId = Integer.parseInt(getIntent().getStringExtra("id"));
        Log.d("GoodInfoActivity", "mId:" + mId);
        mPresenter.loadDetail(mId);
        mPresenter.loadBanner(mId);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mGoodInfoColorPopup.setOnPopupMenuClickListener(this);
        mCouponPopup.setOnCouponItemClick(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_good_info;
    }

    @OnClick({R.id.select_color, R.id.parameter, R.id.see_all_comment, R.id.go_buy, R.id.add2shop, R.id.add_collect,
            R.id.comment_a, R.id.comment_b, R.id.comment_c, R.id.comment_all, R.id.share, R.id.service, R.id
            .collection_status, R.id.select_coupon, R.id.shop})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.shop:
                if (mShopId == null)
                    return;
                Intent intent = new Intent(this, ShopActivity.class);
                intent.putExtra("shop_id", mShopId);
                startActivity(intent);
                break;
            case R.id.select_coupon://优惠券
                mCouponPopup.showAtLocation(view, 0, 0, Gravity.CENTER);
                break;
            case R.id.service://客服
                mPresenter.openConversation();
                break;
            case R.id.share:
                mPresenter.share();
                break;
            case R.id.comment_all:
                refreshComment(0);
                break;
            case R.id.comment_a:
                refreshComment(1);
                break;
            case R.id.comment_b:
                refreshComment(2);
                break;
            case R.id.comment_c:
                refreshComment(3);
                break;
            case R.id.add_collect:
            case R.id.collection_status:
                mPresenter.addCollect(mId + "");
                break;
            case R.id.add2shop:
                add2Shop();
                break;
            case R.id.go_buy:
                toBuy();
                break;
            case R.id.see_all_comment:
                mPresenter.openAllCommentPage();
                break;
            case R.id.select_color:
                if (mGoodInfoColorPopup != null)
                    mGoodInfoColorPopup.show(view, Gravity.CENTER);
                break;
            case R.id.parameter:
                if (mParameterPopup != null)
                    mParameterPopup.showAtLocation(view, Gravity.CENTER, 0, 0);
                break;
        }
    }

    private void toBuy() {
        if (!mIsNotAttr && TextUtils.isEmpty(mGoodInfoColorPopup.getGoodsAttrs())) {
            Toast.makeText(this, "请选择商品属性", Toast.LENGTH_SHORT).show();
            mGoodInfoColorPopup.show(mPoints, Gravity.CENTER);
            return;
        }
        if (!mGoodInfoColorPopup.isSelectAllAttr() && !mIsNotAttr) {
            Toast.makeText(this, "请选择 " + mGoodInfoColorPopup.getUnSelectAttrName() + " 属性", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!mGoodInfoColorPopup.isShowde()) {
            //            Toast.makeText(this, "请选择商品数量", Toast.LENGTH_SHORT).show();
            mGoodInfoColorPopup.show(mPoints, Gravity.CENTER);
            return;
        }
        mPresenter.openConfOrderPage(mGoodInfoColorPopup.getGoodsAttrs(), mGoodInfoColorPopup.getGoodsCount());
    }

    @Override
    public void showBanner(List<BannerBean> data) {
        if (data.size() == 0)
            return;
        mAutoScrollTask = new AutoScrollTask(mViewPager, mPoints, mHandler, this);
        mAutoScrollTask.setData(data);
        mAutoScrollTask.start();
    }

    @Override
    public void initColorPopup(List<List<GoodsInfoAttrBean.SpecBean>> data) {
        mIsNotAttr = data.isEmpty();
        mGoodInfoColorPopup.setAttrData(data);
    }

    @Override
    public void initColorLogo(String logo) {
        mGoodInfoColorPopup.setLogo(logo);
    }

    @Override
    public void initParameterPopup(List<GoodsInfoAttrBean.AttributeBean> data) {
        mParameterPopup = new ParameterPopup(this, data);
    }

    @Override
    public void showDetail(GoodsInfoBean bean) {
        mShopId = bean.shop_id;
        mGoodName.setText(bean.goods_name);
        mGoodDesc.setText(CommonUtil.delHTMLTag(bean.goods_desc));
        String money = TextUtils.isEmpty(bean.promote_price) ? bean.shop_price : bean.promote_price;
        mPrice.setText("￥" + money);
        mGoodInfoColorPopup.setMoney(money);
        //加载详情
        String url = bean.goods_content;
        url = CommonUtil.decodeImgUrl(url);
        Log.d("GoodInfoActivity", url);
        mDetail.getSettings().setJavaScriptEnabled(true);
        //        mDetail.loadUrl(qr_code);
        mDetail.loadDataWithBaseURL(null, url, "text/html", "utf-8", null);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mDetail.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mDetail.setVisibility(View.GONE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mDetail.setVisibility(View.VISIBLE);
            }
        });
        //        Picasso.with(this).load(bean.goods_particulars).into(mDetailImg);
        mCollect.setChecked(bean.collect_type == 1);
        mCollectCount.setText("收藏 " + bean.collect_num);
        mSales.setText("销量" + bean.people + "笔");
        //加入浏览记录
        mRecord_time = System.currentTimeMillis() / 1000 + "";
        RecordBean recordBean = new RecordBean(null, bean.id, bean.shop_price, bean.collect_type,
                mRecord_time, bean.logo, bean.goods_name);
        mDaoSession.insert(recordBean);
    }

    @Override
    public void showComment(List<CommentBean> data) {
        mData = data;
        ArrayList<CommentBean> lista = new ArrayList<>();//好评
        ArrayList<CommentBean> listb = new ArrayList<>();//中评
        ArrayList<CommentBean> listc = new ArrayList<>();//差评
        for (CommentBean bean : data) {
            switch (Integer.parseInt(bean.star)) {
                case 0:
                case 1:
                    listc.add(bean);
                    break;
                case 2:
                    listb.add(bean);
                    break;
                case 3:
                case 4:
                case 5:
                    lista.add(bean);
                    break;
            }
        }
        mCommentAll.setText("全部评论(" + (data == null ? 0 : data.size()) + ")");
        mCommentA.setText("好评(" + lista.size() + ")");
        mCommentB.setText("中评(" + listb.size() + ")");
        mCommentC.setText("差评(" + listc.size() + ")");
        refreshComment(0);
    }

    @Override
    public void refreshNesPrice(String newMoney) {
        mGoodInfoColorPopup.setMoney(newMoney);
    }

    @Override
    public void initCoupon(List<GoodsInfoBean.CouponsBean> coupons) {
        mCouponPopup.setData(coupons);
    }

    private void refreshComment(int type) {
        if (mData == null)
            return;
        ArrayList<CommentBean> lista = new ArrayList<>();//好评
        ArrayList<CommentBean> listb = new ArrayList<>();//中评
        ArrayList<CommentBean> listc = new ArrayList<>();//差评
        for (CommentBean bean : mData) {
            switch (Integer.parseInt(bean.star)) {
                case 0:
                case 1:
                    listc.add(bean);
                    break;
                case 2:
                    listb.add(bean);
                    break;
                case 3:
                case 4:
                case 5:
                    listc.add(bean);
                    break;
            }
        }
        List<CommentBean> data = null;
        switch (type) {
            case 0:
                data = mData;
                break;
            case 1:
                data = lista;
                break;
            case 2:
                data = listb;
                break;
            case 3:
                data = listc;
                break;
        }
        if (data == null)
            return;
        if (data.size() > 0) {
            mCommentName1.setText(data.get(0).nickname);
            mCommentTime1.setText(data.get(0).addtime);
            mCommentContent1.setText(data.get(0).content);
            Picasso.with(this).load(data.get(0).head_pic)
                    .placeholder(R.mipmap.icon_man)
                    .error(R.mipmap.icon_man).into(mCommentIcon1);
            mCommentContainer1.setVisibility(View.VISIBLE);
            //显示评论图片
            showCommentPics(mCommPicContainer1, data.get(0).pic);
        } else {
            mCommentContainer1.setVisibility(View.GONE);
        }
        if (data.size() > 1) {
            mCommentName2.setText(data.get(1).nickname);
            mCommentTime2.setText(data.get(1).addtime);
            mCommentContent2.setText(data.get(1).content);
            Picasso.with(this).load(data.get(1).head_pic)
                    .error(R.mipmap.icon_man).into(mCommentIcon2);
            mCommentContainer2.setVisibility(View.VISIBLE);
            //显示评论图片
            showCommentPics(mCommPicContainer2, data.get(0).pic);
        } else {
            mCommentContainer2.setVisibility(View.GONE);
        }
    }

    View.OnClickListener mReviewPicListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = (String) v.getTag();
            if (url != null)
                mReviewPicPopup.review(url, v);
        }
    };

    private void showCommentPics(LinearLayout view, List<String> pic) {
        int dp40 = UIUtil.dp2px(this, 80);
        int dp5 = UIUtil.dp2px(this, 5);
        view.removeAllViews();
        if (pic != null) {
            for (String url : pic) {
                ImageView imageView = new ImageView(this);
                view.addView(imageView);
                imageView.setPadding(dp5, dp5, dp5, dp5);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(dp40, dp40));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setTag(url);
                imageView.setOnClickListener(mReviewPicListener);
                Picasso.with(this)
                        .load(url)
                        .error(R.mipmap.ic_launcher_trad_food)
                        .placeholder(R.mipmap.ic_launcher_trad_food)
                        .into(imageView);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAutoScrollTask != null && !mAutoScrollTask.isStart())
            mAutoScrollTask.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mAutoScrollTask != null)
            mAutoScrollTask.stop();
    }

    @Override
    public void onPopupItemClick(View view, int tag) {
        switch (tag) {
            case GoodInfoColorPopup.ADD_TO_SHOP:
                add2Shop();
                break;
            case GoodInfoColorPopup.TO_BUY:
                toBuy();
                break;
            case GoodInfoColorPopup.ATTRS:
                mPresenter.getNewPrice(mGoodInfoColorPopup.getGoodsAttrs());
                break;
        }
    }

    private void add2Shop() {
        if (!mIsNotAttr && TextUtils.isEmpty(mGoodInfoColorPopup.getGoodsAttrs())) {
            //            Toast.makeText(this, "请选择商品属性", Toast.LENGTH_SHORT).show();
            mGoodInfoColorPopup.showAtLocation(mPoints, Gravity.CENTER, 0, 0);
            return;
        }
        Log.d("lucas", "getGoodsAttrs:" + mGoodInfoColorPopup.getGoodsAttrs());
        if (!mGoodInfoColorPopup.isSelectAllAttr()) {
            Toast.makeText(this, "请选择" + mGoodInfoColorPopup.getUnSelectAttrName() + "属性", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.add2Shop(mId + "", mGoodInfoColorPopup.getGoodsAttrs(), mGoodInfoColorPopup.getGoodsCount());
    }

    //选择优惠券
    @Override
    public void onCouponItemClick(View view, int position) {
        mPresenter.selectCoupon(position);
    }
}
