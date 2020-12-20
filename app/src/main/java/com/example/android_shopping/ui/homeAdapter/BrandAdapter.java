package com.example.android_shopping.ui.homeAdapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseAdapter;
import com.example.android_shopping.module.data.HomeBean;
import com.example.android_shopping.utils.TxtUtils;

import java.util.List;

import butterknife.BindView;

public class BrandAdapter extends BaseAdapter {

    @BindView(R.id.iv_imageUrl)
    ImageView ivImageUrl;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_price)
    TextView tvPrice;

    public BrandAdapter(Context context, List<HomeBean.DataBean.BrandListBean> data) {
        super( context, data );
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.adapter_brand;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HomeBean.DataBean.BrandListBean brandListBean= (HomeBean.DataBean.BrandListBean) data;
        ImageView imageUrl = (ImageView) vh.getViewById( R.id.iv_imageUrl );
        TextView name = (TextView) vh.getViewById( R.id.tv_name );
        TextView price = (TextView) vh.getViewById( R.id.tv_price );

        Glide.with( context).load( brandListBean.getNew_pic_url() ).into( imageUrl );
        TxtUtils.setTextView( name,brandListBean.getName() );
        TxtUtils.setTextView( price,brandListBean.getFloor_price() );
    }
}
