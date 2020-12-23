package com.example.android_shopping.ui.sort;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.android_shopping.MainActivity;
import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseFragment;
import com.example.android_shopping.interfaces.home.IHome;
import com.example.android_shopping.module.data.HomeBean;
import com.example.android_shopping.presenter.home.HomePresenter;
import com.example.android_shopping.ui.sort.category.CategoryFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.QTabView;

public class SortFragment extends BaseFragment<HomePresenter> implements IHome.View {


    @BindView(R.id.tab)
    VerticalTabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private CategoryFragment categoryFragment;

    @Override
    public int getLayout() {
        return R.layout.navigation_sort;
    }

    @Override
    public void initView() {

    }

    @Override
    public HomePresenter createPersenter() {
        return new HomePresenter( this );
    }

    @Override
    public void initData() {
        persenter.getHome();


    }

    @Override
    public void getHomeReturn(HomeBean homeBean) {
        List<HomeBean.DataBean.CategoryListBean> categoryList = homeBean.getData().getCategoryList();

        ArrayList<Fragment> fragments = new ArrayList<>();
//传值
        for (int i = 0; i < categoryList.size(); i++) {

            categoryFragment = new CategoryFragment();

            //传值
            int id = categoryList.get( i ).getId();
            Bundle bundle = new Bundle();
            bundle.putInt( "id", id );
            categoryFragment.setArguments( bundle );
            fragments.add( categoryFragment );

        }

        vp.setAdapter( new FragmentStatePagerAdapter( getChildFragmentManager() ) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get( position );
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {

                return categoryList.get( position ).getName();
            }
        } );

        tab.setupWithViewPager( vp );


    }
}