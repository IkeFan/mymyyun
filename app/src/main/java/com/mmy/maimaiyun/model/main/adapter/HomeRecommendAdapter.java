package com.mmy.maimaiyun.model.main.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.blankj.utilcode.util.ScreenUtils;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.adapter.BaseQuickAdapter;
import com.mmy.maimaiyun.adapter.BaseViewHolder;
import com.mmy.maimaiyun.data.bean.RecommendBean;
import com.mmy.maimaiyun.utils.UIUtil;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/29 0029 11:12
 * @描述 TODO
 */

public class HomeRecommendAdapter extends BaseQuickAdapter<RecommendBean, BaseViewHolder> {
    private       List<RecommendBean> mData;
    private final int                 mH;
    private       Context             mContext;

    public HomeRecommendAdapter(int layoutResId, Context context, @Nullable List<RecommendBean> data) {
        super(layoutResId, data);
        mContext = context;
        int px = UIUtil.dp2px(context, 3);
        mH = ScreenUtils.getScreenWidth() / 2 - px * 2;
    }

    //    @Override
    //    public BaseRecyclerViewAdapter.BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    //        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_recommend, parent, false);
    //        //调整图片的高度
    //        View viewById = view.findViewById(R.id.icon);
    //        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mH);
    //        viewById.setLayoutParams(params);
    //        return new BaseRecyclerViewAdapter.BaseRecyclerViewHolder(view);
    //    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendBean item) {
        ImageView logo = helper.getView(R.id.icon);
        if (!TextUtils.isEmpty(item.logo))
            Picasso.with(mContext).
                    load(item.logo).
                    placeholder(R.mipmap.ic_def).
                    error(R.mipmap.ic_def).
                    into(logo);
        helper.setText(R.id.name, item.goods_name);
        String money = TextUtils.isEmpty(item.promote_price) ? item.shop_price : item.promote_price;
        helper.setText(R.id.price, "￥" + money);
    }

}
