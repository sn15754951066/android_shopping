package com.example.android_shopping.presenter.home;

import com.example.android_shopping.base.BasePresenter;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.home.IHome;
import com.example.android_shopping.module.data.HomeBean;
import com.example.android_shopping.module.home.HomeModule;


public class HomePresenter extends BasePresenter<IHome.View> implements IHome.Presenter {

    IHome.View view;
    IHome.Model model;

    public HomePresenter(IHome.View view) {
        this.view = view;
        this.model=new HomeModule();
    }

    @Override
    public void getHome() {
        if(view!=null){
            this.model.getHome( new Callback() {
                @Override
                public void success(Object data) {
                    view.getHomeReturn( (HomeBean) data );
                }

                @Override
                public void fail(String err) {
                    view.showToast( err );
                }
            } );
        }

    }
}
