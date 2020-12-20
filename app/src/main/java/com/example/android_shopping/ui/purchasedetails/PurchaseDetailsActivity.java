package com.example.android_shopping.ui.purchasedetails;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alibaba.sdk.android.oss.model.UploadPartRequest;
import com.bumptech.glide.Glide;
import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseActivity;
import com.example.android_shopping.interfaces.purchase.IPurchase;
import com.example.android_shopping.module.purchasedetails.GoodsDetail;
import com.example.android_shopping.module.purchasedetails.GoodsRelated;
import com.example.android_shopping.presenter.purchased.PurchasedPresenter;
import com.example.android_shopping.ui.purchaselAdapter.PuGoodsListAdapter;
import com.example.android_shopping.ui.purchaselAdapter.PuQuestionAdpter;
import com.example.android_shopping.ui.purchaselAdapter.UpParameterAdapter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class PurchaseDetailsActivity extends BaseActivity<PurchasedPresenter> implements IPurchase.View {


    private String h5 = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                word\n" +
            "            </body>\n" +
            "        </html>";


    private int id;
    private Banner mPurchased;
    private TextView mSalesTv;
    private TextView mReimburseTv;
    private TextView mFreeTv;
    private TextView mPuNameTv;
    private TextView mPuBriefTv;
    private TextView mPuPriceTv;
    private ImageView mRightIv;
    private TextView mEvaluateTv;
    private TextView mAllTv;
    private WebView mWebView;
    private RecyclerView mQuestionRv;
    private RecyclerView mGoodsListRv;
    private RecyclerView mParameterRv;
    private ArrayList<GoodsRelated.DataBean.GoodsListBean> goodsListBeans;
    private PuGoodsListAdapter puGoodsListAdapter;


    private UpParameterAdapter upParameterAdapter;
    private ArrayList<GoodsDetail.DataBeanX.AttributeBean> attributeBeans;
    private ArrayList<GoodsDetail.DataBeanX.IssueBean> issueBeans;
    private PuQuestionAdpter puQuestionAdpter;

    @Override
    protected int getLayout() {
        return R.layout.activity_purchase_details;
    }

    @Override
    protected PurchasedPresenter createPrenter() {
        return new PurchasedPresenter( this );
    }

    @Override
    protected void initView() {

        mPurchased = (Banner) findViewById( R.id.purchased );
        mSalesTv = (TextView) findViewById( R.id.tv_sales );
        mReimburseTv = (TextView) findViewById( R.id.tv_reimburse );
        mFreeTv = (TextView) findViewById( R.id.tv_free );
        mPuNameTv = (TextView) findViewById( R.id.tv_pu_name );
        mPuBriefTv = (TextView) findViewById( R.id.tv_pu_brief );
        mPuPriceTv = (TextView) findViewById( R.id.tv_pu_price );
        mRightIv = (ImageView) findViewById( R.id.iv_right );
        mEvaluateTv = (TextView) findViewById( R.id.tv_evaluate );
        mAllTv = (TextView) findViewById( R.id.tv_all );
        mWebView = (WebView) findViewById( R.id.webView );
        mQuestionRv = (RecyclerView) findViewById( R.id.rv_question );
        mGoodsListRv = (RecyclerView) findViewById( R.id.rv_goodsList );
        mParameterRv = (RecyclerView) findViewById( R.id.rv_parameter );


        //---大家都在看---的适配器
        goodsListBeans = new ArrayList<>();
        mGoodsListRv.setLayoutManager( new StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL ) );
        puGoodsListAdapter = new PuGoodsListAdapter( this, goodsListBeans );
        mGoodsListRv.setAdapter( puGoodsListAdapter );

        mGoodsListRv.addItemDecoration( new DividerItemDecoration( this, DividerItemDecoration.VERTICAL ) );
        mGoodsListRv.addItemDecoration( new DividerItemDecoration( this, DividerItemDecoration.HORIZONTAL ) );

        //商品參數  適配器
        attributeBeans = new ArrayList<>();
        mParameterRv.setLayoutManager( new LinearLayoutManager( this) );
        upParameterAdapter = new UpParameterAdapter( this, attributeBeans );
        mParameterRv.setAdapter( upParameterAdapter );


        //常见问题 适配器
        issueBeans = new ArrayList<>();
        mQuestionRv.setLayoutManager( new LinearLayoutManager( this ) );
        puQuestionAdpter = new PuQuestionAdpter(this,issueBeans);
        mQuestionRv.setAdapter( puQuestionAdpter );

    }

    @Override
    protected void initData() {

        //接受值 进行判断
        Intent intent = getIntent();
        if (intent.hasExtra( "categoryId" )) {
            id = intent.getIntExtra( "categoryId", 0 );
            if (id > 0) {
                presenter.getGoodsDetail( id );
                presenter.getGoodsRelated( id );
            } else {
                showToast( "错误的id" );//showToast(getString(R.string.tips_error_goodid));
            }
        }

    }

    @Override
    public void getGoodsDetailReturn(GoodsDetail goodsDetail) {
        //设置banner
        List<GoodsDetail.DataBeanX.GalleryBean> gallery = goodsDetail.getData().getGallery();
        mPurchased.setImages( gallery ).setImageLoader( new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                GoodsDetail.DataBeanX.GalleryBean galleryBean = (GoodsDetail.DataBeanX.GalleryBean) path;
                Glide.with( context ).load( galleryBean.getImg_url() ).into( imageView );
            }
        } ).start();

        GoodsDetail.DataBeanX.InfoBean info = goodsDetail.getData().getInfo();
        mPuNameTv.setText( info.getName() );
        mPuBriefTv.setText( info.getGoods_brief() );
        mPuPriceTv.setText( "￥" + info.getRetail_price() );


        //
        attributeBeans.clear();
        List<GoodsDetail.DataBeanX.AttributeBean> attribute = goodsDetail.getData().getAttribute();
        attributeBeans.addAll(  attribute );
        upParameterAdapter.notifyDataSetChanged();

        //
        issueBeans.clear();
        List<GoodsDetail.DataBeanX.IssueBean> issue = goodsDetail.getData().getIssue();
        issueBeans.addAll( issue );
        puQuestionAdpter.notifyDataSetChanged();

        //h5 商品详情
        initGoodDetail( goodsDetail.getData().getInfo().getGoods_desc() );

    }

    /**
     * 商品详情数据  h5
     *
     * @param webData
     */
    private void initGoodDetail(String webData) {
        String content = h5.replace( "word", webData );
        Log.i( "TAG", content );
        mWebView.loadDataWithBaseURL( "about:blank", content, "text/html", "utf-8", null );

    }

    @Override
    public void getGoodsRelatedReturn(GoodsRelated goodsRelated) {


        goodsListBeans.clear();
        List<GoodsRelated.DataBean.GoodsListBean> goodsList = goodsRelated.getData().getGoodsList();
        goodsListBeans.addAll( goodsList );
        puGoodsListAdapter.notifyDataSetChanged();




    }
}