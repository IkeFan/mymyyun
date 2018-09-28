package com.mmy.maimaiyun.model.personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/8/31 0031 14:39
 * @描述 TODO
 */

public class PhotoUploadAdapter extends BaseRecyclerViewAdapter {

    private List<PhotoBean> mData;
    private boolean         mIsShow;

    public PhotoUploadAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, null);
        return new BaseRecyclerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        if (position == mData.size()) {
            holder.clearTVBG(R.id.img);
            holder.setVisibility(R.id.check, View.GONE);
        } else {
            holder.setPath2IV(R.id.img, mData.get(position).path);
            CheckBox checkBox = (CheckBox) holder.getRootView().findViewById(R.id.check);
            checkBox.setChecked(mData.get(position).isDelete);
            holder.setVisibility(R.id.check, mIsShow ? View.VISIBLE : View.GONE);
        }
    }

    public void setData(List<PhotoBean> data) {
        mData = data;
        notifyDataSetChanged();
    }


    public void isShowDeleted(boolean isShow) {
        mIsShow = isShow;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size() + 1;
    }

    //移除选中想
    public List<PhotoBean> delete() {
        Iterator<PhotoBean> iterator = mData.iterator();
        while (iterator.hasNext()) {
            PhotoBean bean = iterator.next();
            if (bean.isDelete) {
                iterator.remove();   //注意这个地方
            }
        }
        notifyDataSetChanged();
        return mData;
    }

    public static class PhotoBean implements Serializable {
        public boolean isDelete;
        public String  path;
        public String  msg;

        public PhotoBean(String path) {
            this.path = path;
        }
    }
}
