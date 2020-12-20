package com.example.android_shopping.ui.homeAdapter;

import android.content.Context;
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

public class HotAdapter extends BaseAdapter {

    private final Context context;

    public HotAdapter(Context context, List<HomeBean.DataBean.HotGoodsListBean> data) {
        super( context, data );
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.adapter_hot;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        HomeBean.DataBean.HotGoodsListBean hotGoodsListBean= (HomeBean.DataBean.HotGoodsListBean) data;

        ImageView image = (ImageView) vh.getViewById( R.id.image_hot );
        TextView name = (TextView) vh.getViewById( R.id.hot_name );
        TextView goods_brief = (TextView) vh.getViewById( R.id.goods_brief );
        TextView retail_price = (TextView) vh.getViewById( R.id.tv_retail_price );
        Glide.with( context).load( hotGoodsListBean.getList_pic_url() ).into( image );
        TxtUtils.setTextView( name,hotGoodsListBean.getName());
        TxtUtils.setTextView( goods_brief,hotGoodsListBean.getGoods_brief());
        TxtUtils.setTextView( retail_price,"￥"+hotGoodsListBean.getRetail_price());

    }
}
