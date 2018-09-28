package com.mmy.maimaiyun.model.personal.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.data.bean.AddressItemBean;
import com.mmy.maimaiyun.helper.CityPickerViewHelper;
import com.mmy.maimaiyun.model.personal.component.DaggerAddressModifyComponent;
import com.mmy.maimaiyun.model.personal.module.AddressModifyModule;
import com.mmy.maimaiyun.model.personal.presenter.AddressModifyPresenter;
import com.mmy.maimaiyun.model.personal.view.AddressModifyView;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 修改地址
 */
public class AddressModifyActivity extends BaseActivity<AddressModifyPresenter> implements AddressModifyView,
        OptionsPickerView.OnOptionsSelectListener {

    @Bind(R.id.title_center_text)
    TextView             mTitle;
    @Bind(R.id.title_right_text)
    TextView             mRight;
    @Bind(R.id.city_text)
    TextView             mCityText;
    @Bind(R.id.receipt_name)
    EditText             mName;
    @Bind(R.id.phone)
    EditText             mPhone;
    @Bind(R.id.address)
    EditText             mAddressDetail;
    @Inject
    CityPickerViewHelper mCityPickerViewHelper;
    private AddressItemBean mBean;
    private String          mCityIDs;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerAddressModifyComponent.builder()
                .addressModifyModule(new AddressModifyModule(this, this))
                .appComponent(appComponent)
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("修改地址");
        mRight.setText("保存");
        mRight.setTextColor(getResources().getColor(R.color.colorPrimary));
        //        mCityPickerViewHelper = new CityPickerViewHelper(this);
        mBean = (AddressItemBean) getIntent().getSerializableExtra("bean");
        mName.setText(mBean.consignee);
        mPhone.setText(mBean.mobile);
        mAddressDetail.setText(mBean.address);
        mCityText.setText(mBean.province + " " + mBean.city + " " + mBean.district);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        super.initEvent();
        mCityPickerViewHelper.setOnOptionSelectListener(this);
    }

    @OnClick({R.id.title_right_text, R.id.delete, R.id.city})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.city:
                mCityPickerViewHelper.ShowPickerView();
                break;
            case R.id.title_right_text:
                modify();
                break;
            case R.id.delete:
                mPresenter.delAddress(mBean.address_id);
                break;
        }
    }

    private void modify() {
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

        HashMap<String, String> map = new HashMap<>();
        map.put("consignee", name);
        map.put("address_id", mBean.address_id);
        map.put("mobile", phone);
        if (mCityIDs != null) {
            String[] split = mCityIDs.split(" ");
            map.put("province", split[0]);
            map.put("city", split[1]);
            map.put("district", split[2]);
        }
        map.put("street", "");
        map.put("address", detail);
        map.put("is_default", "1");
        mPresenter.modifyData(map);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_address_modify;
    }

    @Override
    public void onOptionsSelect(int options1, int options2, int options3, View v) {
        String citys = mCityPickerViewHelper.getCitys(options1, options2, options3);
        mCityIDs = mCityPickerViewHelper.getCityIDs(options1, options2, options3);
        mCityText.setText(citys);
    }
}
