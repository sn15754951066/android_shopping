package com.example.android_shopping.ui.channel;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseActivity;
import com.example.android_shopping.interfaces.tab.ITab;
import com.example.android_shopping.module.data.TabBean;
import com.example.android_shopping.presenter.tab.TabPresenter;
import com.example.android_shopping.ui.homeAdapter.CanSlidingViewpager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ChannelActivity extends BaseActivity<TabPresenter> implements ITab.View {

    private TabLayout mTab;
    private CanSlidingViewpager mMainVp;
    //int caId;

    @Override
    protected int getLayout() {
        return R.layout.activity_channel;
    }

    @Override
    protected TabPresenter createPrenter() {
        return new TabPresenter( this );
    }

    @Override
    protected void initView() {


        mTab = (TabLayout) findViewById( R.id.tab );
        mMainVp = (CanSlidingViewpager) findViewById( R.id.vp_main );
    }

    @Override
    protected void initData() {
        //网络获取
        int isubstring = getIntent().getIntExtra( "isubstring", 0 );
        int caId = getIntent().getIntExtra( "caId", 0 );
        if (caId != 0) {
            //列表数据
            presenter.getTab( caId );
        }
        if (isubstring != 0) {
            presenter.getTab( isubstring );
        }


    }

    @Override
    public void getTabReturn(TabBean tabBean) {

        ArrayList<Fragment> fragments = new ArrayList<>();
        List<TabBean.DataBean.BrotherCategoryBean> brotherCategory = tabBean.getData().getBrotherCategory();
        for (int i = 0; i < brotherCategory.size(); i++) {

            int id = brotherCategory.get( i ).getId();
            String name = brotherCategory.get( i ).getName();
            String front_name = brotherCategory.get( i ).getFront_name();

            //創建fragment
            ChannelFragment channelFragment = new ChannelFragment();

            Bundle bundle = new Bundle();
            bundle.putInt( "id", id );
            bundle.putString( "name", name );
            bundle.putString( "front_name", front_name );

            channelFragment.setArguments( bundle );

            fragments.add( channelFragment );
        }

        mMainVp.setAdapter( new FragmentPagerAdapter( getSupportFragmentManager() ) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get( position );
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        } );

        mTab.setupWithViewPager( mMainVp );
        for (int i = 0; i < brotherCategory.size(); i++) {
            //获取tab值
            String name = brotherCategory.get( i ).getName();
            //设置tab
            mTab.getTabAt( i ).setText( name );

            //传值 跟随点击
            Intent intent = getIntent();
            String name1 = intent.getStringExtra( "tabName" );
            if (name1 != null) {
                if (name1.equals( name )) {
                    mTab.getTabAt( i ).select();
                }
            }
        }
    }

}