package com.mmy.maimaiyun.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.mmy.maimaiyun.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @创建者 lucas
 * @创建时间 2017/9/22 0022 11:21
 * @描述 商家搜后 点击不同 的弹窗
 */

public class BusinessCustomerServicePopup extends PopupWindow {

    @Bind(R.id.reason)
    EditText mReason;
    Context mContext;
    private OnBCSPopupClickListener mL;

    public BusinessCustomerServicePopup(Context context) {
        super(context);
        mContext = context;
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.popup_business_customer_service, null);
        ButterKnife.bind(this,view);
        setContentView(view);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(0x00000000));
        setFocusable(true);
    }


    @OnClick({R.id.cancel, R.id.confirm})
    public void click(View view){
        switch (view.getId()) {
            case R.id.cancel:
                dismiss();
                break;
            case R.id.confirm:
                submit();
                break;
        }
    }

    private void submit() {
        String reason = mReason.getText().toString().trim();
        if (TextUtils.isEmpty(reason)){
            Toast.makeText(mContext, "请输入原因", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mL!=null)
            mL.onBCSClick(reason);
        dismiss();
    }

    public void setOnBCSPopupClickListener(OnBCSPopupClickListener l){
        mL = l;
    }

    public interface OnBCSPopupClickListener{
        void onBCSClick(String reason);
    }
}
