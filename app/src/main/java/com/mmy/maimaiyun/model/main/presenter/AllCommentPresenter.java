package com.mmy.maimaiyun.model.main.presenter;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.CommentBean;
import com.mmy.maimaiyun.model.main.model.GoodInfoModel;
import com.mmy.maimaiyun.model.main.view.AllCommentView;
import com.mmy.maimaiyun.utils.Constants;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 11:51
 * @描述 TODO
 */
public class AllCommentPresenter extends BasePresenter<AllCommentView> {
    private AllCommentView mView;

    @Inject
    GoodInfoModel mModel;

    @Inject
    public AllCommentPresenter(AllCommentView view) {
        mView = view;
    }

    public void loadData(String goods_id, boolean isShowAll){
        String limit = Constants.LIMIT + "";
        String pageNum = pageCount++ + "";
        String reply = 1 + "";
        if (isShowAll) {
            limit = "";
            pageNum = "";
        }
        mModel.getComment(new OnLoadDataListener<BaseBean<List<CommentBean>>>() {
            @Override
            public void onResponse(String body, BaseBean<List<CommentBean>> data) {
                if (data.status == 1)
                    mView.refreshList(data.data);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted() {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<List<CommentBean>>>() {
                }.getType();
            }
        }, mView.getUserBean().token, goods_id, null, reply, limit, pageNum);
    }

    int pageCount = 1;


}
