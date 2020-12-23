package com.example.android_shopping.module.Login;

import com.example.android_shopping.api.ServiceApi;
import com.example.android_shopping.base.BaseModel;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.login.ILogin;
import com.example.android_shopping.module.data.LoginBean;
import com.example.android_shopping.net.CommonSubscriber;
import com.example.android_shopping.net.HttpManager;
import com.example.android_shopping.utils.RxUtils;

public class LoginModule extends BaseModel implements ILogin.Model {

    private ServiceApi service;

    @Override
    public void getLogin(String user, String password, Callback callback) {
        service = HttpManager.getInstance().getService();

        addDisposible(  service.getLogin( user,password ).compose( RxUtils.rxScheduler() )
                .subscribeWith( new CommonSubscriber<LoginBean>(callback) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        callback.success( loginBean );
                    }
                } ) );

    }
}
