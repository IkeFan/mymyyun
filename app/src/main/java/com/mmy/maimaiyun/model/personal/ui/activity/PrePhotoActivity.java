package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 预览图片
 */
public class PrePhotoActivity extends BaseActivity {

    @Bind(R.id.name)
    EditText  mName;
    @Bind(R.id.img)
    ImageView mImg;
    @Bind(R.id.title_center_text)
    TextView  mTitle;

    @Override
    protected void initDagger(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        mTitle.setText("预览");
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String img = intent.getStringExtra("img");
        mName.setText(mName != null ? name : "");
        mImg.setImageBitmap(BitmapFactory.decodeFile(img));
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.root_view, R.id.img})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.root_view:
            case R.id.img:
                Intent data = getIntent();
                data.putExtra("name", mName.getText().toString().trim());
                setResult(0x11, data);
                finish();
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_pre_photo;
    }
}
