package com.example.android_shopping.ui.purchasedetails;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseActivity;
import com.example.android_shopping.base.BaseAdapter;
import com.example.android_shopping.interfaces.purchase.IPurchase;
import com.example.android_shopping.module.home.login.LoginActivity;
import com.example.android_shopping.module.purchasedetails.GoodsDetail;
import com.example.android_shopping.module.purchasedetails.GoodsRelated;
import com.example.android_shopping.presenter.purchased.PurchasedPresenter;
import com.example.android_shopping.ui.purchasedetails.bigAdaoter.BigAdapter;
import com.example.android_shopping.ui.purchasedetails.bigImage.BigImageActivity;
import com.example.android_shopping.ui.purchaselAdapter.PuGoodsListAdapter;
import com.example.android_shopping.ui.purchaselAdapter.PuQuestionAdpter;
import com.example.android_shopping.ui.purchaselAdapter.UpParameterAdapter;
import com.example.android_shopping.utils.SpUtils;
import com.example.android_shopping.utils.TxtUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PurchaseDetailsActivity extends BaseActivity<PurchasedPresenter> implements IPurchase.View {


    @BindView(R.id.purchased)
    Banner purchased;
    @BindView(R.id.tv_sales)
    TextView tvSales;
    @BindView(R.id.tv_reimburse)
    TextView tvReimburse;
    @BindView(R.id.tv_free)
    TextView tvFree;
    @BindView(R.id.tv_pu_name)
    TextView tvPuName;
    @BindView(R.id.tv_pu_brief)
    TextView tvPuBrief;
    @BindView(R.id.tv_pu_price)
    TextView tvPuPrice;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_evaluate)
    TextView tvEvaluate;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.rv_parameter)
    RecyclerView rvParameter;
    //@BindView(R.id.webView)
    //WebView webView;
    @BindView(R.id.rv_question)
    RecyclerView rvQuestion;
    @BindView(R.id.rv_goodsList)
    RecyclerView rvGoodsList;
    @BindView(R.id.tv_like)
    ImageView tvLike;
    @BindView(R.id.image_shopping)
    ImageView imageShopping;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.buy)
    TextView buy;
    @BindView(R.id.tv_join)
    TextView tvJoin;
    @BindView(R.id.rv_bigImage)
    RecyclerView rvBigImage;
