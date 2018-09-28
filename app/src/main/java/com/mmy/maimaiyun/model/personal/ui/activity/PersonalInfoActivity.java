package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.data.bean.UserBean;
import com.mmy.maimaiyun.helper.CircleImageTransformation;
import com.mmy.maimaiyun.helper.CityPickerViewHelper;
import com.mmy.maimaiyun.helper.PicSelectedHelper;
import com.mmy.maimaiyun.model.personal.component.DaggerPersonalInfoComponent;
import com.mmy.maimaiyun.model.personal.module.PersonalInfoModule;
import com.mmy.maimaiyun.model.personal.presenter.PersonalInfoPresenter;
import com.mmy.maimaiyun.model.personal.view.PersonalInfoView;
import com.mmy.maimaiyun.utils.GlobalUtil;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 个人详情
 */
public class PersonalInfoActivity extends BaseActivity<PersonalInfoPresenter> implements PersonalInfoView,
        PicSelectedHelper.OnPicSelectListener, OptionsPickerView.OnOptionsSelectListener, CityPickerViewHelper
                .OnLoadCompleteListener {

    @Bind(R.id.title_center_text)
    TextView  mTitleCenterText;
    @Bind(R.id.nick_name)
    TextView  mNickName;
    @Bind(R.id.qq)
    TextView  mQq;
    @Bind(R.id.sex)
    TextView  mSex;
    @Bind(R.id.email)
    TextView  mEmail;
    @Bind(R.id.phone)
    TextView  mPhone;
    @Bind(R.id.pwd)
    TextView  mPwd;
    @Bind(R.id.city)
    TextView  mCity;
    @Bind(R.id.icon)
    ImageView mIcon;
    private PicSelectedHelper mSelectedHelper;

    @Inject
    CityPickerViewHelper mCityPickerViewHelper;
    private HashMap<String, String> mMap = new HashMap<>();

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerPersonalInfoComponent.builder()
                .appComponent(appComponent)
                .personalInfoModule(new PersonalInfoModule(this, this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitleCenterText.setText("个人资料");
        //        mCityPickerViewHelper = new CityPickerViewHelper(this);
        mSelectedHelper = new PicSelectedHelper(this);
        showLoading();
    }

    @Override
    public void initData() {
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mSelectedHelper.setOnPicSelectedListener(this);
        mCityPickerViewHelper.setOnOptionSelectListener(this);
        mCityPickerViewHelper.setOnLoadCompleteListener(this);
    }

    @OnClick({R.id.mdf_head_img, R.id.nick_name_parent, R.id.sex_parent, R.id.city_parent, R.id.reset_pwd, R.id
            .submit, R.id.qq_, R.id.email_})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.qq_:
                openEditPage(EditActivity.EDIT_QQ);
                break;
            case R.id.email_:
                openEditPage(EditActivity.EDIT_EMAIL);
                break;
            case R.id.submit:
                mPresenter.mdifInfo(mMap);
                break;
            case R.id.reset_pwd://修改密码
                //判断是否是三方账号
                if (!TextUtils.isEmpty(getUserBean().password))
                    startActivity(new Intent(this, ResetPwdActivity.class));
                break;
            case R.id.mdf_head_img:
                mSelectedHelper.showPopup();
                break;
            case R.id.nick_name_parent:
                openEditPage(EditActivity.EDIT_NICK_NAME);
                break;
            case R.id.sex_parent:
                Intent intent = new Intent(this, EditActivity.class);
                intent.putExtra("action", EditActivity.EDIT_SEX);
                intent.putExtra("sex", getUserBean().sex);
                startActivityForResult(intent, EditActivity.EDIT_SEX);
                break;
            case R.id.city_parent:
                mCityPickerViewHelper.ShowPickerView();
                break;
        }
    }

    private void openEditPage(int action) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("action", action);
        startActivityForResult(intent, action);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case EditActivity.EDIT_QQ:
                String qq = data.getStringExtra("qq");
                mMap.put("qq", qq);
                mQq.setText(qq);
                break;
            case EditActivity.EDIT_EMAIL:
                String email = data.getStringExtra("email");
                mMap.put("email", email);
                mEmail.setText(email);
                break;
            case EditActivity.EDIT_NICK_NAME:
                String nick_name = data.getStringExtra("nick_name");
                mMap.put("nickname", nick_name);
                mNickName.setText(nick_name);
                break;
            case EditActivity.EDIT_SEX:
                int sex = Integer.parseInt(data.getStringExtra("sex"));
                mMap.put("sex", sex + "");
                mSex.setText(sex == 0 ? "未知" : (sex == 1 ? "男" : "女"));
                break;
        }
        mSelectedHelper.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_personal_info;
    }

    @Override
    public void onSelected(String path, Bitmap photo) {
        mIcon.setImageBitmap(photo);
        mPresenter.uploadImg(path);
    }

    @Override
    public void refreshView(UserBean userBean) {
        //刷新缓存数据
        GlobalUtil.getInstance().refreshUserInfo(userBean, this);
        if (!TextUtils.isEmpty(userBean.headimgurl))
            Picasso.with(this).load(userBean.headimgurl).transform(new CircleImageTransformation()).into(mIcon);
        if (!TextUtils.isEmpty(userBean.nickname))
            mNickName.setText(userBean.nickname);
        mQq.setText(userBean.qq);
        mSex.setText(userBean.sex == 0 ? "未知" : (userBean.sex == 1 ? "男" : "女"));
        mEmail.setText(userBean.email);
        mPhone.setText(userBean.phone);
        String nameById = mCityPickerViewHelper.getCityNameById(userBean.province, userBean.city, userBean
                .district);
        mCity.setText(nameById);
    }

    @Override
    public void onOptionsSelect(int options1, int options2, int options3, View v) {
        String citys = mCityPickerViewHelper.getCitys(options1, options2, options3);
        String cityIDs = mCityPickerViewHelper.getCityIDs(options1, options2, options3);
        mCity.setText(citys);
        String[] split = cityIDs.split(" ");
        mMap.put("province", split[0]);
        mMap.put("city", split[1]);
        mMap.put("district", split[2]);
    }

    @Override
    public void onLoadComplete() {
        hideLoading();
        UserBean userBean = getUserBean();
        Log.d("PersonalInfoActivity", userBean.toString());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                refreshView(userBean);
            }
        });
    }
}
