package com.mmy.maimaiyun.model.main.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.GoodNewsClassBean;
import com.mmy.maimaiyun.data.bean.GoodNewsItemBean;
import com.mmy.maimaiyun.utils.Constants;
import com.mmy.maimaiyun.utils.TimeUtil;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/4 0004 9:58
 * @描述 TODO
 */

public class GoodNewsAdapter extends BaseRecyclerViewAdapter {
    private List<GoodNewsItemBean> mData;
    private GoodNewsClassBean mClassBean;
    private Context                mContext;
    public static final int HEAD        = 0;
    public static final int NORMAL_ITEM = 1;

    public GoodNewsAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = viewType == HEAD ? R.layout.item_good_news_pager : R.layout.item_good_news;
        View inflate = LayoutInflater.from(parent.getContext()).inflate(layout, null);
        return new GoodNewsHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        if (getItemViewType(position) == NORMAL_ITEM) {
            GoodNewsItemBean bean = mData.get(position-1);
            holder.setStr2TV(R.id.name, bean.title);
            holder.setStr2TV(R.id.content, bean.description);
            TimeUtil util = new TimeUtil();
            String timeDifference = util.getTimeDifference(bean.publish_time*1000, System.currentTimeMillis());
            holder.setStr2TV(R.id.time, timeDifference);
            if (!TextUtils.isEmpty(bean.thumb))
                holder.setPath2IV(R.id.icon, bean.thumb);
        }
        if (getItemViewType(position) == HEAD) {
//            holder.setPath2IV(R.id.good_news_img,mClassBean.);
            ImageView view = (ImageView) holder.findView(R.id.good_news_img);
            Picasso.with(mContext).load(Constants.SHOP_BANNER).into(view);
            holder.setStr2TV(R.id.class_title,mClassBean.cat_name);
        }
    }

    public void setData(List<GoodNewsItemBean> data, GoodNewsClassBean classBean) {
        mData = data;
        mClassBean = classBean;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEAD;
        return NORMAL_ITEM;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size() + 1;
    }

    public class GoodNewsHolder extends BaseRecyclerViewHolder {


        public GoodNewsHolder(View view) {
            super(view);
        }

    }
}
