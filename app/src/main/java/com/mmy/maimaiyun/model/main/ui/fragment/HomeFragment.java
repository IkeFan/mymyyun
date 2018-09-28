package com.mmy.maimaiyun.model.main.ui.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.mmy.maimaiyun.App;
import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.adapter.BaseQuickAdapter;
import com.mmy.maimaiyun.adapter.BaseViewHolder;
import com.mmy.maimaiyun.base.able.OnPopupMenuItemClickListener;
import com.mmy.maimaiyun.base.activity.BaseFragment;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.customview.InnerViewPager;
import com.mmy.maimaiyun.customview.MessageHintView;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.BannerBean;
import com.mmy.maimaiyun.data.bean.BestBean;
import com.mmy.maimaiyun.data.bean.EventBean;
import com.mmy.maimaiyun.data.bean.GoodNewsItemBean;
import com.mmy.maimaiyun.data.bean.HomeEveryBean;
import com.mmy.maimaiyun.data.bean.HomeOnlyNewBean;
import com.mmy.maimaiyun.data.bean.RecommendBean;
import com.mmy.maimaiyun.helper.AutoScrollTask;
import com.mmy.maimaiyun.helper.MessageManager;
import com.mmy.maimaiyun.model.city.CitySearchActivity;
import com.mmy.maimaiyun.model.main.adapter.BoutiqueAdapter;
import com.mmy.maimaiyun.model.main.adapter.EveryAdapter;
import com.mmy.maimaiyun.model.main.adapter.HomeRecommendAdapter;
import com.mmy.maimaiyun.model.main.adapter.OnlyNewHomeAdapter;
import com.mmy.maimaiyun.model.main.component.DaggerHomeComponent;
import com.mmy.maimaiyun.model.main.module.HomeModule;
import com.mmy.maimaiyun.model.main.presenter.HomePresenter;
import com.mmy.maimaiyun.model.main.ui.activity.GoodNewsActivity;
import com.mmy.maimaiyun.model.main.ui.activity.GoodsActivity;
import com.mmy.maimaiyun.model.main.ui.activity.HotGoodsActivity;
import com.mmy.maimaiyun.model.main.ui.activity.NewGoodsActivity;
import com.mmy.maimaiyun.model.main.ui.activity.OneOneActivity;
import com.mmy.maimaiyun.model.main.ui.activity.OnlyNewActivity;
import com.mmy.maimaiyun.model.main.ui.activity.OverseasShoppingActivity;
import com.mmy.maimaiyun.model.main.ui.activity.SearchActivity;
import com.mmy.maimaiyun.model.main.ui.activity.ShopActivity;
import com.mmy.maimaiyun.model.main.ui.activity.SpecialOfferActivity;
import com.mmy.maimaiyun.model.main.ui.activity.TimeLimitActivity;
import com.mmy.maimaiyun.model.main.view.HomeView;
import com.mmy.maimaiyun.model.msg.ui.activity.MsgActivity;
import com.mmy.maimaiyun.popup.HomeMenuPopup;
import com.mmy.maimaiyun.utils.UIUtil;
import com.example.qr_readerexample.DecoderActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imlib.model.Message;

import static com.mmy.maimaiyun.R.id.city;

