package com.mmy.maimaiyun.data.api;


import com.mmy.maimaiyun.utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * @创建者 lucas
 * @创建时间 2017/8/11 0011 16:47
 * @描述 API 接口
 */

public interface ApiService {

    //城市表
    @GET(Constants.CITYS)
    Call<ResponseBody> getCitys();

    //登陆
    @FormUrlEncoded
    @POST(Constants.LOGIN)
    Call<ResponseBody> login(@Field("phone") String username,
                             @Field("password") String pwd);

    //三方登录
    @FormUrlEncoded
    @POST(Constants.THIRD_PARTY)
    Call<ResponseBody> thirdParty(@Field("headimgurl") String headimg,
                                  @Field("openid") String openid,
                                  @Field("nickname") String nickname);

    //获取token openid
    @GET(Constants.GET_TOKEN_OPENID)
    Call<ResponseBody> getTokenOpenid(@Query("code") String code);

    //获取微信用户个人信息
    @GET(Constants.WX_USER_INFO)
    Call<ResponseBody> getWXUserInfo(@Query("access_token") String access_token,
                                     @Query("openid") String openid);

    //获取用户token
    @FormUrlEncoded
    @POST(Constants.MSG_USER_TOKEN)
    Call<ResponseBody> getMsgUserToken(@HeaderMap Map<String, String> headers,
                                       @Field("userId") String userID,
                                       @Field("name") String name,
                                       @Field("portraitUri") String portraitUri);

    //绑定手机
    @FormUrlEncoded
    @POST(Constants.BIND_PHONE)
    Call<ResponseBody> bindPhone(@Field("headimgurl") String headimg,
                                 @Field("openid") String openid,
                                 @Field("nickname") String nickname,
                                 @Field("phone") String phone,
                                 @Field("code") String code);

    //banner
    @FormUrlEncoded
    @POST(Constants.BANNER)
    Call<ResponseBody> getBanner(@Field("token") String token);

    //send code
    @FormUrlEncoded
    @POST(Constants.SEND_CODE)
    Call<ResponseBody> sendCode(@Field("phone") String phone);

    //注册
    @FormUrlEncoded
    @POST(Constants.REGISTER)
    Call<ResponseBody> register(@Field("phone") String phone,
                                @Field("password") String pwd,
                                @Field("sms_code") String code,
                                @Field("type") String type,
                                @Field("invite_code") String invite_code,
                                @Field("must_click") int must_click);

    //忘记密码
    @FormUrlEncoded
    @POST(Constants.FORGET)
    Call<ResponseBody> forget(@Field("phone") String phone,
                              @Field("password") String password,
                              @Field("sms_code") String code);

    //分类
    @FormUrlEncoded
    @POST(Constants.CLASSITY)
    Call<ResponseBody> classs(@Field("token") String token);

    //分类下对应的数据
    @FormUrlEncoded
    @POST(Constants.CLASS_INFO)
    Call<ResponseBody> getClassInfo(@Field("token") String token,
                                    @Field("id") String id);

    //精品
    @FormUrlEncoded
    @POST(Constants.BEST)
    Call<ResponseBody> getBest(@Field("token") String token);


    //推荐
    @FormUrlEncoded
    @POST(Constants.RECOMMEND)
    Call<ResponseBody> getRecommend(@Field("token") String token,@Field("pageNum") int page_count);

    //新闻头条
    @FormUrlEncoded
    @POST(Constants.NEWS_HEAD)
    Call<ResponseBody> getNewsHead(@Field("token") String token);

    //热卖商品
    @FormUrlEncoded
    @POST(Constants.GOODS_HOT)
    Call<ResponseBody> getGoodsNot(@Field("token") String token,
                                   @Field("order") int order,
                                   @Field("pageNum") int pageNum,
                                   @Field("limt") int limit,
                                   @FieldMap HashMap<String, String> attrs);

    //热卖商品-为你推荐
    @FormUrlEncoded
    @POST(Constants.GOODS_HOT_RECOMMEMD)
    Call<ResponseBody> getGoodsHotRecommemd(@Field("token") String token);

    //海外购-分类
    @FormUrlEncoded
    @POST(Constants.OVERSEAS_GOODS_CLASS)
    Call<ResponseBody> getOverseasGoodsClass(@Field("token") String token);

