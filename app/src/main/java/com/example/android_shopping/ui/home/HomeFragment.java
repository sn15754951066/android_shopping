package com.example.android_shopping.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseAdapter;
import com.example.android_shopping.base.BaseFragment;
import com.example.android_shopping.interfaces.home.IHome;
import com.example.android_shopping.module.data.HomeBean;
import com.example.android_shopping.presenter.home.HomePresenter;
import com.example.android_shopping.ui.brand.BrandActivity;
import com.example.android_shopping.ui.channel.ChannelActivity;
import com.example.android_shopping.ui.homeAdapter.BrandAdapter;
import com.example.android_shopping.ui.homeAdapter.CategoryListAdapter;
import com.example.android_shopping.ui.homeAdapter.HotAdapter;
import com.example.android_shopping.ui.homeAdapter.NewGoodsAdapter;
import com.example.android_shopping.ui.homeAdapter.TopicAdapter;
import com.example.android_shopping.ui.hot.HotActivity;
import com.example.android_shopping.ui.purchasedetails.PurchaseDetailsActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomePresenter> implements IHome.View {


    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.rv_brand)
    RecyclerView rvBrand;
    @BindView(R.id.rv_product)
    RecyclerView rvProduct;
    @BindView(R.id.rv_popularity)
    RecyclerView rvPopularity;
    @BindView(R.id.rv_special)
    RecyclerView rvSpecial;
    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    @BindView(R.id.liner)
    LinearLayout liner;

    //品牌制造商直供
    @BindView(R.id.tv_title1)
    TextView tv_brand;

    //新品首发
    @BindView(R.id.tv_title2)
    TextView tv_hot;
    private BrandAdapter brandAdapter;
    private NewGoodsAdapter newGoodsAdapter;
    private HotAdapter hotAdapter;
    private TopicAdapter topicAdapter;
    private CategoryListAdapter categoryListAdapter;

    @Override
    public int getLayout() {
        return R.layout.navigation_home;
    }

    @Override
    public void initView() {

        //品牌制造商直供监听事件
        tv_brand.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( getActivity(), BrandActivity.class ) );
            }
        } );


        //新品首发的监听事件
        tv_hot.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( getActivity(), HotActivity.class ) );
            }
        } );

    }

    @Override
    public HomePresenter createPersenter() {
        return new HomePresenter( this );
    }


    @Override
    public void initData() {
        persenter.getHome();

    }

    @Override
    public void getHomeReturn(HomeBean homeBean) {
        if (homeBean != null) {
            HomeBean.DataBean data = homeBean.getData();
            //banner
            initBanner( data.getBanner() );
            initCanner( data.getChannel() );
            initBrand( data.getBrandList() );
            initNewGoods( data.getNewGoodsList() );
            initHotGoods( data.getHotGoodsList() );
            initTopic( data.getTopicList() );
            initcategory( data.getCategoryList() );


        }
    }

    private void initcategory(List<HomeBean.DataBean.CategoryListBean> categoryList) {
        for (int i = 0; i < categoryList.size(); i++) {

            View view = LayoutInflater.from( getActivity() ).inflate( R.layout.home_item_cont, null );
            TextView text = view.findViewById( R.id.txt_home_title );
            text.setText( categoryList.get( i ).getName() );
            RecyclerView recy_home = view.findViewById( R.id.recy_home );
            List<HomeBean.DataBean.CategoryListBean.GoodsListBean> goodsList1 = categoryList.get( i ).getGoodsList();
            recy_home.setLayoutManager( new StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL ) );

            categoryListAdapter = new CategoryListAdapter( getActivity(), goodsList1 );
            recy_home.setAdapter( categoryListAdapter );

            //绑定数据
            view.setTag( goodsList1 );
            liner.addView( view );
            categoryListAdapter.addListClick( new BaseAdapter.IListClick() {
                @Override
                public void itemClick(int pos) {
                    //跳转详情页面
                    List<HomeBean.DataBean.CategoryListBean.GoodsListBean> goodsListBeans = (List<HomeBean.DataBean.CategoryListBean.GoodsListBean>) view.getTag();
                    Intent intent = new Intent( getActivity(), PurchaseDetailsActivity.class );
                    intent.putExtra( "categoryId",goodsListBeans.get( pos ).getId());
                    startActivity( intent );
                }
            } );

        }
    }

    private void initTopic(List<HomeBean.DataBean.TopicListBean> topicList) {
        rvSpecial.setLayoutManager( new StaggeredGridLayoutManager( 1, StaggeredGridLayoutManager.HORIZONTAL ) );
        topicAdapter = new TopicAdapter( getActivity(), topicList );
        rvSpecial.setAdapter( topicAdapter );

    }

    private void initHotGoods(List<HomeBean.DataBean.HotGoodsListBean> hotGoodsList) {
        rvPopularity.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        hotAdapter = new HotAdapter( getActivity(), hotGoodsList );
        rvPopularity.setAdapter( hotAdapter );
        rvPopularity.addItemDecoration( new DividerItemDecoration( getActivity(), DividerItemDecoration.VERTICAL ) );

    }

    private void initNewGoods(List<HomeBean.DataBean.NewGoodsListBean> newGoodsList) {
        rvProduct.setLayoutManager( new StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL ) );
        newGoodsAdapter = new NewGoodsAdapter( getActivity(), newGoodsList );
        rvProduct.setAdapter( newGoodsAdapter );

    }

    private void initBrand(List<HomeBean.DataBean.BrandListBean> brandList) {
        rvBrand.setLayoutManager( new StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL ) );
        brandAdapter = new BrandAdapter( getActivity(), brandList );
        rvBrand.setAdapter( brandAdapter );

    }

    private void initCanner(List<HomeBean.DataBean.ChannelBean> channelBeans) {

        for (int i = 0; i < channelBeans.size(); i++) {
            View view = LayoutInflater.from( getActivity() ).inflate( R.layout.home_item_ssss, null );
            TextView text = view.findViewById( R.id.txt_home_title );
            text.setText( channelBeans.get( i ).getName() );
            ImageView image = view.findViewById( R.id.image );

            Glide.with( getActivity() ).load( channelBeans.get( i ).getIcon_url() ).into( image );
            view.setLayoutParams( new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f ) );
            linear.addView( view );

            view.setTag( channelBeans.get( i ) );

            //监听 到详情页面
            int finalI = i;
            view.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    //绑定数据
                    String url = ((HomeBean.DataBean.ChannelBean) v.getTag()).getUrl();
                    int i1 = url.indexOf( "=" );
                    String substring = url.substring( i1 + 1 );

                    Intent intent = new Intent( getActivity(), ChannelActivity.class );
                    intent.putExtra( "substring", substring );
                    intent.putExtra( "name", channelBeans.get( finalI ).getName() );
                    startActivity( intent );

                }
            } );
        }
    }

    private void initBanner(List<HomeBean.DataBean.BannerBean> bannerList) {

        banner.setImages( bannerList ).setImageLoader( new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.DataBean.BannerBean bannerBean = (HomeBean.DataBean.BannerBean) path;
                Glide.with( context ).load( bannerBean.getImage_url() ).into( imageView );
            }
        } ).start();
    }
}