/**
 * 主页
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView, OnPopupMenuItemClickListener,
        MessageManager.OnReceiverMessageListener, App
                .OnAppLocationListenner, BaseQuickAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    @Inject
    protected Handler        mHandler;
    //    @Bind(R.id.banners)
    public    InnerViewPager mViewPager;
    //    @Bind(R.id.points)
    LinearLayout mPoints;
    //    @Bind(R.id.boutique_list)
    RecyclerView mBoutiqueList;
    //    @Bind(R.id.right_hint)
    LinearLayout mRightHint;
    @Bind(city)
    TextView        mCity;
    @Bind(R.id.title_root)
    View            mTitleRoot;
    @Bind(R.id.msg_hint)
    MessageHintView mHintView;
    //    @Bind(R.id.news)
    ViewFlipper mNews;
    @Bind(R.id.list)
    RecyclerView       mRecommend;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefreshLayout;
    RecyclerView mEvertList;
    RecyclerView mOnlyNewList;

    public static final int BOUTIQUE  = 0;
    public static final int RECOMMEND = 1;

    private AutoScrollTask       mAutoScrollTask;
    private BoutiqueAdapter      mBoutiqueAdapter;
    private HomeMenuPopup        mHomeMenuPopup;
    private HomeRecommendAdapter mHomeRecommendAdapter;
    private View                 mHeaderView;
    private EveryAdapter         mEveryAdapter;
    private OnlyNewHomeAdapter   mOnlyNewHomeAdapter;

    @Override
    protected void initView() {
        //创建头部分
        mHeaderView = LayoutInflater.from(mActivity).inflate(R.layout.item_main_header, null, false);
        mEvertList = (RecyclerView) mHeaderView.findViewById(R.id.list_every);
        mOnlyNewList = (RecyclerView) mHeaderView.findViewById(R.id.list_only_new);
        mViewPager = (InnerViewPager) mHeaderView.findViewById(R.id.banners);
        mPoints = (LinearLayout) mHeaderView.findViewById(R.id.points);
        mBoutiqueList = (RecyclerView) mHeaderView.findViewById(R.id.boutique_list);
        mRightHint = (LinearLayout) mHeaderView.findViewById(R.id.right_hint);
        mNews = (ViewFlipper) mHeaderView.findViewById(R.id.news);
        EventBus.getDefault().register(this);
        mRecommend.setLayoutManager(new GridLayoutManager(mActivity, 2));
        int px = UIUtil.dp2px(mActivity, 3);
        mRecommend.addItemDecoration(new SpacesItemDecoration(px));
        mHomeRecommendAdapter = new HomeRecommendAdapter(R.layout.item_home_recommend, mActivity, new
                ArrayList<RecommendBean>());
        mHomeRecommendAdapter.addHeaderView(mHeaderView);
        mRecommend.setAdapter(mHomeRecommendAdapter);

        //订阅消息
        mApp.getMessageManager().register(this);
        mBoutiqueAdapter = new BoutiqueAdapter(mActivity);
        int px15 = UIUtil.dp2px(mActivity, 10);
        mBoutiqueList.setAdapter(mBoutiqueAdapter);
        mBoutiqueList.addItemDecoration(new SpacesItemDecoration(0, 0, px15, px15));
        mBoutiqueList.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        if (mHomeMenuPopup == null) {
            mHomeMenuPopup = new HomeMenuPopup(mActivity);
        }
        mNews.setInAnimation(mActivity, R.anim.anim_in);
        mNews.setOutAnimation(mActivity, R.anim.anim_out);
        //禁止嵌套滑动,处理滑动没有惯性的问题
        //        mRecommend.setNestedScrollingEnabled(false);
        mEvertList.addItemDecoration(new SpacesItemDecoration(0, 0, px15, px15));
        mEvertList.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        mEveryAdapter = new EveryAdapter(R.layout.item_home_every, new ArrayList<>());
        mEvertList.setAdapter(mEveryAdapter);
        int px5 = UIUtil.dp2px(mActivity, 5);
        mOnlyNewList.setLayoutManager(new LinearLayoutManager(mActivity));
        mOnlyNewList.addItemDecoration(new SpacesItemDecoration(px5, 0, 0, 0));
        mOnlyNewHomeAdapter = new OnlyNewHomeAdapter(R.layout.item_only_new, new ArrayList<>());
        mOnlyNewList.setAdapter(mOnlyNewHomeAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.loadBanner();
        mPresenter.loadNews();
        mPresenter.loadBest();
        mPresenter.loadRecommend(false);
        mPresenter.loadHomeEveryData();
        mPresenter.loadHomeOnlyNewData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mEveryAdapter.setOnItemClickListener(this);
        mOnlyNewHomeAdapter.setOnItemClickListener(this);
        mBoutiqueList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //判断最后一个item是否显示
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int position = manager.findLastCompletelyVisibleItemPosition();
                if (position == (manager.getItemCount() - 1)) {
                    mRightHint.setVisibility(View.VISIBLE);
                } else {
                    mRightHint.setVisibility(View.GONE);
                }
            }
        });
        //定位
        App.getAppComponent().getApp().registerLocationListener(this);
        mHomeMenuPopup.setOnItemClickListener(this);
        mHomeRecommendAdapter.setOnItemClickListener(this);
        mBoutiqueAdapter.setOnItemClickListener(mBoutiqueAdapterListener);

        mHeaderView.findViewById(R.id.show_all).setOnClickListener(this::onClick);
        mHeaderView.findViewById(R.id.overseas_shopping).setOnClickListener(this::onClick);
        mHeaderView.findViewById(R.id.hot_goods).setOnClickListener(this::onClick);
        mHeaderView.findViewById(R.id.more).setOnClickListener(this::onClick);
        mHeaderView.findViewById(R.id.volume).setOnClickListener(this::onClick);
        mHeaderView.findViewById(R.id.special_offer).setOnClickListener(this::onClick);
        mHeaderView.findViewById(R.id.time_limit).setOnClickListener(this::onClick);
        mHeaderView.findViewById(R.id.one_one).setOnClickListener(this::onClick);
        mHeaderView.findViewById(R.id.dudu).setOnClickListener(this::onClick);
        mHeaderView.findViewById(R.id.news).setOnClickListener(this::onClick);
        mHeaderView.findViewById(R.id.more_type).setOnClickListener(this::onClick);
        mHeaderView.findViewById(R.id.hot_type).setOnClickListener(this::onClick);
        mHeaderView.findViewById(R.id.every_day).setOnClickListener(this::onClick);
        mHeaderView.findViewById(R.id.new_goods).setOnClickListener(this::onClick);
        mRefreshLayout.setOnRefreshListener(this);
        mHomeRecommendAdapter.setOnLoadMoreListener(this);
    }

    //精选商品
    BaseRecyclerViewAdapter.OnItemClickListener mBoutiqueAdapterListener = new BaseRecyclerViewAdapter
            .OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            mPresenter.openGoodInfoPage(position, BOUTIQUE, null);
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventBean event) {
        if (event.getAction() == CitySearchActivity.SELECT_CITY)
            mCity.setText(event.getMeg());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.getAppComponent().getApp().unregisterLocationListener(this);
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.msg_hint, R.id.search, R.id.city_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.more_type:
                break;
            case R.id.hot_type://品牌热卖
                Intent intent = new Intent(mActivity, SpecialOfferActivity.class);
                intent.putExtra("activity_type", "品牌热卖");
                intent.putExtra("search_type", SearchActivity.HOT_TYPE);
                mActivity.startActivity(intent);
                break;
            //            case R.id.time_limit:
            case R.id.every_day://每日优选
                Intent intent1 = new Intent(mActivity, SpecialOfferActivity.class);
                intent1.putExtra("activity_type", "每日优选");
                intent1.putExtra("search_type", SearchActivity.EVERY_DAY);
                mActivity.startActivity(intent1);
                break;
            case R.id.new_goods://新品推出
                mActivity.startActivity(new Intent(mActivity, NewGoodsActivity.class));
                break;
            case R.id.news://新闻详情
                mPresenter.openNewsInfo(mNews.getDisplayedChild());
                break;
            case R.id.dudu://嘟嘟卡
                Intent intent111 = new Intent(mActivity, ShopActivity.class);
                intent111.putExtra("shop_id", "3");
                startActivity(intent111);
//                openActivity(ClassInfoActivity.class, "cat_id=" + 375 + ",name=嘟嘟精品,isShow=1");
                break;
            case R.id.one_one://一品一县
                startActivity(new Intent(mActivity, OneOneActivity.class));
                break;
            case R.id.city_search://城市列表
                startActivity(new Intent(mActivity, CitySearchActivity.class));
                break;
            case R.id.search:
                openActivity(SearchActivity.class);
                break;
            case R.id.time_limit://限时特惠
                startActivity(new Intent(mActivity, TimeLimitActivity.class));
                break;
            case R.id.special_offer://天天特价
                Intent intent2 = new Intent(mActivity, SpecialOfferActivity.class);
                intent2.putExtra("activity_type", "天天特价");
                intent2.putExtra("search_type", SearchActivity.SPECIAL);
                mActivity.startActivity(intent2);
                break;
            case R.id.msg_hint:
                showMenuPopup(view);
                break;
            case R.id.volume://量贩优选
                Intent intent3 = new Intent(mActivity, SpecialOfferActivity.class);
                intent3.putExtra("activity_type", "量贩优选");
                intent3.putExtra("search_type", SearchActivity.SPECIAL);
                mActivity.startActivity(intent3);
//                startActivity(new Intent(mActivity, VolumeActivity.class));
                break;
            case R.id.show_all://精选
                startActivity(new Intent(mActivity, GoodsActivity.class));
                break;
            case R.id.overseas_shopping://海外购
                startActivity(new Intent(mActivity, OverseasShoppingActivity.class));
                break;
            case R.id.hot_goods://热卖商品
                startActivity(new Intent(mActivity, HotGoodsActivity.class));
                break;
            case R.id.more://商城头条
                startActivity(new Intent(mActivity, GoodNewsActivity.class));
                break;
        }
    }

    private void showMenuPopup(View view) {
        if (mHomeMenuPopup.isShowing()) {
            mHomeMenuPopup.dismiss();
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mHomeMenuPopup.showAsDropDown(mTitleRoot, 0, 10, Gravity.RIGHT);
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerHomeComponent.builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build().inject(this);
    }

    @Override
    public void showBanner(List<BannerBean> data) {
        mAutoScrollTask = new AutoScrollTask(mViewPager, mPoints, mHandler, mActivity);
        mAutoScrollTask.setData(data);
        mAutoScrollTask.start();
    }

    @Override
    public void showNews(List<GoodNewsItemBean> data) {
        for (GoodNewsItemBean bean : data) {
            TextView view = new TextView(mActivity);
            view.setText(bean.title);
            view.setSingleLine();
            view.setEllipsize(TextUtils.TruncateAt.END);
            view.setGravity(Gravity.CENTER_VERTICAL);
            mNews.addView(view);
        }
        if (!mNews.isFlipping())
            mNews.startFlipping();
    }

    @Override
    public void refreshBest(List<BestBean> data) {
        mBoutiqueAdapter.setData(data);
    }

    @Override
    public void refreshRecommend(List<RecommendBean> data, boolean b) {
        if (mRefreshLayout.isRefreshing())
            mRefreshLayout.setRefreshing(false);
        if (b) {
            if (data == null || data.size() == 0) {
                Toast.makeText(mActivity, "没有更多数据~", Toast.LENGTH_SHORT).show();
                mHomeRecommendAdapter.loadMoreEnd();
            } else {
                mHomeRecommendAdapter.addData(data);
                mHomeRecommendAdapter.loadMoreComplete();
            }
        } else {
            mHomeRecommendAdapter.setNewData(data);
        }
    }

    @Override
    public void refreshHomeEveryView(List<HomeEveryBean.TodayBean> data) {
        mEveryAdapter.setNewData(data);
    }

    @Override
    public void refreshHomeOnlyNewView(List<HomeOnlyNewBean.GoodsBean> data) {
        mOnlyNewHomeAdapter.setNewData(data);
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
        if (tag == HomeMenuPopup.MSG) {
            startActivity(new Intent(mActivity, MsgActivity.class));
        } else {
            Intent intent = new Intent(mActivity, DecoderActivity.class);
            startActivityForResult(intent, 0);
        }
        mHomeMenuPopup.dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mApp.getMessageManager().unregister(this);
    }

    //接收到消息
    @Override
    public void onReceiverMessage(Message message) {
        mHintView.show();
    }

    //    @Override
    //    public void onItemClick(View view, int position) {
    //        //进入商品详情
    //        mPresenter.openGoodInfoPage(position, RECOMMEND);
    //    }

    @Override
    public void onLocationSuccess(String city) {
        mCity.setText(city);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, BaseViewHolder baseViewHolder, int position) {
        if (adapter == mEveryAdapter) {
            Intent intent = new Intent(mActivity, TimeLimitActivity.class);
            intent.putExtra("type", 1);
            intent.putExtra("title", "限时特惠");
            mActivity.startActivity(intent);
            return;
        }
        if (adapter == mOnlyNewHomeAdapter) {
            mActivity.startActivity(new Intent(mActivity, OnlyNewActivity.class));
            return;
        }
        //进入商品详情
        mPresenter.openGoodInfoPage(position, RECOMMEND, (RecommendBean) adapter.getData().get(position));
    }

    @Override
    public void onRefresh() {
        mPresenter.loadRecommend(false);
    }

    //加载更多
    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadRecommend(true);
    }
}
