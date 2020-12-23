package com.example.android_shopping.module.register;

import com.example.android_shopping.api.ServiceApi;
import com.example.android_shopping.base.BaseModel;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.regsiter.IRegsiter;
import com.example.android_shopping.module.data.RegisterBean;
import com.example.android_shopping.net.CommonSubscriber;
import com.example.android_shopping.net.HttpManager;
import com.example.android_shopping.utils.RxUtils;

public class RegisterModule extends BaseModel implements IRegsiter.Model {

    private ServiceApi service;


    @Override
    public void getRegsiter(String user, String password, Callback callback) {
        service = HttpManager.getInstance().getService();
        addDisposible( service
                .getRegister( user, password )
                .compose( RxUtils.rxScheduler() )
                .subscribeWith( new CommonSubscriber<RegisterBean>( callback ) {
                    @Override
                    public void onNext(RegisterBean registerBean) {
                        callback.success( registerBean );
                    }
                } ) );
    }
}
