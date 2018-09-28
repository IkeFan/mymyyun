package com.mmy.maimaiyun.model.personal.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @创建者 lucas
 * @创建时间 2017-11-08 02-14-06
 * @描述 修改密码数据模型
 */

public class ResetPwdModel extends BaseModel {

    @Inject
    public ResetPwdModel() {
    }

    public void resetPwd(OnLoadDataListener listener, String phone, String pwd, String npwd) {
        Call<ResponseBody> call = mApi.resetPwd(phone, pwd, npwd);
        wrapRequest(listener,call);
    }
}
