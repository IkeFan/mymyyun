package com.mmy.maimaiyun.model.personal.presenter;

import android.database.Cursor;

import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.customview.LoadingSmartLayout;
import com.mmy.maimaiyun.db.DaoSession;
import com.mmy.maimaiyun.db.RecordBean;
import com.mmy.maimaiyun.db.RecordBeanDao;
import com.mmy.maimaiyun.model.main.ui.activity.GoodInfoActivity;
import com.mmy.maimaiyun.model.personal.view.BrowseHistoryView;

import java.util.ArrayList;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 9:48
 * @描述 TODO
 */

public class BrowseHistoryPresenter extends BasePresenter<BrowseHistoryView> {
    private BrowseHistoryView mView;

    @Inject
    DaoSession mDaoSession;
    private ArrayList<RecordBean> mBeen;

    @Inject
    public BrowseHistoryPresenter(BrowseHistoryView view) {
        mView = view;
    }

    public void loadData() {
        RecordBeanDao dao = mDaoSession.getRecordBeanDao();
        mBeen = new ArrayList<>();
        Cursor cursor = dao.getDatabase().rawQuery("select * from record_bean t group by t.good_id order by t" +
                ".record_time desc", null);
        while (cursor.moveToNext()) {
            RecordBean b = new RecordBean(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6));
            mBeen.add(b);
        }
        LoadingSmartLayout layout = mView.findLoadingSmartLayout();
        if (mBeen.isEmpty()) {
            layout.switchStatus(LoadingSmartLayout.Status.EMPTY);
        } else {
            layout.switchStatus(LoadingSmartLayout.Status.SUCCESS);
        }
        mView.refreshList(mBeen);
    }

    //清空记录
    public void clear() {
        mDaoSession.getRecordBeanDao().deleteAll();
        loadData();
    }

    public void openGoodInfo(int position) {
        RecordBean bean = mBeen.get(position);
        mView.openActivity(GoodInfoActivity.class, "id=" + bean.getGood_id());
    }
}
