package com.mmy.maimaiyun.model.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.CommentBean;
import com.mmy.maimaiyun.popup.ReviewPicPopup;
import com.mmy.maimaiyun.utils.UIUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 11:56
 * @描述 TODO
 */

public class AllCommentAdapter extends BaseRecyclerViewAdapter {

    private final ReviewPicPopup mReviewPicPopup;
    private List<CommentBean> mData;
    private List<CommentBean> mDataCopy;
    private Context           mContext;


    public AllCommentAdapter(Context context) {
        super(context);
        mContext = context;
        mReviewPicPopup = new ReviewPicPopup(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_all_comment, viewGroup,false);
        ViewGroup root = (ViewGroup) view;
        LinearLayout layout = new LinearLayout(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(params);
        root.addView(layout);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setId(R.id.container);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int i) {
        CommentBean bean = mData.get(i);
        holder.setStr2TV(R.id.name,bean.nickname);
        holder.setStr2TV(R.id.time,bean.addtime);
        holder.setStr2TV(R.id.content,bean.content);
        ImageView imageView = (ImageView) holder.findView(R.id.icon);
        showCommentPics((LinearLayout) holder.findView(R.id.pic_container),bean.pic);
        Picasso.with(mContext).load(bean.head_pic).placeholder(R.mipmap.icon_man).error(R.mipmap.icon_man).into(imageView);
//        if (bean.reply_content!=null){
//            LinearLayout container = (LinearLayout) holder.findView(R.id.container);
//            container.removeAllViews();
//            for (CommentBean.ReplyContentBean contentBean : bean.reply_content) {
//                View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_all_comment, container,false);
//                container.addView(view);
//                TextView name = (TextView) view.findViewById(R.id.name);
//                TextView time = (TextView) view.findViewById(R.id.time);
//                TextView content = (TextView) view.findViewById(R.id.content);
//                ImageView icon = (ImageView) view.findViewById(R.id.icon);
//                name.setText("匿名");
//                time.setText(contentBean.addtime);
//                content.setText(contentBean.content);
////                Picasso.with(mContext).load(contentBean.).error(R.mipmap.icon_man).into(imageView);
//            }
//        }
    }

    View.OnClickListener mReviewPicListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = (String) v.getTag();
            if (url != null)
                mReviewPicPopup.review(url, v);
        }
    };
    private void showCommentPics(LinearLayout view, List<String> pic) {
        int dp40 = UIUtil.dp2px(mContext, 80);
        int dp5 = UIUtil.dp2px(mContext, 5);
        view.removeAllViews();
        if (pic != null) {
            for (String url : pic) {
                ImageView imageView = new ImageView(mContext);
                view.addView(imageView);
                imageView.setPadding(dp5, dp5, dp5, dp5);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(dp40, dp40));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setTag(url);
                imageView.setOnClickListener(mReviewPicListener);
                Picasso.with(mContext)
                        .load(url)
                        .error(R.mipmap.ic_launcher_trad_food)
                        .placeholder(R.mipmap.ic_launcher_trad_food)
                        .into(imageView);
            }
        }
    }


    public void setData(List<CommentBean> data) {
        mData = data;
        mDataCopy = data;
        notifyDataSetChanged();
    }

    public void select(int type){
        if (mData==null) return;
        ArrayList<CommentBean> lista = new ArrayList<>();//好评
        ArrayList<CommentBean> listb = new ArrayList<>();//中评
        ArrayList<CommentBean> listc = new ArrayList<>();//差评
        for (CommentBean bean : mDataCopy) {
            switch (Integer.parseInt(bean.star)) {
                case 0:
                case 1:
                    listc.add(bean);
                    break;
                case 2:
                    listb.add(bean);
                    break;
                case 3:
                case 4:
                case 5:
                    lista.add(bean);
                    break;
            }
        }
        switch (type){
            case 0:
                mData = mDataCopy;
                break;
            case 1:
                mData = lista;
                break;
            case 2:
                mData = listb;
                break;
            case 3:
                mData = listc;
                break;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
