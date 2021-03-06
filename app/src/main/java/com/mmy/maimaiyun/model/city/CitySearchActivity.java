package com.mmy.maimaiyun.model.city;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mmy.maimaiyun.App;
import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.data.bean.EventBean;
import com.mmy.maimaiyun.utils.Constants;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.Bind;


/**
 * 城市搜索界面
 */
public class CitySearchActivity extends BaseActivity implements OnScrollListener, App.OnAppLocationListenner {
    private BaseAdapter              adapter;
    private ResultListAdapter        resultListAdapter;
    private ListView                 personList;
    private ListView                 resultList;
    private TextView                 overlay; // 对话框首字母textview
    private MyLetterListView         letterListView; // A-Z listview
    private HashMap<String, Integer> alphaIndexer;// 存放存在的汉语拼音首字母和与之对应的列表位置
    private String[]                 sections;// 存放存在的汉语拼音首字母
    private Handler                  handler;
    private OverlayThread            overlayThread; // 显示首字母对话框
    private ArrayList<City>          allCity_lists; // 所有城市列表
    private ArrayList<City>          city_lists;// 城市列表
    private ArrayList<City>          city_hot;
    private ArrayList<City>          city_result;
    private ArrayList<City>          city_history;
    private EditText                 sh;
    private TextView                 tv_noresult;

    private String currentCity; // 用于保存定位到的城市
    private int locateProcess = 1; // 记录当前定位的状态 正在定位-定位成功-定位失败

    @Bind(R.id.title_center_text)
    TextView mTitle;
    App mApp;

    public static final int SELECT_CITY = 1;

    private DatabaseHelper helper;
    @Override
    protected void initDagger(AppComponent appComponent) {
        mApp = appComponent.getApp();
    }

