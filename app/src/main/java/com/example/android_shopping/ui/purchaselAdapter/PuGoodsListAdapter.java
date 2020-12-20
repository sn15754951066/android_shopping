package com.example.android_shopping.ui.purchaselAdapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseAdapter;
import com.example.android_shopping.module.purchasedetails.GoodsRelated;
import com.example.android_shopping.utils.TxtUtils;

import java.util.List;

import butterknife.BindView;

public class PuGoodsListAdapter extends BaseAdapter {


    public PuGoodsListAdapter(Context context, List<GoodsRelated.DataBean.GoodsListBean> data) {
        super( context, data );
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.adapter_goodslist;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        GoodsRelated.DataBean.GoodsListBean goodsListBean= (GoodsRelated.DataBean.GoodsListBean) data;
        ImageView image = (ImageView) vh.getViewById( R.id.iv_goods_image );
        TextView name = (TextView) vh.getViewById( R.id.tv_goods_name );
        TextView price = (TextView) vh.getViewById( R.id.tv_goods_price );

        Glide.with( context ).load( goodsListBean.getList_pic_url() ).into( image );
        TxtUtils.setTextView( name,goodsListBean.getName() );
        TxtUtils.setTextView( price,"￥"+goodsListBean.getRetail_price() );



    }
}
