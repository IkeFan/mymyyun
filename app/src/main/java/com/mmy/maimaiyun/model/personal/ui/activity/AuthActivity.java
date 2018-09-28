package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.model.personal.adapter.PhotoUploadAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerAuthComponent;
import com.mmy.maimaiyun.model.personal.module.AuthModule;
import com.mmy.maimaiyun.model.personal.presenter.AuthPresenter;
import com.mmy.maimaiyun.model.personal.view.AuthView;
import com.mmy.maimaiyun.utils.CommonUtil;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.mmy.maimaiyun.model.personal.ui.activity.JoinApplyActivity.PIC_ID_CAR;

/**
 * 实名认证
 */
public class AuthActivity extends BaseActivity<AuthPresenter> implements AuthView {

    @Bind(R.id.title_center_text)
    TextView     mTitle;
    @Bind(R.id.name)
    EditText     mName;
    @Bind(R.id.certificates_code)
    EditText     mCertificatesCode;
    @Bind(R.id.id_card_accessories)
    TextView     mImg;
    @Bind(R.id.next)
    TextView     mNext;
    private String mIdCarPath_f;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerAuthComponent.builder()
                .appComponent(appComponent)
                .authModule(new AuthModule(this,this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("实名认证");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        super.initEvent();
    }

    @OnClick({R.id.next, R.id.id_card_accessories})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.next:
                next();
                break;
            case R.id.id_card_accessories:
                Intent intent = new Intent(this, PhotoUploadActivity.class);
                PhotoUploadActivity.PhotoAttrs attrs = new PhotoUploadActivity.PhotoAttrs();
                attrs.maxPhoto = 1;
                attrs.minPhoto = 1;
                intent.putExtra("attr", attrs);
                startActivityForResult(intent, PIC_ID_CAR);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null)
            switch (requestCode) {
                case PIC_ID_CAR: {
                    List<PhotoUploadAdapter.PhotoBean> list = (List<PhotoUploadAdapter.PhotoBean>) data
                            .getSerializableExtra("data");
                    if (list != null && list.size() > 0)
                        mImg.setText("已选择");
                    if (list.size() > 0)
                        mIdCarPath_f = list.get(0).path;
                }
                break;
            }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void next() {
        String name = mName.getText().toString().trim();
        String certificatesCode = mCertificatesCode.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入真实姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!RegexUtils.isIDCard15(certificatesCode) && !RegexUtils.isIDCard18(certificatesCode)) {
            Toast.makeText(this, "请输入身份证号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mIdCarPath_f)) {
            Toast.makeText(this, "请选择手持身份证照", Toast.LENGTH_SHORT).show();
            return;
        }
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)//表单类型
                .addFormDataPart("token", getUserBean().token);
        builder.addFormDataPart("member_id", getUserBean().member_id);
        builder.addFormDataPart("realname", name);
        RequestBody idCardFile_f = RequestBody.create(MediaType.parse("multipart/form-data"), new File(mIdCarPath_f));
        builder.addFormDataPart("idcart_pic", CommonUtil.getFileName(mIdCarPath_f), idCardFile_f);
        builder.addFormDataPart("idcard", certificatesCode);
        List<MultipartBody.Part> parts = builder.build().parts();
        mPresenter.submitAuth(parts);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_auth;
    }

}
