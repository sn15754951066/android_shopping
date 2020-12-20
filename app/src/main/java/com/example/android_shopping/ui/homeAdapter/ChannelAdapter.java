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

public class ChannelAdapter extends BaseAdapter {


    @BindView(R.id.one)
    ImageView one;

    @BindView(R.id.tv_name_one)
    TextView tvNameOne;

    private HomeBean.DataBean.ChannelBean channelBean;

    public ChannelAdapter(Context context, List<HomeBean.DataBean.ChannelBean> data) {
        super( context, data );
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.adapter_channel;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HomeBean.DataBean.ChannelBean channelBean= (HomeBean.DataBean.ChannelBean) data;

        ImageView image_one = (ImageView) vh.getViewById( R.id.one );


        TextView name_one = (TextView) vh.getViewById( R.id.tv_name_one );


        Glide.with( context ).load( channelBean.getIcon_url() ).into(image_one  );


        TxtUtils.setTextView( name_one,channelBean.getName() );



    }
}
