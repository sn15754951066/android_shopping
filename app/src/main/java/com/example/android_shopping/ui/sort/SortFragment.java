package com.example.android_shopping.ui.sort;

import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseFragment;
import com.example.android_shopping.interfaces.home.IHome;
import com.example.android_shopping.module.data.HomeBean;
import com.example.android_shopping.presenter.home.HomePresenter;

public class SortFragment extends BaseFragment<HomePresenter> implements IHome.View {


    @Override
    public int getLayout() {
        return R.layout.navigation_sort;
    }

    @Override
    public void initView() {

    }

    @Override
    public HomePresenter createPersenter() {
        return null;
    }

    @Override
    public void initData() {

    }

    @Override
    public void getHomeReturn(HomeBean homeBean) {

    }
}