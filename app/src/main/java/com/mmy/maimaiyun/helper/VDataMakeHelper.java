package com.mmy.maimaiyun.helper;

import com.mmy.maimaiyun.data.bean.AddAttrsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/8/22 0022 9:31
 * @描述 虚拟数据构件器
 */

public class VDataMakeHelper {

    //分类列表
    public static List<String> getClazzs(){
        ArrayList<String> list = new ArrayList<>();
        list.add("美妆个护");
        list.add("食品饮料");
        list.add("服装箱包");
        list.add("母婴玩具");
        list.add("家居生活");
        list.add("家用电器");
        list.add("蔬果生鲜");
        list.add("日用家纺");
        list.add("手机数码");
        return list;
    }

    //分类详情
    public static List<ClazzInfoBean> getClazzInfo(int clazzID){
        ArrayList<ClazzInfoBean> beens = new ArrayList<>();
//        beens.add(new ClazzInfoBean(R.mipmap.ic_launcher,"面部清洁"));
//        beens.add(new ClazzInfoBean(R.mipmap.ic_launcher,"化妆水"));
//        beens.add(new ClazzInfoBean(R.mipmap.ic_launcher,"乳液"));
//        beens.add(new ClazzInfoBean(R.mipmap.ic_launcher,"面霜"));
//        beens.add(new ClazzInfoBean(R.mipmap.ic_launcher,"面膜"));
//        beens.add(new ClazzInfoBean(R.mipmap.ic_launcher,"眼霜"));
//        beens.add(new ClazzInfoBean(R.mipmap.ic_launcher,"精华精油"));
//        beens.add(new ClazzInfoBean(R.mipmap.ic_launcher,"防嗮"));
//        beens.add(new ClazzInfoBean(R.mipmap.ic_launcher,"洗发护发"));
//        beens.add(new ClazzInfoBean(R.mipmap.ic_launcher,"沐浴清洁"));
//        beens.add(new ClazzInfoBean(R.mipmap.ic_launcher,"口腔护理"));
//        beens.add(new ClazzInfoBean(R.mipmap.ic_launcher,"手足唇护理"));
        return beens;
    }

    public static class ClazzInfoBean {
        public int icon;
        public String name;

        public ClazzInfoBean(int icon, String name) {
            this.icon = icon;
            this.name = name;
        }
    }


    //购物车
    public static List<ShoppingBean> getShoppingData(){
        ArrayList<ShoppingBean> list = new ArrayList<>();
//        list.add(new ShoppingBean(R.mipmap.ic_launcher,"发过原装进口红酒，送醒酒器酒杯罗莎田园干葡萄",9.9f,1));
//        list.add(new ShoppingBean(R.mipmap.ic_launcher,"发过原装进口红酒，送醒酒器酒杯罗莎田园干葡萄",19.9f,2));
//        list.add(new ShoppingBean(R.mipmap.ic_launcher,"发过原装进口红酒，送醒酒器酒杯罗莎田园干葡萄",29.9f,4));
//        list.add(new ShoppingBean(R.mipmap.ic_launcher,"发过原装进口红酒，送醒酒器酒杯罗莎田园干葡萄",5.9f,1));
//        list.add(new ShoppingBean(R.mipmap.ic_launcher,"发过原装进口红酒，送醒酒器酒杯罗莎田园干葡萄",3.9f,4));
//        list.add(new ShoppingBean(R.mipmap.ic_launcher,"发过原装进口红酒，送醒酒器酒杯罗莎田园干葡萄",22.9f,4));
//        list.add(new ShoppingBean(R.mipmap.ic_launcher,"发过原装进口红酒，送醒酒器酒杯罗莎田园干葡萄",7.9f,2));
//        list.add(new ShoppingBean(R.mipmap.ic_launcher,"发过原装进口红酒，送醒酒器酒杯罗莎田园干葡萄",13.9f,4));
//        list.add(new ShoppingBean(R.mipmap.ic_launcher,"发过原装进口红酒，送醒酒器酒杯罗莎田园干葡萄",24.9f,6));
//        list.add(new ShoppingBean(R.mipmap.ic_launcher,"发过原装进口红酒，送醒酒器酒杯罗莎田园干葡萄",15.9f,1));
//        list.add(new ShoppingBean(R.mipmap.ic_launcher,"发过原装进口红酒，送醒酒器酒杯罗莎田园干葡萄",25.9f,1));
//        list.add(new ShoppingBean(R.mipmap.ic_launcher,"发过原装进口红酒，送醒酒器酒杯罗莎田园干葡萄",31.9f,1));
        return list;
    }

    public static class ShoppingBean{
        public int icon;
        public String name;
        public float money;
        public boolean isChecked;//是否选中
        public int count;//商品数量

        public ShoppingBean(int icon, String name, float money,int count) {
            this.icon = icon;
            this.name = name;
            this.money = money;
            this.count = count;
        }
    }

    public static List<String> createStrList(int count){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add("item "+i);
        }
        return list;
    }

    public static List<Integer> createIntList(int count){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(i);
        }
        return list;
    }

    public static List<AddAttrsBean> createAddAttrsList(int count){
        ArrayList<AddAttrsBean> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new AddAttrsBean());
        }
        return list;
    }

}
