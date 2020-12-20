package com.example.android_shopping.ui.purchaselAdapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseAdapter;
import com.example.android_shopping.module.purchasedetails.GoodsDetail;
import com.example.android_shopping.utils.RxUtils;
import com.example.android_shopping.utils.TxtUtils;

import java.util.List;

import butterknife.BindView;

public class UpParameterAdapter extends BaseAdapter {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_value)
    TextView tvValue;

    public UpParameterAdapter(Context context, List<GoodsDetail.DataBeanX.AttributeBean> data) {
        super( context, data );
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.adapter_info;
    }

    @Override
    protected void bindData(Object data, VH vh) {
         GoodsDetail.DataBeanX.AttributeBean attributeBean= (GoodsDetail.DataBeanX.AttributeBean) data;

        TextView name = (TextView) vh.getViewById( R.id.tv_name );
        TextView value = (TextView) vh.getViewById( R.id.tv_value );

        TxtUtils.setTextView( name,attributeBean.getName() );
        TxtUtils.setTextView( value,attributeBean.getValue() );

    }
}
