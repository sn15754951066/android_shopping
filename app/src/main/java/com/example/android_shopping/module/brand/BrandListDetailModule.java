package com.example.android_shopping.module.brand;

import com.example.android_shopping.api.ServiceApi;
import com.example.android_shopping.base.BaseModel;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.brand.IBrandListDetatil;
import com.example.android_shopping.module.data.BrandHeadBean;
import com.example.android_shopping.module.data.BrandListDetailsBean;
import com.example.android_shopping.net.CommonSubscriber;
import com.example.android_shopping.net.HttpManager;
import com.example.android_shopping.utils.RxUtils;

public class BrandListDetailModule extends BaseModel implements IBrandListDetatil.Model {

    private ServiceApi service;

    @Override
    public void getBrandListDetail(int id,Callback callback) {
        service = HttpManager.getInstance().getService();
        addDisposible( service.getBrandListDetail(id).compose( RxUtils.rxScheduler() )
                .subscribeWith( new CommonSubscriber<BrandListDetailsBean>( callback ) {
                    @Override
                    public void onNext(BrandListDetailsBean brandListDetailsBean) {
                        callback.success( brandListDetailsBean );
                    }
                } ) );
    }

    @Override
    public void getBrandHead(int id,Callback callback) {
        service = HttpManager.getInstance().getService();
        addDisposible( service.getBrandHead(id).compose( RxUtils.rxScheduler() )
                .subscribeWith( new CommonSubscriber<BrandHeadBean>( callback ) {
                    @Override
                    public void onNext(BrandHeadBean brandHeadBean) {
                        callback.success( brandHeadBean );
                    }
                } ));
    }
}
