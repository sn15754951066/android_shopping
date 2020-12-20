package com.example.android_shopping.ui.brand;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseActivity;
import com.example.android_shopping.interfaces.brand.IBrandListDetatil;
import com.example.android_shopping.module.data.BrandHeadBean;
import com.example.android_shopping.module.data.BrandListDetailsBean;
import com.example.android_shopping.presenter.brand.BrandListDetailPresenter;
import com.example.android_shopping.ui.brandAdapter.BrandListAdapter;
import com.umeng.commonsdk.debug.D;

import java.util.ArrayList;
import java.util.List;


public class BrandListActivity extends BaseActivity<BrandListDetailPresenter> implements IBrandListDetatil.View {


    private ImageView mBrandHandImage;
    private TextView mBrandNameHandTv;
    private TextView mSimpleDescTv;

    private RecyclerView mBrandListRv;
    private ArrayList<BrandListDetailsBean.DataBeanX.DataBean> dataBeans;
    private BrandListAdapter brandListAdapter;
    private int id;

    @Override
    protected int getLayout() {
        return R.layout.activity_brand_list;
    }

    @Override
    protected BrandListDetailPresenter createPrenter() {
        return new BrandListDetailPresenter( this );
    }

    @Override
    protected void initView() {

        mBrandHandImage = (ImageView) findViewById( R.id.image_brand_hand );
        mBrandNameHandTv = (TextView) findViewById( R.id.tv_brand_name_hand );
        mSimpleDescTv = (TextView) findViewById( R.id.tv_simple_desc );
        mBrandListRv = (RecyclerView) findViewById( R.id.rv_brandList );

        mBrandListRv.setLayoutManager( new StaggeredGridLayoutManager( 2,StaggeredGridLayoutManager.VERTICAL ) );
        dataBeans = new ArrayList<>();
        brandListAdapter = new BrandListAdapter( this, dataBeans );

        mBrandListRv.addItemDecoration( new DividerItemDecoration( this,DividerItemDecoration.VERTICAL ) );
        mBrandListRv.addItemDecoration( new DividerItemDecoration( this,DividerItemDecoration.HORIZONTAL ) );
        mBrandListRv.setAdapter( brandListAdapter );
        Intent intent = getIntent();
        id = intent.getIntExtra( "id",0 );

    }

    @Override
    protected void initData() {
        presenter.getBrandHead(id);
        presenter.getBrandListDetail(id);

    }

    @Override
    public void getBrandListDetailReturn(BrandListDetailsBean brandListDetailsBean) {
        dataBeans.clear();
        List<BrandListDetailsBean.DataBeanX.DataBean> data = brandListDetailsBean.getData().getData();
        dataBeans.addAll( data );
        brandListAdapter.notifyDataSetChanged();


    }

    @Override
    public void getBrandHeadReturn(BrandHeadBean brandHeadBean) {
        BrandHeadBean.DataBean data = brandHeadBean.getData();
        String list_pic_url = data.getBrand().getList_pic_url();
        Glide.with( this ).load( list_pic_url ).into( mBrandHandImage );
        mBrandNameHandTv.setText( data.getBrand().getName() );
        mSimpleDescTv.setText( data.getBrand().getSimple_desc() );

    }


}