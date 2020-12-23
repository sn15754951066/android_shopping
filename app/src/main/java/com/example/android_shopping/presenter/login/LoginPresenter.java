package com.example.android_shopping.presenter.login;

import com.example.android_shopping.base.BasePresenter;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.login.ILogin;
import com.example.android_shopping.module.Login.LoginModule;
import com.example.android_shopping.module.data.LoginBean;
import com.umeng.commonsdk.debug.I;

public class LoginPresenter extends BasePresenter<ILogin.View> implements ILogin.Presenter{


    ILogin.View view;
    ILogin.Model model;

    public LoginPresenter(ILogin.View view) {
        this.view = view;
        this.model=new LoginModule();
    }

    @Override
    public void getLogin(String user, String password) {
        this.model.getLogin( user, password, new Callback() {
            @Override
            public void success(Object data) {
                view.getLoginReturn( (LoginBean) data );
            }

            @Override
            public void fail(String err) {
                view.showToast( err );

            }
        } );

    }
}
