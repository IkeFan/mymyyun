package com.mmy.maimaiyun.model.personal.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.blankj.utilcode.util.StringUtils;
import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.helper.CityPickerViewHelper;
import com.mmy.maimaiyun.model.personal.component.DaggerAddNewsAddressComponent;
import com.mmy.maimaiyun.model.personal.module.AddNewsAddressModule;
import com.mmy.maimaiyun.model.personal.presenter.AddNewsAddressPresenter;
import com.mmy.maimaiyun.model.personal.view.AddNewsAddressView;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 添加新地址
 */
public class AddNewAddressActivity extends BaseActivity<AddNewsAddressPresenter> implements AddNewsAddressView,
        OptionsPickerView.OnOptionsSelectListener, CompoundButton.OnCheckedChangeListener {
    @Bind(R.id.title_center_text)
    TextView mTitle;
    @Bind(R.id.title_right_text)
    TextView mRight;
    @Bind(R.id.city_text)
    TextView mCityText;
    @Bind(R.id.name)
    EditText mName;
    @Bind(R.id.phone)
    EditText mPhone;
    @Bind(R.id.address_detail)
    EditText mAddressDetail;
    @Bind(R.id.is_default)
    CheckBox mIsDefault;

    @Inject
    CityPickerViewHelper mCityPickerViewHelper;
    private boolean mIsChecked = false;
    private String mCityIDs;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerAddNewsAddressComponent.builder()
                .addNewsAddressModule(new AddNewsAddressModule(this, this))
                .appComponent(appComponent)
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("添加新地址");
        mRight.setText("保存");
        mRight.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void initData() {
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mCityPickerViewHelper.setOnOptionSelectListener(this);
        mIsDefault.setOnCheckedChangeListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_add_new_address;
    }

    @OnClick({R.id.title_right_text, R.id.city})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.title_right_text:
                submit();
                break;
            case R.id.city:
                mCityPickerViewHelper.ShowPickerView();
                break;
        }
    }

    private void submit() {
        String name = mName.getText().toString().trim();
        String phone = mPhone.getText().toString().trim();
        String detail = mAddressDetail.getText().toString().trim();
        String city = mCityText.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请填写收货人", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请填写手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(detail)) {
            Toast.makeText(this, "请填写详细地址", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(city)) {
            Toast.makeText(this, "请选择地区", Toast.LENGTH_SHORT).show();
            return;
        }
        if (StringUtils.isEmpty(mCityIDs)){
            Toast.makeText(this, "请选择地区", Toast.LENGTH_SHORT).show();
            return;
        }
        String[] split = mCityIDs.split(" ");
        HashMap<String, String> map = new HashMap<>();
        map.put("consignee", name);
        map.put("mobile", phone);
        if (!StringUtils.isEmpty(mCityIDs)) {
            map.put("province", split[0]);
            map.put("city", split[1]);
            map.put("district", split[2]);
        }
        map.put("street", "");
        map.put("address", detail);
        map.put("is_default", mIsChecked ? "1" : "0");
        mPresenter.submit(map);
    }

    @Override
    public void success(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void fail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOptionsSelect(int options1, int options2, int options3, View v) {
        String citys = mCityPickerViewHelper.getCitys(options1, options2, options3);
        mCityIDs = mCityPickerViewHelper.getCityIDs(options1, options2, options3);
        mCityText.setText(citys);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mIsChecked = isChecked;
    }
}
