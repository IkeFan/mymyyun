package com.mmy.maimaiyun.model.main.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.data.bean.BannerBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/7/5 0005 10:19
 * @描述 TODO
 */

public class BannerAdapter extends PagerAdapter {
    private Context          mContext;
    private List<BannerBean> mBanners;

    public BannerAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<BannerBean> banners) {
        mBanners = banners;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mBanners != null) {
            return Integer.MAX_VALUE;
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % mBanners.size();
        ImageView view = new ImageView(mContext);
        view.setImageResource(R.mipmap.banner_error);
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(param);
        if (!TextUtils.isEmpty(mBanners.get(position).logo))
            Picasso.with(mContext).load(mBanners.get(position).logo)
                    .error(R.mipmap.banner_error)
                    .placeholder(R.mipmap.banner_error)
                    .into(view);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}