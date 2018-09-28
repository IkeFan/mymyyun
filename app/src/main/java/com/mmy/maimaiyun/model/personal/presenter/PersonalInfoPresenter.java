package com.mmy.maimaiyun.model.personal.presenter;

import android.app.Activity;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.UserBean;
import com.mmy.maimaiyun.model.personal.model.PersonalInfoModel;
import com.mmy.maimaiyun.model.personal.view.PersonalInfoView;
import com.mmy.maimaiyun.utils.CommonUtil;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * @创建者 lucas
 * @创建时间 2017/10/18 0018 16:37
 * @描述 TODO
 */

public class PersonalInfoPresenter extends BasePresenter<PersonalInfoView> {
    private PersonalInfoView mView;
    @Inject
    PersonalInfoModel mModel;
    private final Activity mActivity;

    @Inject
    public PersonalInfoPresenter(PersonalInfoView view) {
        mView = view;
        mActivity = (Activity) view;
    }

    public void uploadImg(String path) {
        //上传图片// 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), new File(path));
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)//表单类型
                .addFormDataPart("token", mView.getUserBean().token);
        builder.addFormDataPart("member_id", mView.getUserBean().member_id);
        String fileName = CommonUtil.getFileName(path);
        builder.addFormDataPart("head_pic", fileName, requestFile);
        List<MultipartBody.Part> parts = builder.build().parts();
        mModel.uploadPic(new OnLoadDataListener<BaseBean<UserBean>>() {
            @Override
            public void onResponse(String body, BaseBean<UserBean> data) {
                if (data.status == 1) {
                    Toast.makeText(mApp, "修改成功！", Toast.LENGTH_SHORT).show();
                    mView.refreshView(data.data);
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<UserBean>>() {
                }.getType();
            }
        }, parts);
    }

    public void mdifInfo(HashMap<String, String> data) {
        data.put("token", mView.getUserBean().token);
        data.put("member_id", mView.getUserBean().member_id);
        mModel.pushData(new OnLoadDataListener<BaseBean<UserBean>>() {
            @Override
            public void onResponse(String body, BaseBean<UserBean> data) {
                mView.refreshView(data.data);
                Toast.makeText(mActivity, data.info, Toast.LENGTH_SHORT).show();
                mView.finishSelf();
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<UserBean>>() {
                }.getType();
            }
        }, data);
    }
}
