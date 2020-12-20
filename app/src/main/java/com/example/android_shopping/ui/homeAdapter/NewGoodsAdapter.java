package com.example.android_shopping.ui.homeAdapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseAdapter;
import com.example.android_shopping.module.data.HomeBean;
import com.example.android_shopping.utils.TxtUtils;

import java.util.List;

import butterknife.BindView;
import retrofit2.http.GET;

public class NewGoodsAdapter extends BaseAdapter {

    private final Context context;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_retail_price)
    TextView tvRetailPrice;

    public NewGoodsAdapter(Context context, List data) {
        super( context, data );
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.adapter_newgoods;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HomeBean.DataBean.NewGoodsListBean newGoodsListBean= (HomeBean.DataBean.NewGoodsListBean) data;

        ImageView image = (ImageView) vh.getViewById( R.id.image );
        TextView name = (TextView) vh.getViewById( R.id.tv_newGoods_name );
        TextView price = (TextView) vh.getViewById( R.id.tv_retail_price );

        Glide.with( context ).load( newGoodsListBean.getList_pic_url() ).into( image );
        TxtUtils.setTextView( name,newGoodsListBean.getName() );
        TxtUtils.setTextView( price,"￥"+newGoodsListBean.getRetail_price() );
    }
}
