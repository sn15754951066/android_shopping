package com.example.android_shopping.presenter.brand;

import com.example.android_shopping.base.BasePresenter;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.brand.IBrand;
import com.example.android_shopping.module.brand.BrandModule;
import com.example.android_shopping.module.data.BrandBean;

public class BrandPresenter extends BasePresenter<IBrand.View>  implements IBrand.Presenter{

    IBrand.View view;
    IBrand.Model model;

    public BrandPresenter(IBrand.View view) {
        this.view = view;
        this.model=new BrandModule();
    }

    @Override
    public void getBrand() {
        if(view!=null){
            this.model.getBrand( new Callback<BrandBean>() {
                @Override
                public void success(BrandBean data) {
                    view.getBrandReturn( data );
                }

                @Override
                public void fail(String err) {
                    view.showToast( err );
                }
            } );
        }

    }
}
