package com.example.android_shopping.ui.brandAdapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseAdapter;
import com.example.android_shopping.module.data.BrandBean;
import com.example.android_shopping.utils.TxtUtils;

import java.util.List;

import butterknife.BindView;
import retrofit2.http.GET;

public class BrandMainAdapter extends BaseAdapter<BrandBean.DataBeanX.DataBean> {

    public BrandMainAdapter(Context context, List data) {
        super( context, data );
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.adapter_brand_main;
    }

    @Override
    protected void bindData(BrandBean.DataBeanX.DataBean dataBean, VH vh) {

        ImageView image_brand = (ImageView) vh.getViewById( R.id.image_brand );
        TextView name = (TextView) vh.getViewById( R.id.tv_brand_name );
        TextView price = (TextView) vh.getViewById( R.id.tv_brand_price );

        Glide.with( context ).load( dataBean.getApp_list_pic_url() ).into( image_brand );
        TxtUtils.setTextView( name,dataBean.getName() );
        TxtUtils.setTextView( price,dataBean.getFloor_price() );
    }
}
