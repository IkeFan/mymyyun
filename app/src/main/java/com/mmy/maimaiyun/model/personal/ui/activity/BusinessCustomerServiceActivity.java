package com.mmy.maimaiyun.model.personal.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.helper.VDataMakeHelper;
import com.mmy.maimaiyun.model.personal.adapter.BusinessCustomerServiceAdapter;
import com.mmy.maimaiyun.popup.BusinessCustomerServicePopup;
import com.mmy.maimaiyun.utils.UIUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 商家售后
 */
public class BusinessCustomerServiceActivity extends BaseActivity implements BusinessCustomerServicePopup.OnBCSPopupClickListener {

    @Bind(R.id.title_center_text)
    TextView     mTitle;
    @Bind(R.id.photos)
    RecyclerView mPhotos;
    @Bind(R.id.reason)
    TextView mReason;
    @Bind(R.id.no)
    TextView mNo;
    @Bind(R.id.reason_title)
    View mReasonTitle;
    @Bind(R.id.reason_content)
    View mReasonContent;
    private BusinessCustomerServiceAdapter mAdapter;
    private BusinessCustomerServicePopup   mBusinessCustomerServicePopup;

    @Override
    protected void initDagger(AppComponent appComponent) {
    }

    @Override
    public void initView() {
        mTitle.setText("售后");
        mPhotos.setLayoutManager(new GridLayoutManager(this, 3));
        int px = UIUtil.dp2px(this, 5);
        mPhotos.addItemDecoration(new SpacesItemDecoration(px, px, px, px));
        mAdapter = new BusinessCustomerServiceAdapter(this);
        mPhotos.setAdapter(mAdapter);
        mBusinessCustomerServicePopup = new BusinessCustomerServicePopup(this);
    }

    @Override
    public void initData() {
        mAdapter.setData(VDataMakeHelper.createStrList(5));
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mBusinessCustomerServicePopup.setOnBCSPopupClickListener(this);
    }

    @OnClick({R.id.no})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.no:
                mBusinessCustomerServicePopup.showAtLocation(view, Gravity.CENTER, 0, 0);
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_business_customer_service;
    }

    @Override
    public void onBCSClick(String reason) {
        mReason.setText(reason);
        mNo.setBackgroundResource(R.drawable.shape_orange_solid);
        mNo.setTextColor(getResources().getColor(R.color.white));
        mReasonContent.setVisibility(View.VISIBLE);
        mReasonTitle.setVisibility(View.VISIBLE);
    }
}
