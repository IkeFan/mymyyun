package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 商店类别选择
 */
public class ShopClazzChoiceActivity extends BaseActivity {


    @Bind(R.id.title_center_text)
    TextView mTitle;

    @Override
    protected void initDagger(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        mTitle.setText("XXX店铺");
    }

    @Override
    public void initData() {

    }
    
    @OnClick({R.id.all})
    public void click(View view){
        switch (view.getId()) {
            case R.id.all:
                startActivity(new Intent(this,ShopClazzActivity.class));
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_shop_clazz_choice;
    }

}
