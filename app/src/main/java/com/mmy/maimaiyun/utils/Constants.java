package com.mmy.maimaiyun.utils;


import com.mmy.maimaiyun.BuildConfig;

/**
 * @创建者 lucas
 * @创建时间 2017/8/11 0011 10:29
 * @描述 常量映射表
 */

public interface Constants {

    //微信appkey
    String WEIXIN_APPKEY     = BuildConfig.WEIXIN_APPKEY;
    String WECHAT_APP_SECRET = BuildConfig.WECHAT_APP_SECRET;

    //融云
    String RONG_APPKEY = BuildConfig.RONG_APPKEY;
    String RONG_SECRET = BuildConfig.RONG_SECRET;

    //快递100
    String LOG_100_KEY = BuildConfig.LOG_100_KEY;
    String LOG_100_CUSTOMER = BuildConfig.LOG_100_CUSTOMER;

    //默认城市
    //    2002;//深圳
    //    2257;//重庆
    int DEFAULT_CITY_ID = 2002;

    //本地缓存大小
    long CACHE_SIZE = 1024 * 1024 * 10;

    //连接超时时间
    int CONN_TIME_OUT = 15 * 1000;

    //数据读取超时时间
    int READ_TIME_OUT = 15 * 1000;

    //splash_maimaiyun 时间
    int SPLASH_DISPLAY_TIME = 2000;

    //分页-每页item个数
    int LIMIT = 10;

    //一个汉字空格
    String one_space = "&#8195;";

    //半个汉字空格
    String half_space = "&#8194;";

    //是否是首次启动
    String IS_NO_SHOW_LAUNCHER = "is_first_launcher";

    //服务器测试地址 125 136 239 199
    String TEST_HOST  = "http://192.168.2.125";
    String TEST_HOST2 = "http://cs.mymyun.com";

    //服务器地址
    String HOST = BuildConfig.HOST;

    String BASE_TEST_URL = HOST + "/api/";

    //远程默认用户图片
    String IC_DEF_USER = HOST + "Public/res/usered.png";

    //分享链接
    String SHARE_URL      = HOST + "/Wap/Dudu/index/invite_code/";
    String SHARE_URL2     = HOST + "/app/down.html";
    String SHARE_URL3     = "https://www.ct1212.com";
    //分享商品
    String SHARE_GOOD_URL = HOST + "/index.php/Wap/goods/goods_detail/id/";
    //客服
    String CUSTOMER_SERVICE = "http://agent.ct1212.com/im/text/4028838b5ac815e3015ac81645f90000.html";
    //城市表
    String CITYS = "Common/allCities";

    //图片链接前缀
    String IMG_URL = "http://www.ct1212.com/Public/Uploads/";

    //登录接口
    String LOGIN = "login/login";

    //获取微信的token openid
    String GET_TOKEN_OPENID = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WEIXIN_APPKEY +
            "&secret=" + WECHAT_APP_SECRET + "&grant_type=authorization_code";

    //获取物流信息
    String GET_LOG_LIST = "http://poll.kuaidi100.com/poll/query.do";

    //商城头条banner
    String SHOP_BANNER = "http://www.ct1212.com/Public/Uploads/Banner/2017-12-06/5a279c97a5ed5.jpg";

    //获取微信用户个人信息
    String WX_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?";

    //即时通讯获取用户token
    String MSG_USER_TOKEN = "http://api.cn.ronghub.com/user/getToken.json";

    //绑定手机
    String BIND_PHONE = "login/binding";

    //三方登录
    String THIRD_PARTY = "login/thirdParty";

    //send code
    String SEND_CODE = "login/sendCode";

    //注册
    String REGISTER = "login/register";

    //忘记密码
    String FORGET = "login/reSetPassword";

    //BANNER
    String BANNER = "index/banneres";

    //新闻头条
    String NEWS_HEAD = "index/news";

    //推荐
    String RECOMMEND = "index/recommend";

    //精品
    String BEST = "index/best";

    //分类
    String CLASSITY = "category/cate";

    //分类对应的数据
    String CLASS_INFO = "category/cateChild";

    //热卖商品
    String GOODS_HOT = "goods/hot";

    //热卖商品-为你推荐
    String GOODS_HOT_RECOMMEMD = "goods/recommend";

    //海外购-分类
    String OVERSEAS_GOODS_CLASS = "oversea/overSea";

    //海外购-商品
    String OVERSEAS_GOOD = "oversea/overSeaGoods";

    //精品列表页
    String GOODS_BEST = "goods/best";

    //精品列表也的分类
    String GOODS_BESTCAT = "goods/bestCat";

    //商品详情
    String GOODS_DETAIL = "goods/goodsContent";

    //商品详情价格联动
    String PRICE_LINKAGE = "goods/get_price";

    //商品详情轮播
    String GOODS_DETAIL_BANNER = "goods/pic";

