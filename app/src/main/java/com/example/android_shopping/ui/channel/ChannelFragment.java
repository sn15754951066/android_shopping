package com.example.android_shopping.ui.channel;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseFragment;
import com.example.android_shopping.interfaces.googs.IGoogs;
import com.example.android_shopping.module.data.GoodsBean;
import com.example.android_shopping.presenter.goods.GoodsPresenter;
import com.example.android_shopping.ui.homeAdapter.GoodsBeanAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChannelFragment extends BaseFragment<GoodsPresenter> implements IGoogs.View {


    @BindView(R.id.tv_channel_name)
    TextView tvChannelName;
    @BindView(R.id.tv_channel_front_desc)
    TextView tvChannelFrontDesc;
    @BindView(R.id.rv_channel)
    RecyclerView rvChannel;
    private GoodsBeanAdapter goodsAdapter;
    private ArrayList<GoodsBean.DataBeanX.GoodsListBean> goodsListBeans;
    private int id;

    @Override
    public int getLayout() {
        return R.layout.fragment_channel;
    }

    @Override
    public void initView() {
        id = getArguments().getInt( "id" );
        String name = getArguments().getString( "name" );
        String front_name = getArguments().getString( "front_name" );

        //设置值
        tvChannelName.setText( name);
        tvChannelFrontDesc.setText( front_name);

        //适配器
        rvChannel.setLayoutManager( new StaggeredGridLayoutManager( 2,StaggeredGridLayoutManager.VERTICAL ) );

        goodsListBeans = new ArrayList<>();
        goodsAdapter = new GoodsBeanAdapter( getActivity(), goodsListBeans );
        rvChannel.setAdapter( goodsAdapter );

    }

    @Override
    public GoodsPresenter createPersenter() {
        return new GoodsPresenter( this );
    }

    @Override
    public void initData() {
        persenter.getGoogs( id );

    }


    @Override
    public void getGoogsReturn(GoodsBean goodsBean) {
        List<GoodsBean.DataBeanX.GoodsListBean> goodsList = goodsBean.getData().getGoodsList();
        goodsListBeans.addAll( goodsList );
        goodsAdapter.notifyDataSetChanged();




    }
}
