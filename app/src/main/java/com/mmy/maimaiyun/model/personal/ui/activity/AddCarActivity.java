package com.mmy.maimaiyun.model.personal.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.model.personal.component.DaggerAddCarComponent;
import com.mmy.maimaiyun.model.personal.module.AddCarModule;
import com.mmy.maimaiyun.model.personal.presenter.AddCarPresenter;
import com.mmy.maimaiyun.model.personal.view.AddCarView;
import com.mmy.maimaiyun.utils.CommonUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 添加银行卡
 */
public class AddCarActivity extends BaseActivity<AddCarPresenter> implements AddCarView {

    @Bind(R.id.title_center_text)
    TextView mTitle;
    @Bind(R.id.name)
    EditText mName;
    @Bind(R.id.id_car)
    EditText mIdCar;
    @Bind(R.id.bank_name)
    EditText mBankName;
    @Bind(R.id.bank_car_number)
    EditText mBankCarNumber;


    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerAddCarComponent.builder()
                .addCarModule(new AddCarModule(this))
                .appComponent(appComponent)
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("添加银行卡");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.add})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.add:
                submit();
        }
    }

    private void submit() {
        String name = mName.getText().toString().trim();
        String idCar = mIdCar.getText().toString().trim();
        String bankName = mBankName.getText().toString().trim();
        String bankNumber = mBankCarNumber.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入持卡人姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(idCar)) {
            Toast.makeText(this, "请输入身份证号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!CommonUtil.personIdValidation(idCar)) {
            Toast.makeText(this, "请输入正确的身份证号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(bankName)) {
            Toast.makeText(this, "请输入您的开户银行", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(bankNumber)) {
            Toast.makeText(this, "请输入银行卡号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!CommonUtil.checkBankCard(bankNumber)) {
            Toast.makeText(this, "银行卡号格式错误", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.addCard(name, idCar, bankName, bankNumber);
    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_add_car;
    }

}
