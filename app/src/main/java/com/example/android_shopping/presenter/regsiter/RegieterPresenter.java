package com.example.android_shopping.presenter.regsiter;

import com.example.android_shopping.base.BasePresenter;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.regsiter.IRegsiter;
import com.example.android_shopping.module.data.RegisterBean;
import com.example.android_shopping.module.register.RegisterModule;

public class RegieterPresenter extends BasePresenter<IRegsiter.View> implements IRegsiter.Presenter {

    IRegsiter.View view;
    IRegsiter.Model model;

    public RegieterPresenter(IRegsiter.View view) {
        this.view = view;
        this.model = new RegisterModule();
    }


    @Override
    public void getRegsiter(String user, String password) {
        this.model.getRegsiter(user,password, new Callback() {
            @Override
            public void success(Object data) {
                view.getRegsiterReturn( (RegisterBean) data );
            }

            @Override
            public void fail(String err) {
                view.showToast( err );
            }
        } );
    }
}
