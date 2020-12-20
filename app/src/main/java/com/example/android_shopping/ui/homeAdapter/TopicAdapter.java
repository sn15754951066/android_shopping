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

public class TopicAdapter extends BaseAdapter {

    private final Context context;

    public TopicAdapter(Context context, List<HomeBean.DataBean.TopicListBean> data) {
        super( context, data );
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.adapter_topic;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        HomeBean.DataBean.TopicListBean topicListBean = (HomeBean.DataBean.TopicListBean) data;
        ImageView imageUrl = (ImageView) vh.getViewById( R.id.iv_item_pic_url );
        TextView title = (TextView) vh.getViewById( R.id.tv_title );
        TextView price_info = (TextView) vh.getViewById( R.id.tv_price_info );
        TextView subtitle = (TextView) vh.getViewById( R.id.tv_subtitle );

        Glide.with( context ).load( topicListBean.getItem_pic_url() ).into( imageUrl );
        TxtUtils.setTextView( title, topicListBean.getTitle() );
        TxtUtils.setTextView( price_info, "￥"+topicListBean.getPrice_info() );
        TxtUtils.setTextView( subtitle, topicListBean.getSubtitle() );


    }
}
