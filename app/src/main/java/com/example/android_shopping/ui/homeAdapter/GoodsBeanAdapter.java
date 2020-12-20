package com.example.android_shopping.ui.homeAdapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseAdapter;
import com.example.android_shopping.module.data.TabBean;

import java.util.List;

import butterknife.BindView;

public class BrotherBeanAdapter extends BaseAdapter {
   

    public BrotherBeanAdapter(Context context, List<TabBean.DataBean.BrotherCategoryBean> data) {
        super( context, data );
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.fragment_current;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        ImageView goods_image = (ImageView) vh.getViewById( R.id.goods_image );
        TextView googs_name = (TextView) vh.getViewById( R.id.tv_goods_name );
        TextView googs_retail_price = (TextView) vh.getViewById( R.id.tv_goods_retail_price );


    }
}
