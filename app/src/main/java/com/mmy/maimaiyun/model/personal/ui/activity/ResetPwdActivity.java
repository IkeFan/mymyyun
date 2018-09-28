package com.mmy.maimaiyun.model.personal.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.model.personal.component.DaggerResetPwdComponent;
import com.mmy.maimaiyun.model.personal.module.ResetPwdModule;
import com.mmy.maimaiyun.model.personal.presenter.ResetPwdPresenter;
import com.mmy.maimaiyun.model.personal.view.ResetPwdView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @创建者 lucas
 * @创建时间 2017-11-08 02-14-06
 * @描述 修改密码界面
 */
public class ResetPwdActivity extends BaseActivity<ResetPwdPresenter> implements ResetPwdView {

    @Bind(R.id.title_center_text)
    TextView mTitleCenterText;
    @Bind(R.id.username)
    EditText mUsername;
    @Bind(R.id.pwd)
    EditText mPwd;
    @Bind(R.id.new_pwd)
    EditText mNewPwd;
    @Bind(R.id.new_pwd2)
    EditText mNewPwd2;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerResetPwdComponent.builder()
                .appComponent(appComponent)
                .resetPwdModule(new ResetPwdModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitleCenterText.setText("修改密码");
    }

    @Override
    public void initData() {
    }

    @OnClick(R.id.submit)
    public void click(View view) {
        switch (view.getId()) {
            case R.id.submit:
                submit();
                break;
        }
    }

    private void submit() {
        String username = mUsername.getText().toString().trim();
        String pwd = mPwd.getText().toString().trim();
        String newpwd = mNewPwd.getText().toString().trim();
        String newpwd2 = mNewPwd2.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入原密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(newpwd)) {
            Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(newpwd2)) {
            Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!newpwd.equals(newpwd2)){
            Toast.makeText(this, "两次新密码输入不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.resetPwd(username, pwd, newpwd);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_reset_pwd;
    }

}
