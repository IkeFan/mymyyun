package com.mmy.maimaiyun.model.shopping.view;

import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.AddressItemBean;
import com.mmy.maimaiyun.data.bean.ConfOrdersBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/10/6 0006 15:08
 * @描述 TODO
 */

public interface ConfOrderView extends IView {
//    void refreshOrder(ConfOrdersBean.ShopBean good);
    void showAddNewAddress(boolean isShow);
    void showOrderList(List<ConfOrdersBean.ShopBean> data);
    void showDefaultAddress(BaseBean<List<AddressItemBean>> bean);
    void refreshAddressInfo(AddressItemBean bean);
    void checkDudu(int shopPosition, boolean duducardInfo);
}
