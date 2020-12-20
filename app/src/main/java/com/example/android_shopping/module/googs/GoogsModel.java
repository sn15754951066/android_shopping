package com.example.android_shopping.module.googs;

import com.example.android_shopping.api.ServiceApi;
import com.example.android_shopping.base.BaseModel;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.googs.IGoogs;
import com.example.android_shopping.module.data.GoodsBean;
import com.example.android_shopping.net.CommonSubscriber;
import com.example.android_shopping.net.HttpManager;
import com.example.android_shopping.utils.RxUtils;

public class GoogsModel extends BaseModel implements IGoogs.Model {

    private ServiceApi service;

    @Override
    public void getGoods(int id, Callback callback) {
        service = HttpManager.getInstance().getService();
        CommonSubscriber<GoodsBean> commonSubscriber = service.getGoods( id ).compose( RxUtils.rxScheduler() )
                .subscribeWith( new CommonSubscriber<GoodsBean>( callback ) {
                    @Override
                    public void onNext(GoodsBean goodsBean) {
                        callback.success( goodsBean );
                    }
                } );
        addDisposible( commonSubscriber );

    }
}
