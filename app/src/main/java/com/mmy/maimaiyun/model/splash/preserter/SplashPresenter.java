package com.mmy.maimaiyun.model.splash.preserter;

import android.widget.Toast;

import com.blankj.utilcode.util.NetworkUtils;
import com.mmy.maimaiyun.App;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.model.login.model.LoginModel;
import com.mmy.maimaiyun.model.splash.activity.GuideActivity;
import com.mmy.maimaiyun.model.splash.model.SplashModel;
import com.mmy.maimaiyun.model.splash.view.SplashView;
import com.mmy.maimaiyun.utils.Constants;
import com.mmy.maimaiyun.utils.GlobalUtil;
import com.mmy.maimaiyun.utils.SPUtils;
import com.google.gson.Gson;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/8/16 0016 14:35
 * @描述 TODO
 */

public class SplashPresenter extends BasePresenter<SplashView> {
    private SplashView mSplashView;
    @Inject
    LoginModel  mLoginModel;
    @Inject
    SplashModel mModel;
    @Inject
    Gson        mGson;
//    private String cacheFileName = "/citys.json";
//    String cachePath;

    @Inject
    public SplashPresenter(SplashView splashView, App app) {
        mSplashView = splashView;
//        cachePath = app.getCacheDir() + "/citys";
    }

//    public void getCitys() {
//        //判断缓存目录是否存在
//        File file = new File(cachePath);
//        if (!file.exists()) {
//            boolean mkdirs = file.mkdirs();
//            LogUtils.d("创建城市文件" + (mkdirs ? "成功" : "失败"));
//        } else {
//            //判断文件是否存在
//            File file1 = new File(cachePath + cacheFileName);
//            if (file1.exists() && file1.length() != 0)
//                return;
//        }
//        LogUtils.d("城市列表不存在，开始下载");
//        //不存在，开始下载
//        mModel.getCitys(new OnLoadDataListener<BaseBean<List<CityBean>>>() {
//            @Override
//            public void onResponse(String body, BaseBean<List<CityBean>> data) {
//                new Thread(() -> SplashPresenter.this.parseJson(data)).run();
//            }
//
//            @Override
//            public void onFailure(String body, Throwable t) {
//
//            }
//
//            @Override
//            public Type getBeanType() {
//                return new TypeToken<BaseBean<List<CityBean>>>() {
//                }.getType();
//            }
//        });
//    }

//    private void parseJson(BaseBean<List<CityBean>> data) {
//        //数据分离
//        List<CityBean> list = data.data;
//        //一级数据
//        ArrayList<ParseCityBean> parseCityBean = new ArrayList<ParseCityBean>();
//        for (CityBean bean : list) {
//            ParseCityBean city1 = new ParseCityBean();
//            if (bean.area_type == 1) {
//                city1.name = bean.area_name;
//                city1.id = bean.area_id;
//                city1.type = bean.area_type;
//                parseCityBean.add(city1);
//            }
//        }
//        //二级数据
//        for (ParseCityBean bean : parseCityBean) {
//            int id = bean.id;
//            for (CityBean cityBean : list) {
//                if (id == cityBean.parent_id) {
//                    ParseCityBean.CityBean list2 = new ParseCityBean.CityBean();
//                    list2.id = cityBean.area_id;
//                    list2.name = cityBean.area_name;
//                    list2.parent_id = cityBean.parent_id;
//                    list2.type = cityBean.area_type;
//                    if (bean.city == null) {
//                        bean.city = new ArrayList<>();
//                    }
//                    bean.city.add(list2);
//                }
//            }
//        }
//        //三级数据
//        for (ParseCityBean bean1 : parseCityBean) {
//            for (ParseCityBean.CityBean bean2 : bean1.city) {
//                int id = bean2.id;
//                for (CityBean bean3 : list) {
//                    ParseCityBean.CityBean.AreaBean areaBean = new ParseCityBean.CityBean.AreaBean();
//                    if (id == bean3.parent_id) {
//                        areaBean.id = bean3.area_id;
//                        areaBean.name = bean3.area_name;
//                        areaBean.parent_id = bean3.parent_id;
//                        areaBean.type = bean3.area_type;
//                        if (bean2.areas == null) {
//                            bean2.areas = new ArrayList<>();
//                        }
//                        bean2.areas.add(areaBean);
//                    }
//                }
//            }
//        }
//
//        //转换成json
//        String json = mGson.toJson(parseCityBean);
//        //写入缓存
//        File cacheFile = new File(cachePath + cacheFileName);
//        boolean b = FileIOUtils.writeFileFromString(cacheFile, json);
//        LogUtils.d("城市列表缓存" + (b ? "成功" : "失败"));
//    }

    //自动登录
    public void autoLogin() {
        boolean isLogin = GlobalUtil.getInstance().isExistUserInfo(mSplashView);
        //检查网络
        if (!NetworkUtils.isConnected()) {
            Toast.makeText(mApp, "无网络", Toast.LENGTH_SHORT).show();
            mSplashView.openLoginPage();
            return;
        }
        if (isLogin) {
            //等待进入主页
            mHandler.postDelayed(() -> mSplashView.openMainPage(), Constants.SPLASH_DISPLAY_TIME);
        } else {
            mHandler.postDelayed(() -> mSplashView.openLoginPage(), Constants.SPLASH_DISPLAY_TIME);
        }
    }

    //进入引导页
    public void showGuidePage() {
        boolean isNoShow = SPUtils.getInstance().getBoolean(Constants.IS_NO_SHOW_LAUNCHER);
        if (isNoShow) {
            autoLogin();
//            getCitys();
        } else {
            mHandler.postDelayed(() -> {
                mSplashView.openActivity(GuideActivity.class);
                mSplashView.finishSelf();
            }, Constants.SPLASH_DISPLAY_TIME);
        }
    }
}