    //海外购-商品
    @FormUrlEncoded
    @POST(Constants.OVERSEAS_GOOD)
    Call<ResponseBody> getOverseasGood(@Field("token") String token, @Field("cat_id") String cat_id);

    //精品列表页
    @FormUrlEncoded
    @POST(Constants.GOODS_BEST)
    Call<ResponseBody> getGoodsBest(@Field("token") String token,
                                    @Field("cat_id") int cat_id,
                                    @Field("order") int order,
                                    @FieldMap HashMap<String, String> attrs);

    //精品列表也的分类
    @FormUrlEncoded
    @POST(Constants.GOODS_BESTCAT)
    Call<ResponseBody> getGoodsBestact(@Field("token") String token);

    //商品详情
    @FormUrlEncoded
    @POST(Constants.GOODS_DETAIL)
    Call<ResponseBody> getGoodsDetail(@Field("token") String token,
                                      @Field("member_id") String member_id,
                                      @Field("id") int id);

    //商品详情价格联动
    @FormUrlEncoded
    @POST(Constants.PRICE_LINKAGE)
    Call<ResponseBody> getNewPrice(@Field("token") String token,
                                   @Field("goods_id") String goods_id,
                                   @Field("goods_attr_id") String goods_attr_id);

    //商品详情banner
    @FormUrlEncoded
    @POST(Constants.GOODS_DETAIL_BANNER)
    Call<ResponseBody> getGoodsDetailBanner(@Field("token") String token,
                                            @Field("goods_id") int id);

    //商品属性
    @FormUrlEncoded
    @POST(Constants.GOODS_ATTR)
    Call<ResponseBody> getGoodsAttr(@Field("token") String token,
                                    @Field("goods_id") String goods_id);

    //添加到购物车
    @FormUrlEncoded
    @POST(Constants.ADD_SHOP_CAR)
    Call<ResponseBody> add2ShopCar(@Field("token") String token,
                                   @Field("member_id") String member_id,
                                   @Field("goods_id") String goods_id,
                                   @FieldMap() HashMap<String, String> attr,
                                   @Field("goods_number") String number);

    //购物车列表
    @FormUrlEncoded
    @POST(Constants.SHOP_CAR_LIST)
    Call<ResponseBody> getShopCarList(@Field("token") String token,
                                      @Field("member_id") String member_id);

    //购物车删除
    @FormUrlEncoded
    @POST(Constants.SHOP_CAR_DELETE)
    Call<ResponseBody> deleteShopCar(@Field("token") String token,
                                     @Field("id") String ids);

    //修改购物车商品数量
    @FormUrlEncoded
    @POST(Constants.CHANGE_SHOP_COUNT)
    Call<ResponseBody> changeShopCount(@Field("token") String token,
                                       @Field("id") String shop_id,
                                       @Field("goods_number") int count);

    //立即购买
    @FormUrlEncoded
    @POST(Constants.CONF_ORDER)
    Call<ResponseBody> getOrderInfo(@Field("token") String token,
                                    @Field("goods_id") String goods_id,
                                    @Field("goods_number") String goods_number,
                                    @Field("goods_attr_id") String goods_attr_id,
                                    @Field("member_id") String member_id,
                                    @Field("address_id") String address_id
    );

    //立即购买-多个
    @FormUrlEncoded
    @POST(Constants.CONF_ORDERS)
    Call<ResponseBody> getConfOrders(@Field("token") String token,
                                     @Field("member_id") String member_id,
                                     @Field("id") String id,
                                     @Field("address_id") String address_id);


    //提交订单
    @FormUrlEncoded
    @POST(Constants.SUB_ORDER)
    Call<ResponseBody> subOrder(@Field("data") String dataJson);

    //添加新地址
    @FormUrlEncoded
    @POST(Constants.ADD_NEWS_ADDRESS)
    Call<ResponseBody> addNewsAddress(@Field("token") String token,
                                      @Field("member_id") String member_id,
                                      @Field("consignee") String consignee,
                                      @Field("mobile") String mobile,
                                      @Field("province") String province,
                                      @Field("city") String city,
                                      @Field("district") String district,
                                      @Field("street") String street,
                                      @Field("address") String address,
                                      @Field("is_default") String is_default
    );

