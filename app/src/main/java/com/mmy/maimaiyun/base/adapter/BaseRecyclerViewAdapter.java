package com.mmy.maimaiyun.base.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.helper.CircleTransform;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 添加了监听器的Adapter
 *
 * @author mark
 */
public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseRecyclerViewAdapter
        .BaseRecyclerViewHolder> {

    private Context mContext;

    public BaseRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemFocusListener {
        void onItemFocus(View view, boolean hasFocus, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }


    private OnItemClickListener     onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private OnItemFocusListener     onItemFocusListener;
    private List<OnItemClickListener> mClickListeners = new ArrayList<>();

    @Deprecated
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Deprecated
    public void setOnItemFocusListener(OnItemFocusListener onItemFocusListener) {
        this.onItemFocusListener = onItemFocusListener;
    }

    public void addOnItemCliclListener(OnItemClickListener listener){
        mClickListeners.add(listener);
    }

    @Deprecated
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View
            .OnLongClickListener, View.OnFocusChangeListener {
        protected View mView;

        public BaseRecyclerViewHolder(View view) {
            super(view);
            mView = view;
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            view.setOnFocusChangeListener(this);
        }

        public View getRootView() {
            return mView;
        }

        public View findView(@IdRes int id) {
            return mView.findViewById(id);
        }

        //设置文本
        public void setStr2TV(@IdRes int id, String text) {
            TextView textView = (TextView) mView.findViewById(id);
            textView.setText(text);
        }

        public void setColor2TV(@IdRes int id, @ColorInt int color) {
            TextView textView = (TextView) mView.findViewById(id);
            textView.setTextColor(color);
        }

        //加载圆形图片
        public void setCircleIV(@IdRes int id, @DrawableRes int res) {
            ImageView imageView = (ImageView) mView.findViewById(id);
            Picasso.with(mContext).load(res).transform(new CircleTransform()).into(imageView);
        }

        //设置远程资源到image view
        public void setUrl2IV(@IdRes int id, @DrawableRes int res) {
            ImageView imageView = (ImageView) mView.findViewById(id);
            Picasso.with(mContext).load(res).into(imageView);
        }

        //设置本地资源
        public void setDraw2IV(@IdRes int id, @DrawableRes int res) {
            ImageView imageView = (ImageView) mView.findViewById(id);
            Picasso.with(mContext).load(res).into(imageView);
        }

        //设置本地资源
        public void setBitmap2IV(@IdRes int id, Bitmap bitmap) {
            ImageView imageView = (ImageView) mView.findViewById(id);
            imageView.setImageBitmap(bitmap);
        }

        //设置本地资源
        public void setPath2IV(@IdRes int id, String path) {
            ImageView imageView = (ImageView) mView.findViewById(id);
            setPath2IV(imageView,path);
        }

        public void setPath2TVSize(@IdRes int id, String path,int width,int height){
            ImageView imageView = (ImageView) mView.findViewById(id);
            setImageToSize(height, width, path, imageView);
        }

        public void setPath2IV(ImageView imageView,String path) {
            //获取控件的宽高
            imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    int height = imageView.getHeight();
                    int width = imageView.getWidth();
//                    Log.d("BaseRecyclerViewHolder", "height:" + height);
                    setImageToSize(height, width, path, imageView);
                }
            });
        }

        //清楚图片背景
        public void clearTVBG(@IdRes int id) {
            ImageView imageView = (ImageView) mView.findViewById(id);
            imageView.setImageBitmap(null);
        }

        public void setVisibility(@IdRes int id, int vis) {
            mView.findViewById(id).setVisibility(vis);
        }

        public void setChecked(@IdRes int id, boolean isChecked) {
            CheckBox check = (CheckBox) mView.findViewById(id);
            check.setChecked(isChecked);
        }

        @Override
        public boolean onLongClick(View v) {
            if (onItemLongClickListener != null) {
                onItemLongClickListener.onItemLongClick(v, getAdapterPosition());
            }
            return true;
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getAdapterPosition());
            }
            for (OnItemClickListener listener : mClickListeners) {
                listener.onItemClick(v,getAdapterPosition());
            }
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (onItemFocusListener != null) {
                onItemFocusListener.onItemFocus(v, hasFocus, getAdapterPosition());
            }
        }
    }

    private void setImageToSize(int height, int width, String path, ImageView imageView) {
        if (!TextUtils.isEmpty(path))
            Picasso.with(mContext).
                    load(path).
                    placeholder(R.mipmap.ic_def).
                    resize(width, height).
                    error(R.mipmap.ic_def).
                    into(imageView);
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            if (file.exists())
                Picasso.with(mContext).
                        load(file).
                        placeholder(R.mipmap.ic_def).
                        resize(width, height).
                        error(R.mipmap.ic_def).
                        into(imageView);
        }
    }

}
