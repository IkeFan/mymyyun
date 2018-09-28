package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 个人资料编辑
 */
public class EditActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.title_center_text)
    TextView    mTitleCenterText;
    @Bind(R.id.title_right_text)
    TextView    mTitleRight;
    @Bind(R.id.nick_name)
    EditText    mNickName;
    @Bind(R.id.man)
    RadioButton mMan;
    @Bind(R.id.wuman)
    RadioButton mWuman;
    @Bind(R.id.sex)
    RadioGroup  mSex;

    public static final int EDIT_NICK_NAME = 0x100;
    public static final int EDIT_SEX       = 0x101;
    public static final int EDIT_QQ        = 0x102;
    public static final int EDIT_EMAIL     = 0x103;
    private int mAction;
    private String mSexValue = "1";

    @Override
    protected void initDagger(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        mTitleRight.setText("完成");
        mAction = getIntent().getIntExtra("action", -1);
        switch (mAction) {
            case EDIT_NICK_NAME:
                mTitleCenterText.setText("修改昵称");
                mNickName.setVisibility(View.VISIBLE);
                break;
            case EDIT_SEX:
                mTitleCenterText.setText("修改性别");
                mSex.setVisibility(View.VISIBLE);
                break;
            case EDIT_QQ:
                mTitleCenterText.setText("修改QQ");
                mNickName.setVisibility(View.VISIBLE);
                mNickName.setHint("请输入QQ");
                break;
            case EDIT_EMAIL:
                mTitleCenterText.setText("修改邮箱");
                mNickName.setVisibility(View.VISIBLE);
                mNickName.setHint("请输入邮箱");
                break;
        }
        int sex = getIntent().getIntExtra("sex", 0);
        if (sex == 1)
            mMan.setChecked(true);
        if (sex == 2)
            mWuman.setChecked(true);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.title_right_text})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.title_right_text:
                Intent intent = new Intent();
                        String trim = mNickName.getText().toString().trim();
                switch (mAction) {
                    case EDIT_NICK_NAME:
                        if (TextUtils.isEmpty(trim)) {
                            Toast.makeText(this, "请输入昵称", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        intent.putExtra("nick_name", trim);
                        setResult(EDIT_NICK_NAME, intent);
                        break;
                    case EDIT_SEX:
                        intent.putExtra("sex", mSexValue);
                        setResult(EDIT_SEX, intent);
                        break;
                    case EDIT_QQ:
                        if (TextUtils.isEmpty(trim)) {
                            Toast.makeText(this, "请输入QQ", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        intent.putExtra("qq", trim);
                        setResult(EDIT_QQ, intent);
                        break;
                    case EDIT_EMAIL:
                        if (TextUtils.isEmpty(trim)) {
                            Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        intent.putExtra("email", trim);
                        setResult(EDIT_EMAIL, intent);
                        break;
                }

                break;
        }
        finish();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mSex.setOnCheckedChangeListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_edit;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        mSexValue = checkedId == R.id.man ? "1" : "2";
    }
}