    //修改地址
    @FormUrlEncoded
    @POST(Constants.MODIF_ADDRESS)
    Call<ResponseBody> modifAddress(
            @Field("token") String token,
            @Field("address_id") String address_id,
            @Field("member_id") String member_id,
            @Field("consignee") String consignee,
            @Field("mobile") String mobile,
            @Field("province") String province,
            @Field("city") String city,
            @Field("district") String district,
            @Field("street") String street,
            @Field("address") String address,
            @Field("is_default") String is_default
    );

    //地址列表
    @FormUrlEncoded
    @POST(Constants.ADDRESS_LIST)
    Call<ResponseBody> getAddressList(@Field("token") String token,
                                      @Field("member_id") String member_id);

    //修改默认地址
    @FormUrlEncoded
    @POST(Constants.MIDF_DEFAULT_ADDRESS)
    Call<ResponseBody> midfDefaultAddress(@Field("token") String token,
                                          @Field("member_id") String member_id,
                                          @Field("address_id") String id);

    //删除地址
    @FormUrlEncoded
    @POST(Constants.DEL_ADDRESS)
    Call<ResponseBody> delAddress(@Field("token") String token,
                                  @Field("member_id") String member_id,
                                  @Field("address_id") String address_id);

    //评论
    //参数    object  商品id     isReply  默认不填 不显示回复 1 显示回复  limit 数量 pageNum 页数
    @FormUrlEncoded
    @POST(Constants.COMMENT)
    Call<ResponseBody> getComment(@Field("token") String token,
                                  @Field("object_id") String goods_id,
                                  @Field("type") String type,//-1 回复 0 商品 1 店铺
                                  @Field("isReply") String isReply,
                                  @Field("limit") String limit,
                                  @Field("pageNum") String pageNum);

    //商城头条分类 id = 6 固定值
    @FormUrlEncoded
    @POST(Constants.GOODS_NEWS_CLASS)
    Call<ResponseBody> getGoodsNewsClass(@Field("token") String token,
                                         @Field("id") String id);

    //商城头条列表
    @FormUrlEncoded
    @POST(Constants.GOODS_NEWS_LIST)
    Call<ResponseBody> getGoodsNewsList(@Field("token") String token,
                                        @Field("cat_id") String cat_id);

    //天天特价-分类
    @FormUrlEncoded
    @POST(Constants.SPECIAL_CLASS)
    Call<ResponseBody> getSpecialClass(@Field("token") String token,
                                       @Field("id") int id);

    //天天特价
    @FormUrlEncoded
    @POST(Constants.SPECIAL_ITEM)
    Call<ResponseBody> getSpecialItemData(@Field("token") String token,
                                          @Field("cat_id") String cat_id);

    //限时特卖
    @FormUrlEncoded
    @POST(Constants.TIME_LIMIT)
    Call<ResponseBody> getTimeLimitData(@Field("token") String token,
                                        @Field("type") int type);

    //订单列表
    @FormUrlEncoded
    @POST(Constants.ORDER_LIST)
    Call<ResponseBody> getOrderList(@Field("token") String token,
                                    @Field("member_id") String member_id);

    //取消订单与删除订单
    @FormUrlEncoded
    @POST(Constants.DEL_ORDER)
    Call<ResponseBody> delOrder(@Field("token") String token,
                                @Field("member_id") String member_id,
                                @Field("order_sn") String order_sn,
                                @Field("action") String action);

    //确认收货
    @FormUrlEncoded
    @POST(Constants.CONFIRM_ORDER)
    Call<ResponseBody> confirmOrder(@Field("token") String token,
                                    @Field("member_id") String member_id,
                                    @Field("order_sn") String order_sn);

    //账单
    @FormUrlEncoded
    @POST(Constants.BILL)
    Call<ResponseBody> getBill(@Field("token") String token,
                               @Field("page") int page,
                               @Field("member_id") String member_id);

    //获取订单信息
    @FormUrlEncoded
    @POST(Constants.GET_WX_PAY_INFO)
    Call<ResponseBody> getWXPayInfo(@Field("token") String token,
                                    @Field("order_sn") String order_sn,
                                    @Field("order_amount") String order_amount);

    //获取订单信息
    @FormUrlEncoded
    @POST(Constants.GET_ALI_PAY_INFO)
    Call<ResponseBody> getAliPayInfo(@Field("token") String token,
                                     @Field("order_sn") String order_sn,
                                     @Field("order_amount") String order_amount);

