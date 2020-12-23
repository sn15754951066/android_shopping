package com.example.android_shopping.module.category;

import com.example.android_shopping.api.ServiceApi;
import com.example.android_shopping.base.BaseModel;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.category.ICategory;
import com.example.android_shopping.module.data.CatalogListBase;
import com.example.android_shopping.net.CommonSubscriber;
import com.example.android_shopping.net.HttpManager;
import com.example.android_shopping.utils.RxUtils;

public class CategoryModule extends BaseModel implements ICategory.Model {

    private ServiceApi service;

    @Override
    public void getCategory(int id, Callback callback) {
        service = HttpManager.getInstance().getService();
        addDisposible( service.getCatalogList( id ).compose( RxUtils.rxScheduler() )
                .subscribeWith( new CommonSubscriber<CatalogListBase>( callback ) {
                    @Override
                    public void onNext(CatalogListBase catalogListBase) {
                        callback.success( catalogListBase );
                    }
                } ) );
    }
}
