package com.mmy.maimaiyun.model.main.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.ActivitySubCateBean;
import com.mmy.maimaiyun.data.bean.ActvityGoodInfoBean;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 14:39
 * @描述 TODO
 */

public class VolumeAdapter extends BaseRecyclerViewAdapter {

    private List<ActivitySubCateBean<List<ActvityGoodInfoBean>>> mData;
    private Context                                              mContext;
    private OnInnerClickListener mListener;

    public VolumeAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_volume, viewGroup,false);
        RecyclerView list = (RecyclerView) view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        int px = UIUtil.dp2px(mContext, 10);
        list.addItemDecoration(new SpacesItemDecoration(px, px, px, 0));
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int i) {
        RecyclerView list = (RecyclerView) holder.findView(R.id.list);
        VolumeListAdapter adapter = new VolumeListAdapter(mContext);
        list.setAdapter(adapter);
        adapter.setData(mData.get(i).subGoodsInfo);
        adapter.setOnInnerClickListener(new VolumeListAdapter.OnInnerClickListener() {
            @Override
            public void onInnerClick(String id) {
                if (mListener != null) {
                    mListener.onInnerClick(id);
                }
            }
        });
        holder.setPath2IV(R.id.clazz_icon,mData.get(i).cat_pic);
    }

    public void setData(List<ActivitySubCateBean<List<ActvityGoodInfoBean>>> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setOnInnerClickListener(OnInnerClickListener listener){
        mListener = listener;
    }

    public interface OnInnerClickListener{
        void onInnerClick(String id);
    }

}
