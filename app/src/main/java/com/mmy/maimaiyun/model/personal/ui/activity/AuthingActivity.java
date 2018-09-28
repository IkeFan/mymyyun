package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.Bind;

/**
 * 审核中
 */
public class AuthingActivity extends BaseActivity {

    @Bind(R.id.title_center_text)
    TextView  mTitle;
    @Bind(R.id.name)
    TextView  mName;
    @Bind(R.id.certificates_code)
    TextView  mCertificatesCode;
    @Bind(R.id.address)
    TextView  mAddress;
    @Bind(R.id.image1)
    ImageView mImage1;
    @Bind(R.id.image2)
    ImageView mImage2;

    @Override
    protected void initDagger(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        mTitle.setText("审核中");
    }

    @Override
    public void initData() {
        Intent data = getIntent();
        String name = data.getStringExtra("name");
        String certificatesCode = data.getStringExtra("certificatesCode");
        String address = data.getStringExtra("address");
        String addressDetail = data.getStringExtra("addressDetail");
        String[] imgs = data.getStringArrayExtra("imgs");
        mName.setText(name);
        mCertificatesCode.setText(certificatesCode);
        mAddress.setText(address+"\n"+addressDetail);
        Picasso.with(this).load(new File(imgs[0])).into(mImage1);
        Picasso.with(this).load(new File(imgs[1])).into(mImage2);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_authing;
    }

}
