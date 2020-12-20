package com.example.android_shopping.module.hot;

import com.example.android_shopping.api.ServiceApi;
import com.example.android_shopping.base.BaseModel;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.hot.IHotGoodList;
import com.example.android_shopping.module.data.HotBean;
import com.example.android_shopping.module.data.HotGoodListBean;
import com.example.android_shopping.net.CommonSubscriber;
import com.example.android_shopping.net.HttpManager;
import com.example.android_shopping.utils.RxUtils;

import java.util.HashMap;

import retrofit2.http.HTTP;

public class HotGoodListModule extends BaseModel implements IHotGoodList.Model {

    private ServiceApi service;

    @Override
    public void getHot(Callback callback) {
        //新品首发的头信息
        service = HttpManager.getInstance().getService();
        CommonSubscriber<HotBean> commonSubscriber = service.getHot().compose( RxUtils.rxScheduler() )
                .subscribeWith( new CommonSubscriber<HotBean>( callback ) {
                    @Override
                    public void onNext(HotBean hotBean) {
                        callback.success( hotBean );
                    }
                } );
        addDisposible( commonSubscriber );
    }

    @Override
    public void getHotGoodList(HashMap<String, String> map, Callback callback) {
        //新品首发的列表
        service = HttpManager.getInstance().getService();
        CommonSubscriber<HotGoodListBean> commonSubscriber = service.getHotGoodListBean( map ).compose( RxUtils.rxScheduler() )
                .subscribeWith( new CommonSubscriber<HotGoodListBean>( callback ) {
                    @Override
                    public void onNext(HotGoodListBean hotGoodListBean) {
                        callback.success( hotGoodListBean );
                    }
                } );
        addDisposible( commonSubscriber );
    }
}
