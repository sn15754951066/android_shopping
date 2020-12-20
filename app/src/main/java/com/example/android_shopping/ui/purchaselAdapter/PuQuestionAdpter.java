package com.example.android_shopping.ui.purchaselAdapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseAdapter;
import com.example.android_shopping.module.purchasedetails.GoodsDetail;
import com.example.android_shopping.utils.TxtUtils;

import java.util.List;

import butterknife.BindView;

public class PuQuestionAdpter extends BaseAdapter {



    public PuQuestionAdpter(Context context, List<GoodsDetail.DataBeanX.IssueBean> data) {
        super( context, data );
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.adapter_issue;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        GoodsDetail.DataBeanX.IssueBean issueBean= (GoodsDetail.DataBeanX.IssueBean) data;

        TextView question = (TextView) vh.getViewById( R.id.tv_question );
        TextView answer = (TextView) vh.getViewById( R.id.tv_answer );

        TxtUtils.setTextView( question,issueBean.getQuestion() );
        TxtUtils.setTextView( answer,issueBean.getAnswer() );



    }
}
