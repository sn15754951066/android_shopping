package com.example.android_shopping.ui.brandAdapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseAdapter;
import com.example.android_shopping.module.data.BrandListDetailsBean;
import com.example.android_shopping.utils.TxtUtils;

import java.util.List;

import butterknife.BindView;

public class BrandListAdapter extends BaseAdapter {

    public BrandListAdapter(Context context, List<BrandListDetailsBean.DataBeanX.DataBean> data) {
        super( context, data );
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.adapter_brandlist;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        BrandListDetailsBean.DataBeanX.DataBean dataBean= (BrandListDetailsBean.DataBeanX.DataBean) data;

        ImageView imageBrandList = (ImageView) vh.getViewById( R.id.image_brandList );
        TextView name = (TextView) vh.getViewById( R.id.tv_brandList_name );
        TextView price = (TextView) vh.getViewById( R.id.tv_brandList_price );
        Glide.with( context ).load( dataBean.getList_pic_url() ).into( imageBrandList );
        TxtUtils.setTextView( name,dataBean.getName() );
        TxtUtils.setTextView( price,"￥"+dataBean.getRetail_price() );


    }
}
