package com.example.android_shopping.ui.purchasedetails.bigAdaoter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseAdapter;

import java.util.List;

public class BigAdapter extends BaseAdapter {



    public BigAdapter(Context context, List<String> data) {
        super( context, data );
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.adapter_bigimage;
    }

    @Override
    protected void bindData(Object data, VH vh) {
       String s = (String) data;
        ImageView image = (ImageView) vh.getViewById( R.id.image_bigImage );
        Glide.with( context ).load( s ).into( image );

    }
}
