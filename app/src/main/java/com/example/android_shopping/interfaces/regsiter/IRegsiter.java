package com.example.android_shopping.interfaces.regsiter;

import com.example.android_shopping.base.BaseModel;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.IBaseModel;
import com.example.android_shopping.interfaces.IBasePresenter;
import com.example.android_shopping.interfaces.IBaseView;
import com.example.android_shopping.interfaces.login.ILogin;
import com.example.android_shopping.interfaces.tab.ITab;
import com.example.android_shopping.module.data.LoginBean;
import com.example.android_shopping.module.data.RegisterBean;
import com.example.android_shopping.module.data.TabBean;

public interface IRegsiter {
    /**
     * 契约类
     */

    interface View extends IBaseView {
        void getRegsiterReturn(RegisterBean registerBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getRegsiter(String user,String password);
    }


    interface Model extends IBaseModel {
        void getRegsiter(String user,String password, Callback callback);
    }
}
