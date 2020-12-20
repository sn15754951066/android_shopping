package com.example.android_shopping.module.purchase;

import com.example.android_shopping.api.ServiceApi;
import com.example.android_shopping.base.BaseModel;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.purchase.IPurchase;
import com.example.android_shopping.module.purchasedetails.GoodsDetail;
import com.example.android_shopping.module.purchasedetails.GoodsRelated;
import com.example.android_shopping.net.CommonSubscriber;
import com.example.android_shopping.net.HttpManager;
import com.example.android_shopping.utils.RxUtils;

public class PurchaselModule extends BaseModel implements IPurchase.Model {

    private ServiceApi service;

    @Override
    public void getGoodsDetail(int id, Callback callback) {
        service = HttpManager.getInstance().getService();
        addDisposible( service.getGoodsDetailData( id ).compose( RxUtils.rxScheduler() )
                .subscribeWith( new CommonSubscriber<GoodsDetail>( callback ) {
                    @Override
                    public void onNext(GoodsDetail goodsDetail) {
                        callback.success( goodsDetail );
                    }
                } ) );

    }

    @Override
    public void getGoodsRelated(int id, Callback callback) {
        service = HttpManager.getInstance().getService();
        addDisposible( service.getGoodsRelatedData( id ).compose( RxUtils.rxScheduler() )
                .subscribeWith( new CommonSubscriber<GoodsRelated>( callback ) {
                    @Override
                    public void onNext(GoodsRelated goodsRelated) {
                        callback.success( goodsRelated );
                    }
                } ) );
    }
}
