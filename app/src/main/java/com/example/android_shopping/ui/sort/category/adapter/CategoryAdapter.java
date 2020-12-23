package com.example.android_shopping.ui.sort.category.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseAdapter;
import com.example.android_shopping.module.data.CatalogListBase;
import com.example.android_shopping.utils.TxtUtils;

import java.util.List;

import butterknife.BindView;

public class CategoryAdapter extends BaseAdapter {

    public CategoryAdapter(Context context, List<CatalogListBase.DataBean.CurrentCategoryBean.SubCategoryListBean> data) {
        super( context, data );
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.adapter_categroy;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        CatalogListBase.DataBean.CurrentCategoryBean.SubCategoryListBean subCategoryListBean= (CatalogListBase.DataBean.CurrentCategoryBean.SubCategoryListBean) data;

        ImageView wap_banner_url = (ImageView) vh.getViewById( R.id.wap_banner_url );
        TextView tv_categroy_name = (TextView) vh.getViewById( R.id.tv_categroy_name );


        Glide.with( context).load( subCategoryListBean.getWap_banner_url() ).into( wap_banner_url );
        TxtUtils.setTextView(tv_categroy_name,subCategoryListBean.getName());

    }
}
