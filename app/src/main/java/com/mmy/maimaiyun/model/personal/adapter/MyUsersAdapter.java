package com.mmy.maimaiyun.model.personal.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.UserBean;
import com.mmy.maimaiyun.helper.CircleImageTransformation;
import com.mmy.maimaiyun.utils.StringUtil;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/20 0020 17:55
 * @描述 TODO
 */

public class MyUsersAdapter extends BaseRecyclerViewAdapter {

    private final SimpleDateFormat mFormat;
    private       List<UserBean>   mData;
    private       Context          mContext;

    public MyUsersAdapter(Context context) {
        super(context);
        mContext = context;
        mFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_users, parent, false);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        UserBean bean = mData.get(position);
        String name = TextUtils.isEmpty(bean.nickname) ? StringUtil.getMakePhone(bean.phone) : bean.nickname;
        holder.setStr2TV(R.id.name, name);
        if (bean.reg_time != 0)
            holder.setStr2TV(R.id.register_time, "注册时间：" + mFormat.format(new Date(bean.reg_time * 1000)));
        ImageView img = (ImageView) holder.findView(R.id.icon);
        String imgPic = TextUtils.isEmpty(bean.head_pic) ? bean.headimgurl : bean.head_pic;
        if (!TextUtils.isEmpty(imgPic))
            Picasso.with(mContext).load(imgPic).error(R.mipmap.icon_man)
                    .transform(new CircleImageTransformation()).into(img);
    }

    public void setData(List<UserBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
