package com.mmy.maimaiyun;

import android.widget.TextView;

import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.data.bean.DDCardBean;
import com.mmy.maimaiyun.model.personal.component.DaggerDDCardComponent;
import com.mmy.maimaiyun.model.personal.module.DDCardModule;
import com.mmy.maimaiyun.model.personal.presenter.DDCardPresenter;
import com.mmy.maimaiyun.model.personal.view.DDCardView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;

/**
 * 嘟嘟卡
 */
public class DDCardActivity extends BaseActivity<DDCardPresenter> implements DDCardView {

    @Bind(R.id.title_center_text)
    TextView mTitleCenterText;
    @Bind(R.id.car)
    TextView mCar;
    @Bind(R.id.time_out)
    TextView mTimeOut;
    private SimpleDateFormat mFormat;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerDDCardComponent.builder()
                .appComponent(appComponent)
                .dDCardModule(new DDCardModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitleCenterText.setText("我的嘟嘟卡");
        mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public void initData() {
        mPresenter.loadData();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_dd_card;
    }

    @Override
    public void refreshView(DDCardBean data) {
        mCar.setText(data.number + "张");
        if (data.time != 0)
            mTimeOut.setText(mFormat.format(new Date(data.time * 1000)));
    }

}