    //获取订单信息
    @FormUrlEncoded
    @POST(Constants.GET_BALANCE)
    Call<ResponseBody> getBalancePayInfo(@Field("token") String token,
                                         @Field("member_id") String member_id,
                                         @Field("order_sn") String order_sn,
                                         @Field("order_amount") String order_amount);

    //量贩优选
    @FormUrlEncoded
    @POST(Constants.VOLUME_GASTRONOMY)
    Call<ResponseBody> getVolumeGastronomy(@Field("token") String token,
                                           @Field("id") int id);

    //分类详细
    @FormUrlEncoded
    @POST(Constants.CLASSITY_INFO)
    Call<ResponseBody> getClassityInfo(@Field("token") String token,
                                       @Field("cat_id") String cat_id);

    //查询支付结果
    @FormUrlEncoded
    @POST(Constants.CHECK_PAY_STATUS)
    Call<ResponseBody> checkPayStatus(@Field("token") String token,
                                      @Field("order_sn") String order_sn);

    //添加收藏
    @FormUrlEncoded
    @POST(Constants.ADD_COLLECT)
    Call<ResponseBody> addCollect(@Field("token") String token,
                                  @Field("member_id") String member_id,
                                  @Field("goods_id") String goods_id);

    //收藏列表
    @FormUrlEncoded
    @POST(Constants.COLLECT_LIST)
    Call<ResponseBody> getCollectList(@Field("token") String token,
                                      @Field("member_id") String member_id);

    //批量取消收藏
    @FormUrlEncoded
    @POST(Constants.DEL_COLLECT)
    Call<ResponseBody> delCollect(@Field("token") String token,
                                  @Field("id") String ids);

    //修改头像
    @Multipart
    @POST(Constants.MIDF_HEAD_IMG)
    Call<ResponseBody> midfHeadImg(@Part List<MultipartBody.Part> data);

    //修改头像
    @FormUrlEncoded
    @POST(Constants.MIDF_MEMBER_INFO)
    Call<ResponseBody> midfMemberInfo(@FieldMap HashMap<String, String> map);

    //加盟
    @Multipart
    @POST(Constants.INJOIN)
    Call<ResponseBody> Injoin(@Part List<MultipartBody.Part> data);

    //分公司申请
    @Multipart
    @POST(Constants.BRANCH_OFFICE)
    Call<ResponseBody> subBranchOffice(@Part List<MultipartBody.Part> data);

    @FormUrlEncoded
    @POST(Constants.VERSION)
    Call<ResponseBody> getVersion(@Field("token") String token);


    //获取活动ID
    @FormUrlEncoded
    @POST(Constants.ACTIVITY_ID)
    Call<ResponseBody> getActivityIDs();

    @FormUrlEncoded
    @POST(Constants.ACTIVITY_GOODS)
    Call<ResponseBody> getActivity(@FieldMap HashMap<String, Object> map);

    //轮播
    @FormUrlEncoded
    @POST(Constants.BANNERS)
    Call<ResponseBody> getBanners(@FieldMap HashMap<String, Object> map);

    //反馈
    @FormUrlEncoded
    @POST(Constants.FEEDBACK)
    Call<ResponseBody> feedback(@Field("token") String token,
                                @Field("member_id") String member_id,
                                @Field("content") String content);

    //优惠券列表
    @FormUrlEncoded
    @POST(Constants.COUPON_LIST)
    Call<ResponseBody> getCouponList(@Field("token") String token,
                                     @Field("member_id") String member_id);

    //我的会员
    @FormUrlEncoded
    @POST(Constants.MY_USER)
    Call<ResponseBody> getMyUser(@Field("token") String token,
                                 @Field("member_id") String member_id,
                                 @Field("level") int level);

    //修改密码
    @FormUrlEncoded
    @POST(Constants.RESET_PWD)
    Call<ResponseBody> resetPwd(@Field("phone") String phone,
                                @Field("password") String password,
                                @Field("newpassword") String newpassword);

    //搜索
    @FormUrlEncoded
    @POST(Constants.SEARCH)
    Call<ResponseBody> search(@Field("search_name") String search_name,
                              @Field("page") int page,
                              @Field("count") int count,
                              @Field("type") int type);

