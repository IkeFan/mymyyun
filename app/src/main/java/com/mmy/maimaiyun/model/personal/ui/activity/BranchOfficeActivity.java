package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.blankj.utilcode.util.RegexUtils;
import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.helper.CityPickerViewHelper;
import com.mmy.maimaiyun.model.personal.adapter.PhotoUploadAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerBranchOfficeComponent;
import com.mmy.maimaiyun.model.personal.module.BranchOfficeModule;
import com.mmy.maimaiyun.model.personal.presenter.BranchOfficePresenter;
import com.mmy.maimaiyun.model.personal.view.BranchOfficeView;
import com.mmy.maimaiyun.utils.CommonUtil;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 运营商
 */
public class BranchOfficeActivity extends BaseActivity<BranchOfficePresenter> implements BranchOfficeView,
        OptionsPickerView.OnOptionsSelectListener {

    @Bind(R.id.title_center_text)
    TextView     mTitle;
    @Bind(R.id.office_city_text)
    TextView     mCity;
    @Bind(R.id.office_name)
    EditText     mOfficeName;
    @Bind(R.id.office_scenery_text)
    TextView     mOfficeSceneryText;
    @Bind(R.id.office_scenery)
    LinearLayout mOfficeScenery;
    @Bind(R.id.office_city)
    LinearLayout mOfficeCity;
    @Bind(R.id.office_address)
    EditText     mOfficeAddress;
    @Bind(R.id.legal_person)
    EditText     mLegalPerson;
    @Bind(R.id.link)
    EditText     mLink;
    @Bind(R.id.legal_img)
    TextView     mLegalImg;
    @Bind(R.id.business_license)
    TextView     mBusinessLicense;
    @Bind(R.id.tax_img)
    TextView     mTaxImg;
    @Bind(R.id.organization_code)
    TextView     mOrganizationCode;
    @Bind(R.id.bank_account)
    TextView     mBankAccount;
    @Bind(R.id.user_agreement)
    CheckBox     mUserAgreement;
    @Bind(R.id.submit)
    Button       mSubmit;

    @Inject
     CityPickerViewHelper mCityPickerViewHelper;
    private String mCityIDs;
    private boolean mIsChecked;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerBranchOfficeComponent.builder()
                .appComponent(appComponent)
                .branchOfficeModule(new BranchOfficeModule(this,this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("运营商申请");
    }

    @Override
    public void initData() {
        //初始化城市列表数据
//        mCityPickerViewHelper = new CityPickerViewHelper(this);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mCityPickerViewHelper.setOnOptionSelectListener(this);
    }

    static final int OFFICE_SCENERY             = 0;//分公司外景
    static final int LEGAL_IMG_CONTAINER        = 1;//法人身份证附件
    static final int BUSINESS_LICENSE_CONTAINER = 2;//营业执照
    static final int TAX_IMG_CONTAINER          = 3;//税务登记证
    static final int ORGANIZATION_CODE          = 4;//组织代码
    static final int BANK_ACCOUNT_CONTAINER     = 5;//银行开户许可证

    @OnClick({R.id.title_back, R.id.office_scenery, R.id.office_city, R.id.legal_img_container
            , R.id.business_license_container, R.id.tax_img_container, R.id.bank_account_container
            , R.id.submit, R.id.organization_code, R.id.agreement_text})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.agreement_text:
                startActivity(new Intent(this,AgreementActivity.class));
                break;
            case R.id.title_back:
                finish();
                break;
            case R.id.office_scenery://分公司外景
                openPicActivity(OFFICE_SCENERY, 3, 5, "运营商外景");
                break;
            case R.id.legal_img_container://法人身份证附件
                openPicActivity(LEGAL_IMG_CONTAINER, 2, 2, "法人身份证附件(正反面)");
                break;
            case R.id.business_license_container://营业执照
                openPicActivity(BUSINESS_LICENSE_CONTAINER, 1, 1, "营业执照");
                break;
            case R.id.tax_img_container://税务登记证
                openPicActivity(TAX_IMG_CONTAINER, 1, 1, "税务登记证");
                break;
            case R.id.organization_code://组织代码
                openPicActivity(ORGANIZATION_CODE, 1, 1, "组织代码");
                break;
            case R.id.bank_account_container://银行开户许可证
                openPicActivity(BANK_ACCOUNT_CONTAINER, 1, 1, "银行开户许可证");
                break;
            case R.id.office_city:
                mCityPickerViewHelper.ShowPickerView();
                break;
            case R.id.submit:
                submit();
                break;
        }
    }

    @OnCheckedChanged(R.id.user_agreement)
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mIsChecked = isChecked;
    }

    private void submit() {
        String officeName = mOfficeName.getText().toString().trim();
        if (TextUtils.isEmpty(officeName)) {
            Toast.makeText(this, "请输入运营商名称", Toast.LENGTH_SHORT).show();
            return;
        }
        String officeSceneryText = mOfficeSceneryText.getText().toString().trim();
        if (officeSceneryText.equals("请选择")) {
            Toast.makeText(this, "请上传运营商外景图片", Toast.LENGTH_SHORT).show();
            return;
        }
        String officeCity = mCity.getText().toString().trim();
        if (TextUtils.isEmpty(officeCity)) {
            Toast.makeText(this, "请选择运营商所在区域", Toast.LENGTH_SHORT).show();
            return;
        }
        String officeAddress = mOfficeAddress.getText().toString().trim();
        if (TextUtils.isEmpty(officeAddress)) {
            Toast.makeText(this, "请输入运营商所在地址", Toast.LENGTH_SHORT).show();
            return;
        }
        String legalPerson = mLegalPerson.getText().toString().trim();
        if (TextUtils.isEmpty(legalPerson)) {
            Toast.makeText(this, "请输入代表法人姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        String link = mLink.getText().toString().trim();
        if (TextUtils.isEmpty(link)) {
            Toast.makeText(this, "请输入联系方式", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!RegexUtils.isMobileSimple(link)) {
            Toast.makeText(this, "请输入正确的联系方式", Toast.LENGTH_SHORT).show();
            return;
        }
        String legalImg = mLegalImg.getText().toString().trim();
        if (legalImg.equals("请选择")) {
            Toast.makeText(this, "请上传法人身份证附件", Toast.LENGTH_SHORT).show();
            return;
        }
        String businessLicense = mBusinessLicense.getText().toString().trim();
        if (businessLicense.equals("请选择")) {
            Toast.makeText(this, "请上传营业执照", Toast.LENGTH_SHORT).show();
            return;
        }
        String taxImg = mTaxImg.getText().toString().trim();
        if (taxImg.equals("请选择")) {
            Toast.makeText(this, "请上传税务登记证", Toast.LENGTH_SHORT).show();
            return;
        }
        String organizationCode = mOrganizationCode.getText().toString().trim();
        if (organizationCode.equals("请选择")) {
            Toast.makeText(this, "请上传组织机构代码", Toast.LENGTH_SHORT).show();
            return;
        }
        String bankAccount = mBankAccount.getText().toString().trim();
        if (bankAccount.equals("请选择")) {
            Toast.makeText(this, "请上传银行开户许可证", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!mIsChecked){
            Toast.makeText(this, "请阅读并同意 用户服务协议", Toast.LENGTH_SHORT).show();
            return;
        }
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("company_name", officeName);
        builder.addFormDataPart("member_id", getUserBean().member_id);
        String[] split = mCityIDs.split(" ");
        builder.addFormDataPart("province", split[0]);
        builder.addFormDataPart("city", split[1]);
        builder.addFormDataPart("county", split[2]);
        builder.addFormDataPart("company_address", officeAddress);
        builder.addFormDataPart("name_person", legalPerson);
        builder.addFormDataPart("tel", link);
        addImg2Form(OFFICE_SCENERY, "company_location", builder, -2);
        addImg2Form(LEGAL_IMG_CONTAINER, "idcard_img_front", builder, 0);
        addImg2Form(LEGAL_IMG_CONTAINER, "idcard_img_re", builder, 1);
        addImg2Form(BUSINESS_LICENSE_CONTAINER, "bus_licence_img", builder, -1);
        addImg2Form(TAX_IMG_CONTAINER, "tax_img", builder, -1);
        addImg2Form(ORGANIZATION_CODE, "organization_code", builder, -1);
        addImg2Form(BANK_ACCOUNT_CONTAINER, "bank_account_img", builder, -1);
        List<MultipartBody.Part> parts = builder.build().parts();
        mPresenter.subBranchOfficeData(parts);
    }

    private void addImg2Form(int type, String keyName, MultipartBody.Builder builder, int index) {
        ArrayList<PhotoUploadAdapter.PhotoBean> photoBeen = mPics.get(type);
        if (index == -2) {
            //多图上传
            for (int i = 0; i < photoBeen.size(); i++) {
                PhotoUploadAdapter.PhotoBean photoBean = photoBeen.get(i);
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), new File
                        (photoBean.path));
                builder.addFormDataPart(keyName + "[" + i + "]", CommonUtil.getFileName(photoBean.path), requestBody);
            }
            return;
        }
        PhotoUploadAdapter.PhotoBean photoBean = photoBeen.get(index == -1 ? 0 : index);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), new File(photoBean.path));
        builder.addFormDataPart(keyName, CommonUtil.getFileName(photoBean.path), requestBody);
    }

    //打开找点上传界面
    private void openPicActivity(int picDoor, int min_pic, int max_pic, String title) {
        Intent intent = new Intent(this, PhotoUploadActivity.class);
        PhotoUploadActivity.PhotoAttrs attrs = new PhotoUploadActivity.PhotoAttrs();
        attrs.title = title;
        attrs.maxPhoto = max_pic;
        attrs.minPhoto = min_pic;
        intent.putExtra("attr", attrs);
        startActivityForResult(intent, picDoor);
    }

    HashMap<Integer, ArrayList<PhotoUploadAdapter.PhotoBean>> mPics = new HashMap<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null && resultCode == PhotoUploadActivity.PIC_RESULT_CODE) {
            Serializable extra = data.getSerializableExtra("data");
            ArrayList<PhotoUploadAdapter.PhotoBean> pics = null;
            pics = (ArrayList<PhotoUploadAdapter.PhotoBean>) extra;
            switch (requestCode) {
                case OFFICE_SCENERY:
                    refreshPics(pics, requestCode, mOfficeSceneryText);
                    break;
                case LEGAL_IMG_CONTAINER:
                    refreshPics(pics, requestCode, mLegalImg);
                    break;
                case BUSINESS_LICENSE_CONTAINER:
                    refreshPics(pics, requestCode, mBusinessLicense);
                    break;
                case TAX_IMG_CONTAINER:
                    refreshPics(pics, requestCode, mTaxImg);
                    break;
                case ORGANIZATION_CODE:
                    refreshPics(pics, requestCode, mOrganizationCode);
                    break;
                case BANK_ACCOUNT_CONTAINER:
                    refreshPics(pics, requestCode, mBankAccount);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void refreshPics(ArrayList<PhotoUploadAdapter.PhotoBean> pics, int requestCode, TextView
            textView) {
        if (pics != null && pics.size() != 0) {
            mPics.put(requestCode, pics);
            textView.setText("已选择(" + pics.size() + ")");
        } else {
            if (mPics.containsKey(requestCode))
                mPics.remove(requestCode);
            textView.setText("请选择");
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_branch_office;
    }

    @Override
    public void onOptionsSelect(int options1, int options2, int options3, View v) {
        //返回的分别是三个级别的选中位置
        String citys = mCityPickerViewHelper.getCitys(options1, options2, options3);
        mCityIDs = mCityPickerViewHelper.getCityIDs(options1, options2, options3);
        mCity.setText(citys);
    }

}