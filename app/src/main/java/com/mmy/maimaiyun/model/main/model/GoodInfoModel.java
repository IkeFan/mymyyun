package com.mmy.maimaiyun.model.main.model;

import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BaseModel;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * @创建者 lucas
 * @创建时间 2017/9/5 0005 11:16
 * @描述 TODO
 */

public class GoodInfoModel extends BaseModel {
    @Inject
    public GoodInfoModel() {
    }

    public void loadGoodsDetailData(OnLoadDataListener listener, String token, String member_id, int id) {
        Call<ResponseBody> cal = mApi.getGoodsDetail(token, member_id, id);
        wrapRequest(listener, cal);
    }

    public void getNewPrice(OnLoadDataListener listener,String token,String goods_id,String attrs){
        Call<ResponseBody> call = mApi.getNewPrice(token, goods_id, attrs);
        wrapRequest(listener,call);
    }

    public void loadBanner(OnLoadDataListener listener, String token, int id) {
        Call<ResponseBody> call = mApi.getGoodsDetailBanner(token, id);
        wrapRequest(listener, call);
    }

    public void loadGoodsAttr(OnLoadDataListener listener, String token, String goods_id) {
        Call<ResponseBody> call = mApi.getGoodsAttr(token, goods_id);
        wrapRequest(listener, call);
    }

    public void add2ShopCar(OnLoadDataListener listener, HashMap<String, String> data) {
        HashMap<String, String> map = new HashMap<>();
        String[] split = data.get("attr").split(",");
        int index =0;
        for (String s : split) {
            map.put("attr["+(index++)+"]",s);
        }
        Call<ResponseBody> call = mApi.add2ShopCar(data.get("token"), data.get("member_id"), data.get("goods_id"),
                                                   map, data.get("goods_number"));
        wrapRequest(listener, call);
    }

    public void getComment(OnLoadDataListener listener, String token, String goods_id, String type, String isReply, String limit
            , String pageNum) {
        Call<ResponseBody> call = mApi.getComment(token, goods_id, type, isReply, limit, pageNum);
        wrapRequest(listener, call);
    }

    public void addCollect(OnLoadDataListener listener, String ton, String member_id, String goods_id) {
        Call<ResponseBody> call = mApi.addCollect(ton, member_id, goods_id);
        wrapRequest(listener, call);
    }

    public void receiveCoupon(OnLoadDataListener listener,String token,String coupon_id,String member_id){
        Call<ResponseBody> call = mApi.receiveCoupon(member_id, coupon_id, token);
        wrapRequest(listener,call);
    }
}
