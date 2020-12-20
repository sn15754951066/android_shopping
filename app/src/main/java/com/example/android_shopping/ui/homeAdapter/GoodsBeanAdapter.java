package com.example.android_shopping.ui.homeAdapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseAdapter;
import com.example.android_shopping.module.data.GoodsBean;
import com.example.android_shopping.module.data.TabBean;
import com.example.android_shopping.utils.TxtUtils;

import java.util.List;

import butterknife.BindView;

public class GoodsBeanAdapter extends BaseAdapter {


    public GoodsBeanAdapter(Context context, List<GoodsBean.DataBeanX.GoodsListBean> data) {
        super( context, data );
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.fragment_current;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        GoodsBean.DataBeanX.GoodsListBean goodsListBean= (GoodsBean.DataBeanX.GoodsListBean) data;

        ImageView goods_image = (ImageView) vh.getViewById( R.id.goods_image );
        TextView googs_name = (TextView) vh.getViewById( R.id.tv_goods_name );
        TextView googs_retail_price = (TextView) vh.getViewById( R.id.tv_goods_retail_price );

        Glide.with( context ).load( goodsListBean.getList_pic_url() ).into( goods_image );
        TxtUtils.setTextView( googs_name,goodsListBean.getName() );
        TxtUtils.setTextView( googs_retail_price,"￥"+goodsListBean.getRetail_price() );



    }
}
