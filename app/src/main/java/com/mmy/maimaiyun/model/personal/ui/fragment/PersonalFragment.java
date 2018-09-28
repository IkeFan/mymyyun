package com.mmy.maimaiyun.model.personal.ui.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.activity.BaseFragment;
import com.mmy.maimaiyun.data.bean.EventBean;
import com.mmy.maimaiyun.data.bean.UserBean;
import com.mmy.maimaiyun.helper.CircleImageTransformation;
import com.mmy.maimaiyun.model.main.ui.activity.MainWebActivity;
import com.mmy.maimaiyun.model.personal.adapter.OrderAdapter;
import com.mmy.maimaiyun.model.personal.ui.activity.AddressManagerActivity;
import com.mmy.maimaiyun.model.personal.ui.activity.AuthActivity;
import com.mmy.maimaiyun.model.personal.ui.activity.BranchOfficeActivity;
import com.mmy.maimaiyun.model.personal.ui.activity.BrowseHistoryActivity;
import com.mmy.maimaiyun.model.personal.ui.activity.CollectionActivity;
import com.mmy.maimaiyun.model.personal.ui.activity.CouponActivity;
import com.mmy.maimaiyun.model.personal.ui.activity.FinanceCenterActivity;
import com.mmy.maimaiyun.model.personal.ui.activity.HelpActivity;
import com.mmy.maimaiyun.model.personal.ui.activity.JoinApplyActivity;
import com.mmy.maimaiyun.model.personal.ui.activity.OrderActivity;
import com.mmy.maimaiyun.model.personal.ui.activity.PersonalInfoActivity;
import com.mmy.maimaiyun.model.personal.ui.activity.RefundListActivity;
import com.mmy.maimaiyun.model.personal.ui.activity.SettingActivity;
import com.mmy.maimaiyun.model.personal.ui.activity.ShopManagerActivity;
import com.mmy.maimaiyun.model.personal.ui.activity.SpreadCenterActivity;
import com.mmy.maimaiyun.popup.QrCodePopupWindow;
import com.mmy.maimaiyun.popup.SelectPopupWindow;
import com.mmy.maimaiyun.utils.Constants;
import com.mmy.maimaiyun.utils.GlobalUtil;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @创建者 lucas
 * @创建时间 2017/8/16 0016 17:59
 * @描述 我的
 */

public class PersonalFragment extends BaseFragment implements SelectPopupWindow.OnItemClickListener {

    @Bind(R.id.my_select)
    LinearLayout mSelect;
    @Bind(R.id.my_type)
    TextView     mType;
    @Bind(R.id.head_img)
    ImageView    mHeadImg;
    @Bind(R.id.username)
    TextView     mUserName;
    @Bind(R.id.money_text)
    TextView     mMoney;
    @Bind(R.id.frozen_money)
    TextView     mFrozenMoney;
    @Bind(R.id.distribut_money)
    TextView     mDistributMoney;
    @Bind(R.id.pay_points)
    TextView     mPayPoints;
    @Bind(R.id.qr_code)
    View         mQrCode;
    @Bind(R.id.auth_status)
    TextView     mAuthStatus;


    private SelectPopupWindow mSelectPopupWindow;
    private QrCodePopupWindow mQrCodePopupWindow;

    @Override
    protected void initView() {
        mSelectPopupWindow = new SelectPopupWindow.Builder(mActivity)
                .addItemText("运营商申请")
                .addItemText("合伙人申请")
                .setOnItemClickListener(this)
                .relationView(mSelect).build();
        //二维码
        String str = Constants.SHARE_URL3 + "?invite_code=" + mActivity.getUserBean().invite_code;
        mQrCodePopupWindow = new QrCodePopupWindow.Builder(mActivity)
                .setHintText(str)
                .setQrText(str)
                .build();
        //        mMoney.setText(getUserBean().member_money + "\n余额");
        //        mFrozenMoney.setText(getUserBean().frozen_money + "\n待返还");
        //        mDistributMoney.setText(getUserBean().distribut_money + "\n已返还");
        //        mPayPoints.setText(getUserBean().pay_points + "\n金豆");
    }

