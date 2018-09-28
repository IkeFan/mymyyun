package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.model.personal.adapter.PhotoUploadAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerJoinApplyComponent;
import com.mmy.maimaiyun.model.personal.module.JoinApplyModule;
import com.mmy.maimaiyun.model.personal.presenter.JoinApplyPresenter;
import com.mmy.maimaiyun.model.personal.view.JoinApplyView;
import com.mmy.maimaiyun.utils.CommonUtil;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * @创建者 lucas
 * @创建时间 2017/8/28 0028 10:45
 * @描述 合伙人
 */

public class JoinApplyActivity extends BaseActivity<JoinApplyPresenter> implements  JoinApplyView {

    @Bind(R.id.title_center_text)
    TextView     mTitle;
    @Bind(R.id.id_card_accessories)
    TextView     mIdCardAccessories;
    @Bind(R.id.contact_information)
    EditText     mContactInformation;
    @Bind(R.id.rel_name)
    EditText     mName;
    @Bind(R.id.user_agreement)
    CheckBox     mUserAgreement;
    @Bind(R.id.agreement)
    LinearLayout mAgreement;
    @Bind(R.id.submit)
    Button       mSubmit;

    public static final int PIC_ID_CAR = 1;

    private String mIdCarPath_f;
    private String mIdCarPath_r;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerJoinApplyComponent.builder()
                .appComponent(appComponent)
                .joinApplyModule(new JoinApplyModule(this, this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("合伙人申请");
    }

    @Override
    public void initEvent() {
        super.initEvent();
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.title_back,
            R.id.agreement,
            R.id.id_card_accessories,
            R.id.submit})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.id_card_accessories://身份证正反面
                openPicActivity(PIC_ID_CAR, 2);
                break;
            case R.id.agreement:
                startActivity(new Intent(this, AgreementActivity.class));
                break;
            case R.id.title_back:
                finish();
                break;
            case R.id.submit:
                submit();
                break;
        }
    }

    private void submit() {
        String name = mName.getText().toString().trim();
        String conne = mContactInformation.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入真实姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mIdCarPath_f)) {
            Toast.makeText(this, "请选择身份证正面附件", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mIdCarPath_r)) {
            Toast.makeText(this, "请选择身份证反面附件", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(conne)) {
            Toast.makeText(this, "请输入联系方式", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!RegexUtils.isMobileSimple(conne)) {
            Toast.makeText(this, "请输入正确的联系方式", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!mUserAgreement.isChecked()) {
            Toast.makeText(this, "请同意用户协议", Toast.LENGTH_SHORT).show();
            return;
        }
        //上传图片// 创建 RequestBody，用于封装构建RequestBody
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)//表单类型
                .addFormDataPart("token", getUserBean().token);
        builder.addFormDataPart("member_id", getUserBean().member_id);
        builder.addFormDataPart("real_name", name);
        RequestBody idCardFile_f = RequestBody.create(MediaType.parse("multipart/form-data"), new File(mIdCarPath_f));
        builder.addFormDataPart("idcard_front", CommonUtil.getFileName(mIdCarPath_f), idCardFile_f);
        RequestBody idCardFile_r = RequestBody.create(MediaType.parse("multipart/form-data"), new File(mIdCarPath_r));
        builder.addFormDataPart("idcard_reverse", CommonUtil.getFileName(mIdCarPath_r), idCardFile_r);
        builder.addFormDataPart("mobile", conne);
        List<MultipartBody.Part> parts = builder.build().parts();
        mPresenter.pushData(parts);
    }

    //打开找点上传界面
    private void openPicActivity(int picDoor, int count) {
        Intent intent = new Intent(this, PhotoUploadActivity.class);
        PhotoUploadActivity.PhotoAttrs attrs = new PhotoUploadActivity.PhotoAttrs();
        attrs.maxPhoto = count;
        attrs.minPhoto = count;
        attrs.title = "选择身份证";
        intent.putExtra("attr", attrs);
        startActivityForResult(intent, picDoor);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null)
            switch (requestCode) {
                case PIC_ID_CAR: {
                    List<PhotoUploadAdapter.PhotoBean> list = (List<PhotoUploadAdapter.PhotoBean>) data
                            .getSerializableExtra("data");
                    if (list != null && list.size() > 0)
                        mIdCardAccessories.setText("已选择("+list.size()+")");
                    if (list.size() > 0)
                        mIdCarPath_f = list.get(0).path;
                    if (list.size() > 1)
                        mIdCarPath_r = list.get(1).path;
                }
                break;
            }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_joinapply;
    }


}