    //获取银行卡列表
    @FormUrlEncoded
    @POST(Constants.BANK_LIST)
    Call<ResponseBody> getBankList(@Field("token") String token,
                                   @Field("member_id") String member_id);

    //添加银行卡
    @FormUrlEncoded
    @POST(Constants.ADD_BANK_CAR)
    Call<ResponseBody> addBankCard(@Field("token") String token,
                                   @Field("member_id") String member_id,
                                   @Field("name") String name,
                                   @Field("id_card") String id_card,
                                   @Field("bank_name") String deposit,
                                   @Field("bank_card") String bank_card);

    //删除银行卡
    @FormUrlEncoded
    @POST(Constants.DEL_BANK_CAR)
    Call<ResponseBody> delBankCard(@Field("token") String token,
                                   @Field("id") String id,
                                   @Field("member_id") String member_id);

    //分销订单 0:冻结，1：发放，2：已失效（商品退货）
    @FormUrlEncoded
    @POST(Constants.DIST_ORDER)
    Call<ResponseBody> getDistOrder(@Field("token") String token,
                                    @Field("member_id") String member_id,
                                    @Field("page") int page,
                                    @Field("status") int type);

    //提现
    @FormUrlEncoded
    @POST(Constants.WITHDRAW)
    Call<ResponseBody> withdraw(@Field("token") String token,
                                @Field("member_id") String member_id,
                                @Field("bank_id") String bank_id,
                                @Field("money") String money);

    //获取用户信息
    @FormUrlEncoded
    @POST(Constants.INFROMATION)
    Call<ResponseBody> refreshUserInfo(@Field("member_id") String member_id);

    //商铺详情
    @FormUrlEncoded
    @POST(Constants.SHOP_INFO)
    Call<ResponseBody> getShopInfo(@Field("token") String token,
                                   @Field("member_id") String member_id);

    //售后/退款
    @Multipart
    @POST(Constants.REFUND)
    Call<ResponseBody> refund(@Part List<MultipartBody.Part> data);

    //商品评价
    @Multipart
    @POST(Constants.GOOD_COMMENT)
    Call<ResponseBody> goodComment(@Part List<MultipartBody.Part> data);

    //领取优惠券
    @FormUrlEncoded
    @POST(Constants.RECEIVE_COUPON)
    Call<ResponseBody> receiveCoupon(@Field("member_id") String member_id,
                                     @Field("coupon_id") String coupon_id,
                                     @Field("token") String token);

    //嘟嘟卡
    @FormUrlEncoded
    @POST(Constants.DD_CARD)
    Call<ResponseBody> getDDCard(@Field("token") String token,
                                 @Field("member_id") String member_id);

    //获取物流信息
    @FormUrlEncoded
    @POST(Constants.GET_LOG_LIST)
    Call<ResponseBody> getLogList(@Field("customer") String customer,
                                  @Field("sign") String sign,
                                  @Field("param") String param);

    //首页抢购
    @GET(Constants.HOME_EVERY)
    Call<ResponseBody> getHomeEvery();

    //首页特惠
    @GET(Constants.HOME_ONLY_NEW)
    Call<ResponseBody> getHomeOnlyNew();

    //新选商品
    @FormUrlEncoded
    @POST(Constants.NEW_GOODS)
    Call<ResponseBody> getNewGoods(@Field("param") String type,@Field("pageNum") int page);

    //退款
    @FormUrlEncoded
    @POST(Constants.REFUND_LIST)
    Call<ResponseBody> getRefundList(@Field("member_id") String member_id,@Field("pageNum") int page,@Field("token") String token);

    //充值
    @FormUrlEncoded
    @POST(Constants.MEMBER_RECHARGE)
    Call<ResponseBody> rechargeMember(@Field("token") String token,
                                      @Field("member_id") String member_id,
                                      @Field("money") String money,
                                      @Field("pay_code") String pay_code);

    //商铺
    @FormUrlEncoded
    @POST(Constants.MAIN_SHOP)
    Call<ResponseBody> getMainShop(@Field("id") String shop_id,@Field("page") int page);

    //实名认证
    @Multipart
    @POST(Constants.AUTH)
    Call<ResponseBody> auth(@Part List<MultipartBody.Part> data);
}
