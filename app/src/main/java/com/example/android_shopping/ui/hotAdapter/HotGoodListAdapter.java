package com.example.android_shopping.ui.hotAdapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseAdapter;
import com.example.android_shopping.module.data.HotGoodListBean;
import com.example.android_shopping.utils.TxtUtils;

import java.util.List;

import butterknife.BindView;
import retrofit2.http.GET;

public class HotGoodListAdapter extends BaseAdapter {
    @BindView(R.id.image_hotgoodList)
    ImageView imageHotgoodList;
    @BindView(R.id.tv_name_hotgoodList)
    TextView tvNameHotgoodList;
    @BindView(R.id.tv_floor_price_hotgoodList)
    TextView tvFloorPriceHotgoodList;
    @BindView(R.id.liner)
    LinearLayout liner;

    public HotGoodListAdapter(Context context, List<HotGoodListBean.DataBeanX.DataBean> data) {
        super( context, data );
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.adapter_hotgoodlist;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        HotGoodListBean.DataBeanX.DataBean dataBean= (HotGoodListBean.DataBeanX.DataBean) data;
        ImageView image = (ImageView) vh.getViewById( R.id.image_hotgoodList );
        TextView name = (TextView) vh.getViewById( R.id.tv_name_hotgoodList );
        TextView price = (TextView) vh.getViewById( R.id.tv_floor_price_hotgoodList );

        Glide.with( context ).load( dataBean.getList_pic_url()).into( image );
        TxtUtils.setTextView( name,dataBean.getName() );
        TxtUtils.setTextView( price,"￥"+dataBean.getRetail_price() );

    }
}