    //商品属性
    String GOODS_ATTR = "goods/attr";

    //添加购物中
    String ADD_SHOP_CAR = "cart/add";

    //购物车列表
    String SHOP_CAR_LIST = "cart/index";

    //购物车删除
    String SHOP_CAR_DELETE = "cart/del";

    //修改购物车商品数量
    String CHANGE_SHOP_COUNT = "cart/edit";

    //立即购买-单个
    String CONF_ORDER = "cart/gobuy";

    //立即购买-多个
    String CONF_ORDERS = "cart/settlement";

    //提交订单
    String SUBMIT_ORDER = "pay/sub_order";

    // 提交订单
    String SUB_ORDER = "order/sub_order";

    //添加收货地址
    String ADD_NEWS_ADDRESS = "address/add";

    //修改地址
    String MODIF_ADDRESS = "address/editAddress";

    //地址列表
    String ADDRESS_LIST = "address/adressAdminister";

    //修改默认
    String MIDF_DEFAULT_ADDRESS = "address/setdefault";

    //删除地址
    String DEL_ADDRESS = "address/del";

    //评论
    String COMMENT = "goods/goodsComment";

    //商城头条分类
    String GOODS_NEWS_CLASS = "news/cate";

    //商城头条列表
    String GOODS_NEWS_LIST = "news/article";

    //天天特价-分类
    String SPECIAL_CLASS = "activity/special";

    //天天特价
    String SPECIAL_ITEM = "activity/specialGoods";

    //限时特卖
    String TIME_LIMIT = "flashsale/index";

    //订单列表
    String ORDER_LIST = "order/index";

    //取消订单与删除订单
    String DEL_ORDER = "order/cancel";

    //确认收货
    String CONFIRM_ORDER = "order/confirm";

    //账单
    String BILL = "member/member_account";

    //获取订单信息-微信
    String GET_WX_PAY_INFO = "WxPay/wxpay";

    //获取订单信息-支付宝
    String GET_ALI_PAY_INFO = "Alipay/alipay";

    //获取订单信息-余额
    String GET_BALANCE = "Yepay/pay";

    //量贩优选
    String VOLUME_GASTRONOMY = "Volume/gastronomy";

    //分类详细
    String CLASSITY_INFO = "goods/cat_goods";

    //查询支付状态
    String CHECK_PAY_STATUS = "pay/get_order";

    //添加收藏
    String ADD_COLLECT = "member/addcollect";

    //收藏列表
    String COLLECT_LIST = "member/collectlist";

    //批量取消关注
    String DEL_COLLECT = "member/delcollect";

    //修改头像
    String MIDF_HEAD_IMG = "member/modifyPic";

    //修改个人信息
    String MIDF_MEMBER_INFO = "member/modify";

    //加盟
    String INJOIN = "joining/partner";

    //版本更新
    String VERSION = "version/latest";

    //活动商品
    String ACTIVITY_ID = " IndexController/everyDayFlash";
    String ACTIVITY_GOODS = "Activity/activityAndGoodsInfo";

    //轮播
    String BANNERS = "banner/banneres";

    //分公司申请
    String BRANCH_OFFICE = "joining/companyApplication";

    //反馈
    String FEEDBACK = "feedback/Feedback";

    //优惠券列表
    String COUPON_LIST = "coupon/coupon";

    //我的会员
    String MY_USER = "member/leader";

    //修改密码
    String RESET_PWD = "login/RetPassword";

    //搜索
    String SEARCH = "index/search";

    //获取银行卡列表
    String BANK_LIST = "bank/cardList";

    //添加银行卡
    String ADD_BANK_CAR = "bank/addBankCard";

    //删除银行卡
    String DEL_BANK_CAR = "bank/delBankCard";

    //分销订单
    String DIST_ORDER = "member/distInfo";

    //提现
    String WITHDRAW = "member/withdraw";

    //获取用户个人信息
    String INFROMATION = "member/infromation";

    //商铺详情
    String SHOP_INFO = "Shop/getShopInfo";


    //售后/退款
    String REFUND = "Order/afterSales";

    //评价
    String GOOD_COMMENT = "Order/comment";

    //领取优惠券
    String RECEIVE_COUPON = "goods/getcoupon";

    //嘟嘟卡
    String DD_CARD = "member/duducard";

    //首页抢购
    String HOME_EVERY = "index/everyDayFlash";

    //首页特惠
    String HOME_ONLY_NEW = "index/onlyNewFlash";

    //新选商品
    String NEW_GOODS = "goods/getGoodsByParam";

    //退款
    String REFUND_LIST = "order/getAftersalesOrder";

    //充值
    String MEMBER_RECHARGE = "member/recharge";

    //商铺
    String MAIN_SHOP = "shop/index";

    //实名认证
    String AUTH = "member/audit";
}
