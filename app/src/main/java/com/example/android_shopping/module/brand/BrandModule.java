package com.example.android_shopping.module.brand;

import com.example.android_shopping.api.ServiceApi;
import com.example.android_shopping.base.BaseModel;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.IBaseModel;
import com.example.android_shopping.interfaces.brand.IBrand;
import com.example.android_shopping.module.data.BrandBean;
import com.example.android_shopping.net.CommonSubscriber;
import com.example.android_shopping.net.HttpManager;
import com.example.android_shopping.utils.RxUtils;

public class BrandModule extends BaseModel implements IBrand.Model {

    private ServiceApi service;

    @Override
    public void getBrand(Callback callback) {
        service = HttpManager.getInstance().getService();
        addDisposible( service.getBrand()
                .compose( RxUtils.rxScheduler() )
                .subscribeWith( new CommonSubscriber<BrandBean>( callback ) {
                    @Override
                    public void onNext(BrandBean brandBean) {
                        callback.success( brandBean );
                    }
                } )
        );
    }
}
