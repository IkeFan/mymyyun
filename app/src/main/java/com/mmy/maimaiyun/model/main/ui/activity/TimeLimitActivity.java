package com.mmy.maimaiyun.model.main.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.adapter.BaseQuickAdapter;
import com.mmy.maimaiyun.adapter.BaseViewHolder;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.TimeLimitBean;
import com.mmy.maimaiyun.model.main.adapter.TimeLimitAdapter;
import com.mmy.maimaiyun.model.main.component.DaggerTimeLimitComponent;
import com.mmy.maimaiyun.model.main.module.TimeLimitModule;
import com.mmy.maimaiyun.model.main.presenter.TimeLimitPresenter;
import com.mmy.maimaiyun.model.main.view.TimeLimitView;
import com.mmy.maimaiyun.utils.TimeUtil;
import com.mmy.maimaiyun.utils.UIUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;

/**
 * 限时特惠
 */
public class TimeLimitActivity extends BaseActivity<TimeLimitPresenter> implements TimeLimitView, TabLayout
        .OnTabSelectedListener, BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.title_center_text)
    TextView     mTitleCenterText;
    @Bind(R.id.tabs)
    TabLayout    mTabs;
    @Bind(R.id.hint_text)
    TextView     mHintText;
    @Bind(R.id.hint_time)
    TextView     mHintTime;
    @Bind(R.id.list)
    RecyclerView mList;
    @Bind(R.id.toolbar)
    View         mTitleRoot;
    private TimeLimitAdapter mAdapter;
    private int              mType;
    private int              tabPosition;
    private TimeTask         mTimeTask;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerTimeLimitComponent.builder()
                .appComponent(appComponent)
                .timeLimitModule(new TimeLimitModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitleCenterText.setText("限时特卖");
        mType = getIntent().getIntExtra("type", 1);
        String title = getIntent().getStringExtra("title");
        mTitleCenterText.setText(title);
        mTabs.addTab(mTabs.newTab().setText("今日疯抢"));
        mTabs.addTab(mTabs.newTab().setText("明日预告"));
        mTitleRoot.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mTitleCenterText.setTextColor(0xffffffff);
        mList.setLayoutManager(new LinearLayoutManager(this));
        int px = UIUtil.dp2px(this, 7);
        mList.addItemDecoration(new SpacesItemDecoration(px, 0, 0, 0));
        mAdapter = new TimeLimitAdapter(R.layout.item_time_limit, new ArrayList<>());
        mList.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mPresenter.loadListData(mType);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mTabs.setOnTabSelectedListener(this);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_time_limit;
    }

    @Override
    public void refresTodayhList(List<TimeLimitBean.GoodInfo> today) {
        mAdapter.setNewData(today);
    }

    @Override
    public void refresTomorrowhList(List<TimeLimitBean.GoodInfo> tomorrow) {
        mAdapter.setNewData(tomorrow);
    }

    @Override
    public void refreshTime(long data) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        //获取结束时间
        long endTime = data*1000;
        if (new Date().getTime() > endTime) {
            mHintTime.setText("活动已结束");
            return;
        }
        calendar.setTime(new Date(endTime));
        //        calendar.set(Calendar.HOUR_OF_DAY, data.hour);
        //        calendar.set(Calendar.MINUTE, data.minute);
        //        calendar.set(Calendar.SECOND, 0);
        String s = format.format(calendar.getTime());
        mHintTime.setText(s);
        if (mTimeTask == null) {
            mTimeTask = new TimeTask();
        }
        mTimeTask.start(endTime);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        tabPosition = tab.getPosition();
//        mHintTime.setVisibility(tabPosition == 0 ? View.VISIBLE : View.GONE);
        mAdapter.setTextStr(tabPosition == 0 ?"立即抢购":"明日预抢");
        mPresenter.selectTab(tabPosition);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, BaseViewHolder baseViewHolder, int position) {
        mPresenter.openGoodInfo(position, tabPosition);
    }

    public class TimeTask implements Runnable {


        private long mEndTime;

        @Override
        public void run() {
            if (new Date().getTime() > mEndTime) {
                mHintTime.setText("活动已结束");
                mHandler.removeCallbacks(this);
                return;
            }
            mHintTime.setText(TimeUtil.getTimeDiff(mEndTime));
            mHandler.postDelayed(this, 1000);
        }

        public void start(long endTime) {
            mEndTime = endTime;
            mHandler.post(this);
        }

        public void stop() {
            mHandler.removeCallbacks(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimeTask != null) {
            mTimeTask.stop();
        }
    }
}
