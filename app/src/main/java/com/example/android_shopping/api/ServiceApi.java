package com.example.android_shopping.api;

import com.example.android_shopping.module.data.BrandBean;
import com.example.android_shopping.module.data.BrandHeadBean;
import com.example.android_shopping.module.data.BrandListDetailsBean;
import com.example.android_shopping.module.data.CatalogListBase;
import com.example.android_shopping.module.data.GoodsBean;
import com.example.android_shopping.module.data.HomeBean;
import com.example.android_shopping.module.data.HotBean;
import com.example.android_shopping.module.data.HotGoodListBean;
import com.example.android_shopping.module.data.LoginBean;
import com.example.android_shopping.module.data.RegisterBean;
import com.example.android_shopping.module.data.TabBean;
import com.example.android_shopping.module.purchasedetails.GoodsDetail;
import com.example.android_shopping.module.purchasedetails.GoodsRelated;

import java.util.HashMap;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ServiceApi {
    String BASE_URL = "https://cdplay.cn/";

    //首页数据
    @GET("api/index")
    Flowable<HomeBean> getHome();

    //https://cdplay.cn/goods/category?id=1005000   通过频道获取的tablayout的数据接口
    @GET("goods/category")
    Flowable<TabBean> getTab(@Query("id") int id);

    //https://cdplay.cn/api/goods/list?id=1005001&page=2&size=5  获取分类数据
    @GET("goods/list")
    Flowable<GoodsBean> getGoods(@Query("categoryId") int id);

    /**
     * 品牌制造商直供
     */

    //https://cdplay.cn/api/brand/list?page=1&size=1000 制造商品牌列表
    @GET("api/brand/list?page=1&size=1000")
    Flowable<BrandBean> getBrand();

    //https://cdplay.cn/api/brand/detail?id=1001000  制造商品牌头信息
    @GET("api/brand/detail")
    Flowable<BrandHeadBean> getBrandHead(@Query("id") int id);

    //https://cdplay.cn/api/goods/list?brandId=1001000&page=1&size=1000 制造商品牌详情页列表
    @GET("api/goods/list?page=1&size=1000")
    Flowable<BrandListDetailsBean> getBrandListDetail(@Query("brandId") int brandId);


    /**
     * 新品首发
     *
     * @return
     */
    //https://cdplay.cn/api/goods/hot 新品首发的头信息
    @GET("goods/hot")
    Flowable<HotBean> getHot();

    //https://cdplay.cn/api/goods/list?isNew=1&page=1&size =1000&order=asc&sort=default&categoryId=0 //新品首发的详细列表
    @GET("goods/list")
    Flowable<HotGoodListBean> getHotGoodListBean(@QueryMap HashMap<String, String> map);


    /**
     * 详情页
     */

    //https://cdplay.cn/api/goods/detail?id=1155000 商品购买详情页
    @GET("api/goods/detail")
    Flowable<GoodsDetail> getGoodsDetailData(@Query("id") int id);

    //商品购买详情页 底部数据列表 https://cdplay.cn/api/goods/related?id=1155000
    @GET("api/goods/related")
    Flowable<GoodsRelated> getGoodsRelatedData(@Query("id") int id);


    //分类
    //https://cdplay.cn/api/catalog/current?id=1005001
    @GET("api/catalog/current?")
    Flowable<CatalogListBase> getCatalogList(@Query("id") int id);


    //注册
    //https://cdplay.cn/api/auth/register
    @POST("api/auth/register")
    @FormUrlEncoded
    Flowable<RegisterBean> getRegister(@Field("username") String username, @Field("password") String pw);

    //登录
    //https://cdplay.cn/api/auth/login
    @POST("api/auth/login")
    @FormUrlEncoded
    Flowable<LoginBean> getLogin(@Field("username") String username, @Field("password") String pw);




}
