package com.example.android_shopping.ui.brand;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseActivity;
import com.example.android_shopping.base.BaseAdapter;
import com.example.android_shopping.interfaces.brand.IBrand;
import com.example.android_shopping.module.data.BrandBean;
import com.example.android_shopping.presenter.brand.BrandPresenter;
import com.example.android_shopping.ui.brandAdapter.BrandMainAdapter;

import java.util.ArrayList;
import java.util.List;

public class BrandActivity extends BaseActivity<BrandPresenter> implements IBrand.View {

    private RecyclerView mRvBrandMain;
    private BrandMainAdapter brandMainAdapter;
    private List<BrandBean.DataBeanX.DataBean> dataBeans;


    @Override
    protected int getLayout() {
        return R.layout.activity_brand;
    }

    @Override
    protected BrandPresenter createPrenter() {
        return new BrandPresenter( this );
    }

    @Override
    protected void initView() {

        mRvBrandMain = (RecyclerView) findViewById( R.id.rv_brand_main );

        dataBeans = new ArrayList<>();
        mRvBrandMain.setLayoutManager( new LinearLayoutManager( this ) );
        brandMainAdapter = new BrandMainAdapter( this, dataBeans );
        mRvBrandMain.setAdapter( brandMainAdapter );


        brandMainAdapter.addListClick( new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent( BrandActivity.this, BrandListActivity.class );
                int id = dataBeans.get( pos ).getId();
                intent.putExtra( "id", id );
                startActivity(intent);
            }
        } );
    }


    @Override
    protected void initData() {
        presenter.getBrand();

    }

    @Override
    public void getBrandReturn(BrandBean brandBean) {
        dataBeans.clear();
        if (brandBean.getData() != null) {
            List<BrandBean.DataBeanX.DataBean> data = brandBean.getData().getData();

            dataBeans.addAll( data );
            brandMainAdapter.notifyDataSetChanged();
        }
    }

}