//    private String h5 = "<html>\n" +
//            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
//            "            <head>\n" +
//            "                <style>\n" +
//            "                    p{\n" +
//            "                        margin:0px;\n" +
//            "                    }\n" +
//            "                    img{\n" +
//            "                        width:100%;\n" +
//            "                        height:auto;\n" +
//            "                    }\n" +
//            "                </style>\n" +
//            "            </head>\n" +
//            "            <body>\n" +
//            "                word\n" +
//            "            </body>\n" +
//            "        </html>";


    private int id;
    private Banner mPurchased;
    private TextView mSalesTv;
    private TextView mReimburseTv;
    private TextView mFreeTv;
    private TextView mPuNameTv;
    private TextView mPuBriefTv;
    private TextView mPuPriceTv;
    private ImageView mRightIv;
    private ImageView mLike;
    private TextView mEvaluateTv;
    private TextView mAllTv;
    private TextView mJoin;
    //private WebView mWebView;

    private RecyclerView mQuestionRv;
    private RecyclerView mGoodsListRv;
    private RecyclerView mParameterRv;
    private ArrayList<GoodsRelated.DataBean.GoodsListBean> goodsListBeans;
    private PuGoodsListAdapter puGoodsListAdapter;


    private UpParameterAdapter upParameterAdapter;
    private ArrayList<GoodsDetail.DataBeanX.AttributeBean> attributeBeans;
    private ArrayList<GoodsDetail.DataBeanX.IssueBean> issueBeans;
    private PuQuestionAdpter puQuestionAdpter;
    private Button btn_jia;
    private Button btn_jian;
    private TextView tv_shu;
    private int shu;
    private RecyclerView mRvBigImage;
    private List<String> listUrl;
    private BigAdapter bigAdapter;
    private TextView mTvNum;
    private PopupWindow popupWindow;

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
        //mWebView = (WebView) findViewById( R.id.webView );
        mQuestionRv = (RecyclerView) findViewById( R.id.rv_question );
        mGoodsListRv = (RecyclerView) findViewById( R.id.rv_goodsList );
        mParameterRv = (RecyclerView) findViewById( R.id.rv_parameter );
        mJoin = (TextView) findViewById( R.id.tv_join );
        mLike = (ImageView) findViewById( R.id.tv_like );
        mRvBigImage = (RecyclerView) findViewById( R.id.rv_bigImage );
        mTvNum = (TextView) findViewById( R.id.tv_num );


        //---大家都在看---的适配器
        goodsListBeans = new ArrayList<>();
        mGoodsListRv.setLayoutManager( new StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL ) );
        puGoodsListAdapter = new PuGoodsListAdapter( this, goodsListBeans );
        mGoodsListRv.setAdapter( puGoodsListAdapter );

        mGoodsListRv.addItemDecoration( new DividerItemDecoration( this, DividerItemDecoration.VERTICAL ) );
        mGoodsListRv.addItemDecoration( new DividerItemDecoration( this, DividerItemDecoration.HORIZONTAL ) );

        //商品參數  適配器
        attributeBeans = new ArrayList<>();
        mParameterRv.setLayoutManager( new LinearLayoutManager( this ) );
        upParameterAdapter = new UpParameterAdapter( this, attributeBeans );
        mParameterRv.setAdapter( upParameterAdapter );


        //常见问题 适配器
        issueBeans = new ArrayList<>();
        mQuestionRv.setLayoutManager( new LinearLayoutManager( this ) );
        puQuestionAdpter = new PuQuestionAdpter( this, issueBeans );
        mQuestionRv.setAdapter( puQuestionAdpter );


        mLike.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLike.setImageResource( R.drawable.red_like );
            }
        } );
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

    private void initpop(GoodsDetail.DataBeanX.InfoBean info) {
        //pop
        View join_view = LayoutInflater.from( PurchaseDetailsActivity.this ).inflate( R.layout.join_item, null );
        popupWindow = new PopupWindow( join_view, GridLayout.LayoutParams.MATCH_PARENT, 300 );


        ImageView image_pop = join_view.findViewById( R.id.image_pop );
        TextView price_pop = join_view.findViewById( R.id.tv_price_pop );
        btn_jia = join_view.findViewById( R.id.btn_jia );
        btn_jian = join_view.findViewById( R.id.btn_jian );
        tv_shu = join_view.findViewById( R.id.btn_shu );
        TextView tv_back = join_view.findViewById( R.id.tv_back );

        Glide.with( PurchaseDetailsActivity.this ).load( info.getList_pic_url() ).into( image_pop );
        price_pop.setText( "￥" + info.getRetail_price() + "" );
        shu = 1;

        ClickListener clickListener = new ClickListener();
        btn_jia.setOnClickListener( clickListener );
        btn_jian.setOnClickListener( clickListener );


        tv_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        } );
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
        attributeBeans.addAll( attribute );
        upParameterAdapter.notifyDataSetChanged();

        //
        issueBeans.clear();
        List<GoodsDetail.DataBeanX.IssueBean> issue = goodsDetail.getData().getIssue();
        issueBeans.addAll( issue );
        puQuestionAdpter.notifyDataSetChanged();

        //h5 商品详情
        initGoodDetail( goodsDetail.getData().getInfo().getGoods_desc() );

        //绑定数据
        mJoin.setTag( 0 );

        initpop(info);




        //加入购物车
        mJoin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //String token = SpUtils.getInstance().getString("token");
                //if(token!=null){
                    int tag = (int) mJoin.getTag();
                    if (tag==0){
                        popupWindow.showAtLocation( mJoin, Gravity.BOTTOM, 0, 60 );
                        mJoin.setTag( 1 );
                    }else{
                        popupWindow.dismiss();
                        mJoin.setTag( 0 );
                    }
                //}else{
                    //跳转页面
                    startActivity( new Intent( PurchaseDetailsActivity.this, LoginActivity.class ) );
                //}
            }
        } );


    }


    class ClickListener implements View.OnClickListener {




        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_jia:
                    shu++;
                    if (shu > 0) {
                        tv_shu.setText( shu + "" );
                        String s = mTvNum.getText().toString();
                        Integer integer = Integer.valueOf( s );
                        TxtUtils.setTextView( mTvNum, (integer + 1) + "" );
                    }


                    break;
                case R.id.btn_jian:
                    shu--;
                    if (shu > 0) {
                        tv_shu.setText( shu + "" );
                        String s = mTvNum.getText().toString();
                        Integer integer = Integer.valueOf( s );
                        TxtUtils.setTextView( mTvNum, (integer - 1) + "" );
                    }

                    break;
            }
        }
    }

    /**
     * 商品详情数据  h5
     *
     * @param webData
     */
    private void initGoodDetail(String webData) {
//        String content = h5.replace( "word", webData );
//        Log.i( "TAG", content );
//        mWebView.loadDataWithBaseURL( "about:blank", content, "text/html", "utf-8", null );


        listUrl = new ArrayList<>();

        String str = null;
        String[] image = webData.split( "http" );
        for (int i = 0; i < image.length; i++) {
            String[] url = image[i].split( "jpg" );
            if (url.length != 0) {
                for (int j = 0; j < url.length - 1; j++) {
                    str = "http" + url[0] + "jpg";
                    //集合里是否包含了元素
                    if (!listUrl.contains( str ))
                        listUrl.add( str );
                }
            }
            String[] urls = image[i].split( "png" );
            if (urls.length != 0) {
                for (int j = 0; j < urls.length - 1; j++) {
                    str = "http" + urls[0] + "png";
                    if (!listUrl.contains( str ))
                        listUrl.add( str );
                }
            }
        }
        //大图
        mRvBigImage.setLayoutManager( new LinearLayoutManager( this ) );
        bigAdapter = new BigAdapter( this, listUrl );
        mRvBigImage.setAdapter( bigAdapter );

        bigAdapter.addListClick( new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                //跳转页面
                Intent intent = new Intent( PurchaseDetailsActivity.this, BigImageActivity.class );
                intent.putExtra( "imageList", (Serializable) listUrl );
                intent.putExtra( "id", pos );
                startActivity( intent );
            }
        } );

    }

    @Override
    public void getGoodsRelatedReturn(GoodsRelated goodsRelated) {


        goodsListBeans.clear();
        List<GoodsRelated.DataBean.GoodsListBean> goodsList = goodsRelated.getData().getGoodsList();
        goodsListBeans.addAll( goodsList );
        puGoodsListAdapter.notifyDataSetChanged();


    }

}