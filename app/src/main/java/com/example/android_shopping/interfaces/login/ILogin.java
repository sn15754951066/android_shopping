package com.example.android_shopping.interfaces.login;

import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.IBaseModel;
import com.example.android_shopping.interfaces.IBasePresenter;
import com.example.android_shopping.interfaces.IBaseView;
import com.example.android_shopping.interfaces.regsiter.IRegsiter;
import com.example.android_shopping.module.data.LoginBean;

public interface ILogin {
    /**
     * 契约类
     */

    interface View extends IBaseView {
        void getLoginReturn(LoginBean loginBean);
    }

    interface Presenter extends IBasePresenter<ILogin.View> {
        void getLogin(String user,String password);
    }


    interface Model extends IBaseModel {
        void getLogin(String user,String password, Callback callback);
    }
}