    @Override
    public boolean registerEventBus() {
        return true;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initDagger(AppComponent appComponent) {
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshUserInfoView();
    }

    private void refreshUserInfoView() {
        UserBean userBean = mActivity.getUserBean();
        if (!TextUtils.isEmpty(userBean.headimgurl))
            Picasso.with(mActivity).load(userBean.headimgurl)
                    .placeholder(R.mipmap.touxiang)
                    .error(R.mipmap.touxiang)
                    .transform(new CircleImageTransformation()).into(mHeadImg);
        String nickname = userBean.nickname;
        mUserName.setText(TextUtils.isEmpty(nickname) ? "客户" : nickname);
        mMoney.setText(userBean.member_money + "\n余额");
        mFrozenMoney.setText(getUserBean().frozen_money + "\n冻结");
        mDistributMoney.setText(getUserBean().pay_points + "\n积分");
        mPayPoints.setText(getUserBean().mcdull + "\n金豆");
        //判断是否绑定了嘟嘟卡
        mQrCode.setVisibility(getUserBean().is_dudu != 0 ? View.VISIBLE : View.GONE);
        mAuthStatus.setText(getUserBean().getAuthStatusName());
        mAuthStatus.setTextColor(getResources().getColor(getUserBean().is_audit == 2 ? R.color.colorPrimary : R.color
                .gray_btn_bg_color));
    }

    @OnClick({R.id.my_select, R.id.qr_code, R.id.my_injoin, R.id.head_img, R.id.order
            , R.id.waiting_payment, R.id.waiting_deliver, R.id.waiting_receipt, R.id.waiting_evaluate
            , R.id.refund, R.id.address_manager, R.id.collection, R.id.history, R.id.coupon,
            R.id.shop_manager, R.id.finance, R.id.spread_center, R.id.help, R.id.settings, R.id.service,
            R.id.auth_status})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.auth_status:
                if (getUserBean().is_audit == 0)
                    openActivity(AuthActivity.class);
                break;
            case R.id.service://客服
                openService();
                break;
            case R.id.settings:
                //                startActivity(new Intent(mActivity, CommentActivity.class));
                startActivity(new Intent(mActivity, SettingActivity.class));
                break;
            case R.id.help://反馈与帮助
                startActivity(new Intent(mActivity, HelpActivity.class));
                break;
            case R.id.spread_center://推广中心
                startActivity(new Intent(mActivity, SpreadCenterActivity.class));
                break;
            case R.id.finance://财务中心
                startActivity(new Intent(mActivity, FinanceCenterActivity.class));
                break;
            case R.id.shop_manager://店铺管理
                startActivity(new Intent(mActivity, ShopManagerActivity.class));
                break;
            case R.id.coupon://优惠券
                startActivity(new Intent(mActivity, CouponActivity.class));
                break;
            case R.id.history://我的足迹
                startActivity(new Intent(mActivity, BrowseHistoryActivity.class));
                break;
            case R.id.my_select:
                if (mSelectPopupWindow == null)
                    return;
                if (mSelectPopupWindow.isShowing())
                    mSelectPopupWindow.dismiss();
                else
                    mSelectPopupWindow.showPopup();
                break;
            case R.id.qr_code://二维码
                mQrCodePopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                break;
            case R.id.my_injoin:
                String type = mType.getText().toString().trim();
                Class<? extends BaseActivity> aClass = "运营商申请".equals(type) ? BranchOfficeActivity.class :
                        JoinApplyActivity.class;
                Intent intent = new Intent(mActivity, aClass);
                startActivity(intent);
                break;
            case R.id.head_img://个人信息
                startActivity(new Intent(mActivity, PersonalInfoActivity.class));
                break;
            case R.id.order://订单
                startActivity(new Intent(mActivity, OrderActivity.class));
                break;
            case R.id.waiting_payment:
                openActivity(OrderActivity.class, "type=" + OrderAdapter.PENDING_PAYMENT);
                break;
            case R.id.waiting_deliver:
                openActivity(OrderActivity.class, "type=" + OrderAdapter.SHIPMENT_PENDING);
                break;
            case R.id.waiting_receipt:
                openActivity(OrderActivity.class, "type=" + OrderAdapter.GOODS_RECEIVED);
                break;
            case R.id.waiting_evaluate:
                openActivity(OrderActivity.class, "type=" + OrderAdapter.GRADING);
                break;
            case R.id.address_manager://地址管理
                startActivity(new Intent(mActivity, AddressManagerActivity.class));
                break;
            case R.id.collection://我的收藏
                startActivity(new Intent(mActivity, CollectionActivity.class));
                break;
            case R.id.refund://售后
                startActivity(new Intent(mActivity, RefundListActivity.class));
                break;
        }
    }

    private void openService() {//首先需要构造使用客服者的用户信息
        Intent intent = new Intent(getActivity(), MainWebActivity.class);
        intent.putExtra("url", Constants.CUSTOMER_SERVICE);
        intent.putExtra("title", "客服");
        startActivity(intent);
        //        String nickname = getUserBean().nickname;
        //        CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
        //        CSCustomServiceInfo csInfo = csBuilder.nickName(TextUtils.isEmpty(nickname) ? "游客" : nickname)
        //                .birthday(getUserBean().head_pic)
        //                .build();
        //        /**
        //         * 启动客户服聊天界面。
        //         *
        //         * @param context           应用上下文。
        //         * @param customerServiceId 要与之聊天的客服 Id。
        //         * @param title             聊天的标题，如果传入空值，则默认显示与之聊天的客服名称。
        //         * @param customServiceInfo 当前使用客服者的用户信息。{@link CSCustomServiceInfo}
        //         */
        //        RongIM.getInstance().startCustomerServiceChat(getActivity(), BuildConfig.RONG_SERVICE, "在线客服",
        // csInfo);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventBean event) {
        if (GlobalUtil.REFRESH_USER_INFO.equals(event.getMeg()))
            refreshUserInfoView();
    }

    @Override
    public void onItemClick(View view, String str, int index) {
        mSelectPopupWindow.dismiss();
        mType.setText(str);
    }
}
