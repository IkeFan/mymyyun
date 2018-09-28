package com.mmy.maimaiyun.helper;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.LogUtils;
import com.mmy.maimaiyun.App;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.data.bean.CityBean;
import com.mmy.maimaiyun.data.bean.ParseCityBean;
import com.mmy.maimaiyun.model.splash.model.SplashModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @创建者 lucas
 * @创建时间 2017/9/1 0001 9:43
 * @描述 城市选择器
 */

public class CityPickerViewHelper {

    private ArrayList<ParseCityBean>                options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>>            options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA    = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED  = 0x0003;

    private boolean isLoaded = false;
    private OptionsPickerView.OnOptionsSelectListener mListener;
    private OnLoadCompleteListener                    mOnLoadCompleteListener;
    private BaseActivity                              mActivity;
    SplashModel mModel;
    Gson        mGson;
    App         mApp;
    private String cacheFileName = "/citys.json";
    String cachePath;

    @Inject
    public CityPickerViewHelper(BaseActivity activity, SplashModel model, Gson gson, App app) {
        mActivity = activity;
        mModel = model;
        mGson = gson;
        mApp = app;
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                //下载城市数据
                                downloadData();
                            }
                        });
                        thread.start();
                    }
                    break;
                case MSG_LOAD_SUCCESS:
                    //                    Toast.makeText(mContext, "Parse Succeed", Toast.LENGTH_SHORT).show();
                    isLoaded = true;
                    break;
                case MSG_LOAD_FAILED:
                    //                    Toast.makeText(mContext, "Parse Failed", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };

    private void downloadData() {
        cachePath = mApp.getCacheDir() + "/citys";
        getCitys();
    }

    public void getCitys() {
        //判断缓存目录是否存在
        File file = new File(cachePath);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            LogUtils.d("创建城市文件" + (mkdirs ? "成功" : "失败"));
        } else {
            //判断文件是否存在
            File file1 = new File(cachePath + cacheFileName);
            if (file1.exists() && file1.length() != 0) {
                // 写子线程中的操作,解析省市区数据
                initJsonData();
                return;
            }
        }
        LogUtils.d("城市列表不存在，开始下载");
        //不存在，开始下载
        mModel.getCitys(new OnLoadDataListener<BaseBean<List<CityBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<CityBean>> data) {
                new Thread(() -> CityPickerViewHelper.this.parseJson(data)).run();
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<CityBean>>>() {
                }.getType();
            }
        });
    }

    private void parseJson(BaseBean<List<CityBean>> data) {
        //数据分离
        List<CityBean> list = data.data;
        //一级数据
        ArrayList<ParseCityBean> parseCityBean = new ArrayList<ParseCityBean>();
        for (CityBean bean : list) {
            ParseCityBean city1 = new ParseCityBean();
            if (bean.area_type == 1) {
                city1.name = bean.area_name;
                city1.id = bean.area_id;
                city1.type = bean.area_type;
                parseCityBean.add(city1);
            }
        }
        //二级数据
        for (ParseCityBean bean : parseCityBean) {
            int id = bean.id;
            for (CityBean cityBean : list) {
                if (id == cityBean.parent_id) {
                    ParseCityBean.CityBean list2 = new ParseCityBean.CityBean();
                    list2.id = cityBean.area_id;
                    list2.name = cityBean.area_name;
                    list2.parent_id = cityBean.parent_id;
                    list2.type = cityBean.area_type;
                    if (bean.city == null) {
                        bean.city = new ArrayList<>();
                    }
                    bean.city.add(list2);
                }
            }
        }
        //三级数据
        for (ParseCityBean bean1 : parseCityBean) {
            for (ParseCityBean.CityBean bean2 : bean1.city) {
                int id = bean2.id;
                for (CityBean bean3 : list) {
                    ParseCityBean.CityBean.AreaBean areaBean = new ParseCityBean.CityBean.AreaBean();
                    if (id == bean3.parent_id) {
                        areaBean.id = bean3.area_id;
                        areaBean.name = bean3.area_name;
                        areaBean.parent_id = bean3.parent_id;
                        areaBean.type = bean3.area_type;
                        if (bean2.areas == null) {
                            bean2.areas = new ArrayList<>();
                        }
                        bean2.areas.add(areaBean);
                    }
                }
            }
        }

        //转换成json
        String json = mGson.toJson(parseCityBean);
        //写入缓存
        File cacheFile = new File(cachePath + cacheFileName);
        boolean b = FileIOUtils.writeFileFromString(cacheFile, json);
        LogUtils.d("城市列表缓存" + (b ? "成功" : "失败"));
        if (!b) {
            Toast.makeText(mApp, "城市列表缓存失败", Toast.LENGTH_SHORT).show();
            mActivity.finish();
            return;
        }
        // 写子线程中的操作,解析省市区数据
        initJsonData();
    }

    public void setOnOptionSelectListener(OptionsPickerView.OnOptionsSelectListener listener) {
        mListener = listener;
    }

    public String getCitys(int options1, int options2, int options3) {
        return options1Items.get(options1).getPickerViewText() + " " +
                options2Items.get(options1).get(options2) + " " +
                options3Items.get(options1).get(options2).get(options3);
    }

    public String getCityIDs(int options1, int options2, int options3) {
        return options1Items.get(options1).id + " " +
                options1Items.get(options1).city.get(options2).id + " " +
                options1Items.get(options1).city.get(options2).areas.get(options3).id;
    }

    //根据id获取城市名称
    public String getCityNameById(int id1, int id2, int id3) {
        String temp = "";
        for (ParseCityBean item : options1Items) {
            if (item.id == id1) {
                temp = temp + " " + item.name;
                for (ParseCityBean.CityBean bean : item.city) {
                    if (bean.id == id2) {
                        temp = temp + " " + bean.name;
                        for (ParseCityBean.CityBean.AreaBean area : bean.areas) {
                            if (area.id == id3)
                                temp = temp + " " + area.name;
                        }
                    }
                }
            }
        }
        return temp;
    }

    public void ShowPickerView() {// 弹出选择器
        if (!isLoaded) {
            //            Toast.makeText(mContext, "Please waiting until the data is parsed", Toast.LENGTH_SHORT)
            // .show();
            return;
        }
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(mActivity, mListener)
                .setTitleText("城市选择")
                .setCancelText("取消")
                .setSubmitText("确认")
                .setDividerColor(R.color.colorPrimary)
                .setTextColorCenter(R.color.colorPrimary) //设置选中项文字颜色
                .setContentTextSize(25)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = FileIOUtils.readFile2String(new File(mApp.getCacheDir() + "/citys/citys.json"));
        ArrayList<ParseCityBean> arrayList = new Gson().fromJson(JsonData, new TypeToken<ArrayList<ParseCityBean>>() {
        }.getType());
        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = arrayList;

        for (int i = 0; i < arrayList.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < arrayList.get(i).city.size(); c++) {//遍历该省份的所有城市
                String CityName = arrayList.get(i).city.get(c).name;
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (arrayList.get(i).city.get(c).areas == null
                        || arrayList.get(i).city.get(c).areas.size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < arrayList.get(i).city.get(c).areas.size(); d++) {//该城市对应地区所有数据
                        String AreaName = arrayList.get(i).city.get(c).areas.get(d).name;

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);
        if (mOnLoadCompleteListener != null) {
            mOnLoadCompleteListener.onLoadComplete();
        }
    }

    public void setOnLoadCompleteListener(OnLoadCompleteListener onLoadCompleteListener) {
        mOnLoadCompleteListener = onLoadCompleteListener;
    }

    public interface OnLoadCompleteListener {
        void onLoadComplete();
    }
}
