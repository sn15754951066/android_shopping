package com.example.android_shopping.ui.topic;

import androidx.fragment.app.Fragment;

import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseFragment;
import com.example.android_shopping.interfaces.home.IHome;
import com.example.android_shopping.module.data.HomeBean;
import com.example.android_shopping.presenter.home.HomePresenter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopicFragment extends BaseFragment<HomePresenter> implements IHome.View {



    @Override
    public void getHomeReturn(HomeBean homeBean) {

    }

    @Override
    public int getLayout() {
        return R.layout.navigation_topic;
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
}