    @Override
    public void initView() {
        mTitle.setText("切换城市");
        personList = (ListView) findViewById(R.id.list_view);
        allCity_lists = new ArrayList<>();
        city_hot = new ArrayList<>();
        city_result = new ArrayList<>();
        city_history = new ArrayList<>();
        resultList = (ListView) findViewById(R.id.search_result);
        sh = (EditText) findViewById(R.id.sh);
        tv_noresult = (TextView) findViewById(R.id.tv_noresult);
        helper = new DatabaseHelper(this);
        sh.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString() == null || "".equals(s.toString())) {
                    letterListView.setVisibility(View.VISIBLE);
                    personList.setVisibility(View.VISIBLE);
                    resultList.setVisibility(View.GONE);
                    tv_noresult.setVisibility(View.GONE);
                } else {
                    city_result.clear();
                    letterListView.setVisibility(View.GONE);
                    personList.setVisibility(View.GONE);
                    getResultCityList(s.toString());
                    if (city_result.size() <= 0) {
                        tv_noresult.setVisibility(View.VISIBLE);
                        resultList.setVisibility(View.GONE);
                    } else {
                        tv_noresult.setVisibility(View.GONE);
                        resultList.setVisibility(View.VISIBLE);
                        resultListAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        letterListView = (MyLetterListView) findViewById(R.id.MyLetterListView01);
        letterListView.setOnTouchingLetterChangedListener(new LetterListViewListener());
        alphaIndexer = new HashMap<>();
        handler = new Handler();
        overlayThread = new OverlayThread();
        personList.setOnItemClickListener((parent, view, position, id) -> {
            if (position >= 4) {
                EventBus.getDefault().post(new EventBean(allCity_lists.get(position).getName(),SELECT_CITY));
                //   设置初始list监听  点击了 添加到数据库
                //获取的listView的item中的城市信息
                // SPUtils.put(CitySearchActivity.this, "cityID", allCity_lists.get(position).getCityID());
                // SPUtils.put(CitySearchActivity.this, "cityName", allCity_lists.get(position).getName());
                //点击搜索结果传值给首页定位结果
                //EventBus.getDefault().post(SPUtils.get(CitySearchActivity.this, "cityName",
                // Constants
                //                            .DEFAULT_CITY_NAME));
                finish();
            }
        });
        locateProcess = 1;
        personList.setAdapter(adapter);
        personList.setOnScrollListener(this);
        resultListAdapter = new ResultListAdapter(this, city_result);
        resultList.setAdapter(resultListAdapter);
        resultList.setOnItemClickListener((parent, view, position, id) -> {
            //  设置搜索结果,往数据库添加数据
            //                InsertCity(city_result.get(position).getName());
            //                SPUtils.put(CitySearchActivity.this, "LocationCity", city_result.get(position).getName());
            //                //点击搜索结果传值给首页定位结果
            //                EventBus.getDefault().post(SPUtils.get(CitySearchActivity.this, "LocationCity", "重庆市"));
            finish();
        });
    }

    @Override
    public void initData() {
        initOverlay();
        cityInit();
        hotCityInit();
        hisCityInit();
        setAdapter(allCity_lists, city_hot, city_history);
    }

    @Override
    public void initEvent() {
        App.getAppComponent().getApp().registerLocationListener(this);
    }

    @Override
    public void onLocationSuccess(String city) {
        locateProcess = 2;
        currentCity = city;
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getAppComponent().getApp().unregisterLocationListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_citysearch;
    }

    public void InsertCity(String name) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from recentcity where name = '"
                + name + "'", null);
        if (cursor.getCount() > 0) { //
            db.delete("recentcity", "name = ?", new String[]{name});
        }
        db.execSQL("insert into recentcity(name, date) values('" + name + "', "
                + System.currentTimeMillis() + ")");
        db.close();
    }

    private void cityInit() {
        City city;
        city = new City("定位", "0"); // 当前定位城市
        allCity_lists.add(city);
//        city = new City("最近", "1"); // 最近访问的城市
//        allCity_lists.add(city);
        city = new City("热门", "2"); // 热门城市
        allCity_lists.add(city);
        city = new City("全部", "3"); // 全部城市
        allCity_lists.add(city);
        city_lists = getCityList();
        allCity_lists.addAll(city_lists);
    }

    /**
     * 热门城市
     */
    public void hotCityInit() {
        String[] hotCitys = {"深圳市"};
        for (String s : hotCitys) {
            city_hot.add(new City(s, "2", findCityID(s)));
        }
    }

    //查找城市ID,未找到默认返回重庆市
    public int findCityID(String cityName) {
        DBHelper dbHelper = new DBHelper(mApp);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select cityID from city where name like \"%" + cityName + "%\"", null);
        //取第一条
        int cityID = Constants.DEFAULT_CITY_ID;
        if (cursor.moveToNext())
            cityID = cursor.getInt(0);
        cursor.close();
        db.close();
        return cityID == -1 ? Constants.DEFAULT_CITY_ID : cityID;
    }

    private void hisCityInit() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "select * from recentcity order by date desc limit 0, 3", null);
        while (cursor.moveToNext()) {
            city_history.add(new City(cursor.getString(1), "1", findCityID(cursor.getString(1))));
        }
        cursor.close();
        db.close();
    }


    @SuppressWarnings("unchecked")
    private ArrayList<City> getCityList() {
        DBHelper dbHelper = new DBHelper(this);
        ArrayList<City> list = new ArrayList<>();
        //            dbHelper.createDataBase();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from city", null);
        City city;
        while (cursor.moveToNext()) {
            city = new City(cursor.getString(1), cursor.getString(2));
            city.setCityID(Integer.parseInt(cursor.getString(3)));
            list.add(city);
        }
        cursor.close();
        db.close();
        Collections.sort(list, comparator);
        return list;
    }

    @SuppressWarnings("unchecked")
    private void getResultCityList(String keyword) {
        DBHelper dbHelper = new DBHelper(this);
        //            dbHelper.createDataBase();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "select * from city where name like \"%" + keyword
                        + "%\" or pinyin like \"%" + keyword + "%\"", null);
        City city;
        Log.e("info", "length = " + cursor.getCount());
        while (cursor.moveToNext()) {
            city = new City(cursor.getString(1), cursor.getString(2));
            city_result.add(city);
        }
        cursor.close();
        db.close();
        Collections.sort(city_result, comparator);
    }

    /**
     * a-z排序
     */
    @SuppressWarnings("rawtypes")
    Comparator comparator = (Comparator<City>) (lhs, rhs) -> {
        String a = lhs.getPinyi().substring(0, 1);
        String b = rhs.getPinyi().substring(0, 1);
        int flag = a.compareTo(b);
        if (flag == 0) {
            return a.compareTo(b);
        } else {
            return flag;
        }
    };

    private void setAdapter(List<City> list, List<City> hotList,
                            List<City> hisCity) {
        adapter = new ListAdapter(this, list, hotList, hisCity);
        personList.setAdapter(adapter);
    }


    private class ResultListAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private ArrayList<City> results = new ArrayList<>();

        public ResultListAdapter(Context context, ArrayList<City> results) {
            inflater = LayoutInflater.from(context);
            this.results = results;
        }

        @Override
        public int getCount() {
            return results.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.citysearchlist_item, null);
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView) convertView
                        .findViewById(R.id.name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.name.setText(results.get(position).getName());

            return convertView;
        }

        class ViewHolder {
            TextView name;
        }
    }

    public class ListAdapter extends BaseAdapter {
        private Context        context;
        private LayoutInflater inflater;
        private List<City>     list;
        private List<City>     hotList;
        private List<City>     hisCity;
        final int VIEW_TYPE = 5;

        public ListAdapter(Context context, List<City> list,
                           List<City> hotList, List<City> hisCity) {

            this.inflater = LayoutInflater.from(context);
            this.list = list;
            this.context = context;
            this.hotList = hotList;
            this.hisCity = hisCity;
            alphaIndexer = new HashMap<>();
            sections = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                // 当前汉语拼音首字母
                String currentStr = getAlpha(list.get(i).getPinyi());
                // 上一个汉语拼音首字母，如果不存在为" "
                String previewStr = (i - 1) >= 0 ? getAlpha(list.get(i - 1)
                        .getPinyi()) : " ";
                if (!previewStr.equals(currentStr)) {
                    String name = getAlpha(list.get(i).getPinyi());
                    alphaIndexer.put(name, i);
                    sections[i] = name;
                }
            }
        }

        @Override
        public int getViewTypeCount() {
            return VIEW_TYPE;
        }

        @Override
        public int getItemViewType(int position) {
            return position < 3 ? position : 3;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        ViewHolder holder;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final TextView city;
            int viewType = getItemViewType(position);
            if (viewType == 0) { // 定位
                convertView = inflater.inflate(R.layout.frist_list_item, null);
                final TextView locateHint = (TextView) convertView
                        .findViewById(R.id.locateHint);
                city = (TextView) convertView.findViewById(R.id.lng_city);
                city.setOnClickListener(v -> {
                    if (locateProcess == 2) {
                        //搜索结果
                        InsertCity(city.getText() + "");
//                            if (!city.getText().equals("重庆市")) {
//                                showErrorCityDialog();
//                                return;
//                            }
                        //                            SPUtils.put(CitySearchActivity.this, "cityName", city
                        // .getText());
                        //                            SPUtils.put(CitySearchActivity.this, "cityID", findCityID
                        // (city.getText().toString()));
                        //                            //点击搜索结果传值给首页定位结果
                        //                            EventBus.getDefault().post(SPUtils.get(CitySearchActivity
                        // .this, "cityName",
                        //                                    Constants.DEFAULT_CITY_NAME));
                        finish();
                    } else if (locateProcess == 3) {
                        locateProcess = 1;
                        personList.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        currentCity = "";
                    }
                });
                final ProgressBar pbLocate = (ProgressBar) convertView
                        .findViewById(R.id.pbLocate);
                if (locateProcess == 1) { // 正在定位
                    locateHint.setText("正在定位");
                    city.setVisibility(View.GONE);
                    pbLocate.setVisibility(View.VISIBLE);
//                    UIUtils.postDelayedTask(new Runnable() {
//                        @Override
//                        public void run() {
//                            locateHint.setText("当前定位城市");
//                            city.setVisibility(View.VISIBLE);
//                            if (currentCity == null) {
//                                locateHint.setText("未定位到城市,请重新定位");
//                                locateHint.setGravity(Gravity.CENTER_VERTICAL);
//                                locateHint.setClickable(false);
//                                mLocationClient.stopLocation();
//                                pbLocate.setVisibility(View.GONE);
//                                city.setVisibility(View.GONE);
//                            } else {
//                                city.setText(currentCity);
//                                mLocationClient.stopLocation();
//                                pbLocate.setVisibility(View.GONE);
//                            }
//                        }
//                    }, 2000);
                } else if (locateProcess == 2) { // 定位成功
                    if (currentCity == null) {
                        locateHint.setText("未定位到城市,请重新定位");
                        locateHint.setGravity(Gravity.CENTER_VERTICAL);
                        locateHint.setClickable(false);
                        pbLocate.setVisibility(View.GONE);
                        city.setVisibility(View.GONE);
                    } else {
                        locateHint.setText("当前定位城市");
                        city.setVisibility(View.VISIBLE);
                        city.setText(currentCity);
                        pbLocate.setVisibility(View.GONE);
                    }

                } else if (locateProcess == 3) {
                    locateHint.setText("未定位到城市,请选择");
                    city.setVisibility(View.VISIBLE);
                    city.setText("重新选择");
                    pbLocate.setVisibility(View.GONE);
                }
            } /*else if (viewType == 1) { // 最近访问城市
                convertView = inflater.inflate(R.layout.recent_city, null);
                GridView rencentCity = (GridView) convertView
                        .findViewById(R.id.recent_city);
                rencentCity.setAdapter(new HitCityAdapter(context, this.hisCity));
                rencentCity.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        if (!city_history.get(position).getName().equals("重庆市")) {
                            showErrorCityDialog();
                            return;
                        }
                        //最近
                        //                        SPUtils.put(CitySearchActivity.this, "cityName", city_history.get
                        // (position).getName());
                        //                        SPUtils.put(CitySearchActivity.this, "cityID", city_history.get
                        // (position).getCityID());
                        //                        //点击搜索结果传值给首页定位结果
                        //                        EventBus.getDefault().post(city_history.get(position).getName());
                        finish();
                    }
                });
                TextView recentHint = (TextView) convertView
                        .findViewById(R.id.recentHint);
                recentHint.setText("最近访问的城市");
            } */else if (viewType == 1) {
                convertView = inflater.inflate(R.layout.recent_city, null);
                GridView hotCity = (GridView) convertView
                        .findViewById(R.id.recent_city);
                hotCity.setOnItemClickListener((parent1, view, position1, id) -> {
                    //点击热门城市
                    InsertCity(city_hot.get(position1).getName());
                    finish();
                });
                hotCity.setAdapter(new HotCityAdapter(context, this.hotList));
                TextView hotHint = (TextView) convertView
                        .findViewById(R.id.recentHint);
                hotHint.setText("热门城市");
            } else if (viewType == 2) {
                convertView = inflater.inflate(R.layout.total_item, null);
            } else {
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.citysearchlist_item, null);
                    holder = new ViewHolder();
                    holder.alpha = (TextView) convertView
                            .findViewById(R.id.alpha);
                    holder.name = (TextView) convertView
                            .findViewById(R.id.name);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                if (position >= 1) {
                    holder.name.setText(list.get(position).getName());
                    String currentStr = getAlpha(list.get(position).getPinyi());
                    String previewStr = (position - 1) >= 0 ? getAlpha(list
                            .get(position - 1).getPinyi()) : " ";
                    if (!previewStr.equals(currentStr)) {
                        holder.alpha.setVisibility(View.VISIBLE);
                        holder.alpha.setText(currentStr);
                    } else {
                        holder.alpha.setVisibility(View.GONE);
                    }
                }
            }
            return convertView;
        }

        private class ViewHolder {
            TextView alpha; // 首字母标题
            TextView name; // 城市名字
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    class HotCityAdapter extends BaseAdapter {
        private Context        context;
        private LayoutInflater inflater;
        private List<City>     hotCitys;

        public HotCityAdapter(Context context, List<City> hotCitys) {
            this.context = context;
            inflater = LayoutInflater.from(this.context);
            this.hotCitys = hotCitys;
        }

        @Override
        public int getCount() {
            return hotCitys.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.item_city, null);
            TextView city = (TextView) convertView.findViewById(R.id.city);
            city.setText(hotCitys.get(position).getName());
            return convertView;
        }
    }

    class HitCityAdapter extends BaseAdapter {
        private Context        context;
        private LayoutInflater inflater;
        private List<City>     hotCitys;

        public HitCityAdapter(Context context, List<City> hotCitys) {
            this.context = context;
            inflater = LayoutInflater.from(this.context);
            this.hotCitys = hotCitys;
        }

        @Override
        public int getCount() {
            return hotCitys.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.item_city, null);
            TextView city = (TextView) convertView.findViewById(R.id.city);
            if (hotCitys.get(position).getName().length() > 3) {
                city.setText(hotCitys.get(position).getName().substring(0, 3) + "...");
            } else {
                city.setText(hotCitys.get(position).getName());
            }
            return convertView;
        }
    }

    private boolean mReady;

    // 初始化汉语拼音首字母弹出提示框
    private void initOverlay() {
        mReady = true;
        LayoutInflater inflater = LayoutInflater.from(this);
        overlay = (TextView) inflater.inflate(R.layout.overlay, null);
        overlay.setVisibility(View.INVISIBLE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        WindowManager windowManager = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(overlay, lp);
    }

    private boolean isScroll = false;

    private class LetterListViewListener implements MyLetterListView.OnTouchingLetterChangedListener {
        @Override
        public void onTouchingLetterChanged(final String s) {
            isScroll = false;
            if (alphaIndexer.get(s) != null) {
                int position = alphaIndexer.get(s);
                personList.setSelection(position);
                overlay.setText(s);
                overlay.setVisibility(View.VISIBLE);
                handler.removeCallbacks(overlayThread);
                // 延迟一秒后执行，让overlay为不可见
                handler.postDelayed(overlayThread, 1000);
            }
        }
    }

    // 设置overlay不可见
    private class OverlayThread implements Runnable {
        @Override
        public void run() {
            overlay.setVisibility(View.GONE);
        }
    }

    // 获得汉语拼音首字母
    private String getAlpha(String str) {
        if (str == null) {
            return "#";
        }
        if (str.trim().length() == 0) {
            return "#";
        }
        char c = str.trim().substring(0, 1).charAt(0);
        // 正则表达式，判断首字母是否是英文字母
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(c + "").matches()) {
            return (c + "").toUpperCase();
        } else if (str.equals("0")) {
            return "定位";
        }/* else if (str.equals("1")) {
            return "最近";
        }*/ else if (str.equals("1")) {
            return "热门";
        } else if (str.equals("2")) {
            return "全部";
        } else {
            return "#";
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_TOUCH_SCROLL
                || scrollState == SCROLL_STATE_FLING) {
            isScroll = true;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        if (!isScroll) {
            return;
        }

        if (mReady) {
            String text;
            String name = allCity_lists.get(firstVisibleItem).getName();
            String pinyin = allCity_lists.get(firstVisibleItem).getPinyi();
            if (firstVisibleItem < 4) {
                text = name;
            } else {
                text = PingYinUtil.converterToFirstSpell(pinyin)
                        .substring(0, 1).toUpperCase();
            }
            overlay.setText(text);
            overlay.setVisibility(View.VISIBLE);
            handler.removeCallbacks(overlayThread);
            // 延迟一秒后执行，让overlay为不可见
            handler.postDelayed(overlayThread, 1000);
        }
    }
}