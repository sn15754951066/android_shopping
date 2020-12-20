package com.example.android_shopping.ui.hot;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseActivity;
import com.example.android_shopping.interfaces.hot.IHotGoodList;
import com.example.android_shopping.module.data.HotBean;
import com.example.android_shopping.module.data.HotGoodListBean;
import com.example.android_shopping.presenter.hot.HotGoodListPresenter;
import com.example.android_shopping.ui.hotAdapter.HotGoodListAdapter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class HotActivity extends BaseActivity<IHotGoodList.Presenter> implements IHotGoodList.View {

    private static final String ASC = "asc";
    private static final String DESC = "desc";
    private static final String DEFAULT = "default";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";

    @BindView(R.id.img_hotgood)
    ImageView imgHotGood;
    @BindView(R.id.txt_info)
    TextView txtInfo;
    @BindView(R.id.txt_all)
    TextView txtAll;
    @BindView(R.id.layout_price)
    LinearLayout layoutPrice;
    @BindView(R.id.img_arrow_up)
    ImageView imgArrowUp;
    @BindView(R.id.img_arrow_down)
    ImageView imgArrowDown;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.txt_sort)
    TextView txtSort;
    @BindView(R.id.recy_hotgood)
    RecyclerView recyHotGood;
    @BindView(R.id.layout_sort)
    ConstraintLayout layoutSort;


    //是否是新品
    private int isNew = 1;
    private int page = 1;
    private int size = 100;
    private String order;
    private String sort;
    private int categoryId;
    private PopupWindow popupWindow;
    private View view;
    private TextView all;
    private TextView home;
    private TextView dinner;
    private TextView chider;
    private TextView cargo;
    private TextView diet;
    private List<HotGoodListBean.DataBeanX.FilterCategoryBean> filterCategory;

    @Override
    protected int getLayout() {
        return R.layout.activity_hotgood;
    }

    @Override
    protected IHotGoodList.Presenter createPrenter() {
        return new HotGoodListPresenter( this );
    }

    @SuppressLint("ResourceType")
    @Override
    protected void initView() {
        order = ASC;
        //默认
        sort = DEFAULT;
        categoryId = 0;
        layoutPrice.setTag( 0 );
        txtAll.setTextColor( Color.parseColor( HotActivity.this.getString( R.color.red ) ) );
    }

    @Override
    protected void initData() {
        presenter.getHot();
        presenter.getHotGoodList( getParam() );
        //创建pop
        popWinds();
    }

    @SuppressLint("ResourceType")
    @OnClick({R.id.layout_price, R.id.txt_all, R.id.txt_sort})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.layout_price:
                int tag = (int) layoutPrice.getTag();
                if (tag == 0) {
                    resetPriceState();
                    priceStateUp();
                    layoutPrice.setTag( 1 );
                    order = ASC;
                } else if (tag == 1) {
                    resetPriceState();
                    priceStateDown();
                    layoutPrice.setTag( 0 );
                    order = DESC;
                }
                sort = PRICE;
                presenter.getHotGoodList( getParam() );
                break;
            case R.id.txt_all:
                resetPriceState();
                txtAll.setTextColor( Color.parseColor( HotActivity.this.getString( R.color.red ) ) );
                sort = DEFAULT;
                categoryId = 0;
                presenter.getHotGoodList( getParam() );
                break;
            case R.id.txt_sort:
                resetPriceState();
                txtSort.setTextColor( Color.parseColor( HotActivity.this.getString( R.color.red ) ) );

                initPop();
                break;
        }
    }

    @SuppressLint("ResourceType")
    private void initPop() {
        //网络请求完 显示pop
        popupWindow.showAsDropDown( txtSort );

        all = view.findViewById( R.id.all );
        home = view.findViewById( R.id.home );
        dinner = view.findViewById( R.id.dinner );
        chider = view.findViewById( R.id.chider );
        cargo = view.findViewById( R.id.cargo );
        diet = view.findViewById( R.id.diet );

        all.setOnClickListener( this::onClickItem );
        home.setOnClickListener( this::onClickItem );
        dinner.setOnClickListener( this::onClickItem );
        chider.setOnClickListener( this::onClickItem );
        cargo.setOnClickListener( this::onClickItem );
        diet.setOnClickListener( this::onClickItem );

        all.setTextColor( Color.parseColor( HotActivity.this.getString( R.color.red ) ) );
        all.setBackgroundResource( R.drawable.shape2 );

    }

    @OnClick
    public void onClickItem(View view) {
        switch (view.getId()) {
            case R.id.all:
                initColor();
                hotId( all );
                presenter.getHotGoodList( getParam() );
                getRedColor( all );
                break;
            case R.id.home:
                initColor();
                hotId( home );
                getRedColor( home );
                break;
            case R.id.dinner:
                initColor();
                hotId( dinner );
                getRedColor( dinner );
                break;
            case R.id.chider:
                initColor();
                hotId( chider );
                getRedColor( chider );
                break;
            case R.id.cargo:
                initColor();
                hotId( cargo );
                getRedColor( cargo );
                break;
            case R.id.diet:
                initColor();
                hotId( diet );
                getRedColor( diet );
                break;
        }
    }


    private void hotId(TextView show) {
        String s = show.getText().toString();
        for (int i = 0; i < filterCategory.size(); i++) {
            String name = filterCategory.get( i ).getName();
            if (s.equals( name )) {
                categoryId = filterCategory.get( i ).getId();
                sort = CATEGORY;
                presenter.getHotGoodList( getParam() );
            }
        }
    }

    @SuppressLint("ResourceType")
    private void getRedColor(TextView red) {
        red.setTextColor( Color.parseColor( HotActivity.this.getString( R.color.red ) ) );
        red.setBackgroundResource( R.drawable.shape2 );
    }


    @SuppressLint("ResourceType")
    private void initColor() {
        all.setTextColor( Color.parseColor( getString( R.color.black ) ) );
        home.setTextColor( Color.parseColor( getString( R.color.black ) ) );
        dinner.setTextColor( Color.parseColor( getString( R.color.black ) ) );
        chider.setTextColor( Color.parseColor( getString( R.color.black ) ) );
        cargo.setTextColor( Color.parseColor( getString( R.color.black ) ) );
        diet.setTextColor( Color.parseColor( getString( R.color.black ) ) );
        all.setBackgroundResource( R.drawable.shape );
        home.setBackgroundResource( R.drawable.shape );
        dinner.setBackgroundResource( R.drawable.shape );
        chider.setBackgroundResource( R.drawable.shape );
        cargo.setBackgroundResource( R.drawable.shape );
        diet.setBackgroundResource( R.drawable.shape );
    }

    /**
     * 组装当前的接口参数
     *
     * @return
     */
    private HashMap<String, String> getParam() {
        HashMap<String, String> map = new HashMap<>();
        map.put( "isNew", String.valueOf( isNew ) );
        map.put( "page", String.valueOf( page ) );
        map.put( "size", String.valueOf( size ) );
        map.put( "order", order );
        map.put( "sort", sort );
        map.put( "categoryId", String.valueOf( categoryId ) );
        return map;
    }

    /**
     * 按价格升序排序
     */
    @SuppressLint("ResourceType")
    private void priceStateUp() {
        imgArrowUp.setImageResource( R.mipmap.up_red );
        imgArrowDown.setImageResource( R.mipmap.down );
        txtPrice.setTextColor( Color.parseColor( getString( R.color.red ) ) );
    }

    /**
     * 价格的降序排列
     */
    @SuppressLint("ResourceType")
    private void priceStateDown() {
        imgArrowUp.setImageResource( R.mipmap.up );
        imgArrowDown.setImageResource( R.mipmap.down_red );
        txtPrice.setTextColor( Color.parseColor( getString( R.color.red ) ) );
    }

    /**
     * 重置条件选择的所有状态
     */
    @SuppressLint("ResourceType")
    private void resetPriceState() {
        imgArrowUp.setImageResource( R.mipmap.up );
        imgArrowDown.setImageResource( R.mipmap.down );
        txtPrice.setTextColor( Color.parseColor( getString( R.color.black ) ) );
        txtAll.setTextColor( Color.parseColor( getString( R.color.black ) ) );
        txtSort.setTextColor( Color.parseColor( getString( R.color.black ) ) );
        layoutPrice.setTag( 0 );
    }

    @Override
    public void getHotReturn(HotBean hotBean) {
        HotBean.DataBean.BannerInfoBean bannerInfo = hotBean.getData().getBannerInfo();
        Glide.with( this ).load( bannerInfo.getImg_url() ).into( imgHotGood );
        txtInfo.setText( bannerInfo.getName() );

    }

    @Override
    public void getHotGoodListReturn(HotGoodListBean hotGoodListBean) {
        filterCategory = hotGoodListBean.getData().getFilterCategory();
        recyHotGood.setLayoutManager( new StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL ) );
        HotGoodListAdapter hotGoodListAdapter = new HotGoodListAdapter( this, hotGoodListBean.getData().getData() );
        recyHotGood.setAdapter( hotGoodListAdapter );
    }

    //pop
    private void popWinds() {
        view = LayoutInflater.from( this ).inflate( R.layout.hot_pop, null );
        popupWindow = new PopupWindow( view, ViewGroup.LayoutParams.MATCH_PARENT, 200 );
        popupWindow.setBackgroundDrawable( new ColorDrawable() );
        popupWindow.setOutsideTouchable( true );
    }


}
