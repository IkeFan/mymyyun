package com.mmy.maimaiyun.model.main.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.adapter.BasePushRefreshAdapter;
import com.mmy.maimaiyun.customview.InnerViewPager;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.BannerBean;
import com.mmy.maimaiyun.data.bean.HotGoodsBean;
import com.mmy.maimaiyun.data.bean.HotGoodsRecommemdBean;
import com.mmy.maimaiyun.helper.AutoScrollTask;
import com.mmy.maimaiyun.helper.PushRefreshScrollHelper;
import com.mmy.maimaiyun.utils.UIUtil;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/2 0002 10:59
 * @描述
 */

public class HotGoodsAdapter extends BasePushRefreshAdapter<HotGoodsBean.GoodsBean> {
    private static final int BANNER    = 0;
    private static final int RECOMMEND = 1;
    private static final int NORMAL    = 2;

    protected Handler        mHandler;
    public    InnerViewPager mViewPager;
    public    LinearLayout   mPoints;

    private       RecyclerView                mRecommendList;
    private       Context                     mContext;
    private       RecommendAdapter            mRecommendAdapter;
    private       AutoScrollTask              mAutoScrollTask;
    private final BaseActivity                mActivity;
    private       OnHotGoodsItemClickListener mListener;
    private       List<BannerBean>            mBanners;
    private       List<HotGoodsRecommemdBean> mRecommendData;

    public HotGoodsAdapter(Context context, Handler handler, PushRefreshScrollHelper helper) {
        super(context, helper);
        mHandler = handler;
        mContext = context;
        mActivity = (BaseActivity) context;
    }


    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case BANNER:
                break;
            case RECOMMEND:
                break;
            case NORMAL:
                LinearLayout root = (LinearLayout) holder.getRootView();
                View child1 = root.getChildAt(0);
                View child2 = root.getChildAt(1);
                int ceil = (mData.size() - 2) / 2;
                HotGoodsBean.GoodsBean bean1 = null;
                HotGoodsBean.GoodsBean bean2 = null;
                //数量为单数时最后一行只显示一个
                int index = position - 2;
                bean1 = mData.get(index * 2);
                if (ceil == position - 2 && (mData.size() % 2 != 0)) {
                    child2.setVisibility(View.INVISIBLE);
                    child2.setEnabled(false);
                } else {
                    bean2 = mData.get(index * 2 + 1);
                    child2.setVisibility(View.VISIBLE);
                    child2.setEnabled(true);
                }
                TextView money2 = (TextView) holder.getRootView().findViewById(R.id.hot_goods_money2);
                money2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//添加中划线
                child1.setOnClickListener(v -> {
                    if (mListener != null) {
                        int adapterPosition = holder.getAdapterPosition() - 2;
                        mListener.onHotItemClick(v, adapterPosition * 2);
                    }
                });
                child2.setOnClickListener(v -> {
                    if (mListener != null) {
                        int adapterPosition = holder.getAdapterPosition() - 2;
                        mListener.onHotItemClick(v, adapterPosition * 2 + 1);
                    }
                });
            {
                ImageView icon = (ImageView) child1.findViewById(R.id.hot_goods_icon);
                TextView name = (TextView) child1.findViewById(R.id.hot_goods_title);
                TextView payCount = (TextView) child1.findViewById(R.id.pay_count);
                TextView hotGoodsMoney = (TextView) child1.findViewById(R.id.hot_goods_money);
                TextView hotGoodsMoney2 = (TextView) child1.findViewById(R.id.hot_goods_money2);
                Picasso.with(mContext).load(bean1.logo).into(icon);
                name.setText(bean1.goods_name);
                hotGoodsMoney.setText("￥" + bean1.shop_price);
            }

            if (bean2 != null) {
                ImageView icon = (ImageView) child2.findViewById(R.id.hot_goods_icon);
                TextView name = (TextView) child2.findViewById(R.id.hot_goods_title);
                TextView payCount = (TextView) child2.findViewById(R.id.pay_count);
                TextView hotGoodsMoney = (TextView) child2.findViewById(R.id.hot_goods_money);
                TextView hotGoodsMoney2 = (TextView) child2.findViewById(R.id.hot_goods_money2);
                Picasso.with(mContext).load(bean2.logo).into(icon);
                name.setText(bean2.goods_name);
                hotGoodsMoney.setText("￥" + bean2.shop_price);
            }
            break;
        }
    }

    @Override
    protected BaseRecyclerViewHolder onCreateViewHolder2(ViewGroup parent, int viewType) {
        View view = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        int itemCount = getItemCount();
        switch (viewType) {
            case BANNER:
                view = layoutInflater.inflate(R.layout.item_banner, parent, false);
                ViewGroup viewGroup = (ViewGroup) view;
                viewGroup.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtil
                        .dp2px(mContext, 150)));
                mViewPager = (InnerViewPager) viewGroup.findViewById(R.id.banners);
                mPoints = (LinearLayout) viewGroup.findViewById(R.id.points);
                mAutoScrollTask = new AutoScrollTask(mViewPager, mPoints, mHandler, mActivity);
                mAutoScrollTask.setData(mBanners);
                mAutoScrollTask.start();
                break;
            case RECOMMEND:
                view = layoutInflater.inflate(R.layout.item_hot_goods_list, parent, false);
                mRecommendList = (RecyclerView) view.findViewById(R.id.recommend_list);
                LinearLayoutManager layout = new LinearLayoutManager(mContext);
                layout.setOrientation(LinearLayoutManager.HORIZONTAL);
                mRecommendList.setLayoutManager(layout);
                mRecommendList.addItemDecoration(new SpacesItemDecoration(UIUtil.dp2px(mContext, 10)));
                mRecommendAdapter = new RecommendAdapter(mContext);
                mRecommendList.setAdapter(mRecommendAdapter);
                if (mRecommendData != null)
                    mRecommendAdapter.setData(mRecommendData);
                break;
            case NORMAL:
                view = layoutInflater.inflate(R.layout.item_hot_goods2, parent, false);
                break;
        }

        return new BaseRecyclerViewHolder(view);
    }

    @Override
    protected int getItemCount2() {
        return mData == null ? 2 : (int) (Math.ceil(mData.size() / 2f)) + 2;
    }

    @Override
    public int getItemViewType2(int position) {
        if (position == 0)
            return BANNER;
        if (position == 1)
            return RECOMMEND;
        return NORMAL;
    }

    public interface OnHotGoodsItemClickListener {
        void onHotItemClick(View view, int position);
    }

    public void setOnHotGoodsItemClickListener(OnHotGoodsItemClickListener listener) {
        mListener = listener;
    }

    public void refreshBanner(List<BannerBean> banners) {
        mBanners = banners;
    }

    public void refreshRecommend(List<HotGoodsRecommemdBean> recommend) {
        mRecommendData = recommend;
    }

    public void setRecommendItemClickListener(OnItemClickListener listener) {
        if (mRecommendAdapter != null)
            mRecommendAdapter.addOnItemCliclListener(listener);
    }

}
