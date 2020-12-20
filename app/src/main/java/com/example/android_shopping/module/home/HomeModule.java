package com.example.android_shopping.module.home;

import com.example.android_shopping.api.ServiceApi;
import com.example.android_shopping.base.BaseModel;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.home.IHome;
import com.example.android_shopping.module.data.HomeBean;
import com.example.android_shopping.net.CommonSubscriber;
import com.example.android_shopping.net.HttpManager;
import com.example.android_shopping.utils.RxUtils;

import io.reactivex.disposables.Disposable;

public class HomeModule extends BaseModel implements IHome.Model {


    @Override
    public void getHome(Callback callback) {
        Disposable disposable = HttpManager.getInstance().getService()
                .getHome()
                .compose( RxUtils.rxScheduler() )
                .subscribeWith( new CommonSubscriber<HomeBean>(callback) {
                    @Override
                    public void onNext(HomeBean dataBean) {
                        callback.success( dataBean );
                    }
                } );
        addDisposible( disposable );
    }
}
