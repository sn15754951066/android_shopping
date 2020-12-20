package com.example.android_shopping.presenter.brand;

import com.example.android_shopping.base.BasePresenter;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.brand.IBrandListDetatil;
import com.example.android_shopping.module.brand.BrandListDetailModule;
import com.example.android_shopping.module.data.BrandHeadBean;
import com.example.android_shopping.module.data.BrandListDetailsBean;

public class BrandListDetailPresenter extends BasePresenter<IBrandListDetatil.View> implements IBrandListDetatil.Presenter {

    IBrandListDetatil.View view;
    IBrandListDetatil.Model model;

    public BrandListDetailPresenter(IBrandListDetatil.View view) {
        this.view = view;
        this.model = new BrandListDetailModule();
    }

    @Override
    public void getBrandListDetail(int id) {
        if (view != null) {
            model.getBrandListDetail( id,new Callback() {
                @Override
                public void success(Object data) {
                    view.getBrandListDetailReturn( (BrandListDetailsBean) data );
                }

                @Override
                public void fail(String err) {
                    view.showToast( err );
                }
            } );
        }

    }

    @Override
    public void getBrandHead(int id) {
        if(view!=null){
            this.model.getBrandHead(id, new Callback() {
                @Override
                public void success(Object data) {
                    view.getBrandHeadReturn( (BrandHeadBean) data );
                }

                @Override
                public void fail(String err) {
                    view.showToast( err );
                }
            } );
        }
    }
}
