package com.mmy.maimaiyun.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mmy.maimaiyun.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @创建者 lucas
 * @创建时间 2017/8/7 0007 14:50
 * @描述 自带标题的activity
 */

public abstract class TitleActivity extends BaseActivity {
    @Bind(R.id.root)
    LinearLayout mRoot;
    @Bind(R.id.title_name)
    TextView mTitleName;
    @Bind(R.id.title_back)
    ImageView mTitleImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        ButterKnife.bind(this);
        mTitleName.setText(getTitleName());
        mRoot.addView(View.inflate(this, getTitleContextViewId(), null));
        initView();
        initData();
        initEvent();
    }

    public void isShowBack(boolean isShow){
        mTitleImg.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    abstract int getTitleContextViewId();

    abstract String getTitleName();
}
