package com.mmy.maimaiyun.model.personal.presenter;

import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.model.personal.view.FindSourceOfGoodsView;

import java.util.ArrayList;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/1 0001 11:18
 * @描述 TODO
 */

public class FindSourceOfGoodsPresenter extends BasePresenter<FindSourceOfGoodsView> {
    private FindSourceOfGoodsView mView;


    @Inject
    public FindSourceOfGoodsPresenter(FindSourceOfGoodsView view) {
        mView = view;
    }

    public void loadTabsData(){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add("TAB "+i);
        }
        mView.refreshTabs(list);
    }

    public void loadGoodsData(){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add(i);
        }
        mView.refreshGoods(list);
    }

}
