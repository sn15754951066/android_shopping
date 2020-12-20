package com.example.android_shopping.presenter.purchased;

import com.example.android_shopping.base.BasePresenter;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.purchase.IPurchase;
import com.example.android_shopping.module.purchase.PurchaselModule;
import com.example.android_shopping.module.purchasedetails.GoodsDetail;
import com.example.android_shopping.module.purchasedetails.GoodsRelated;

public class PurchasedPresenter extends BasePresenter<IPurchase.View> implements IPurchase.Presenter {

    IPurchase.View view;
    IPurchase.Model model;

    public PurchasedPresenter(IPurchase.View view) {
        this.view = view;
        this.model=new PurchaselModule();
    }

    @Override
    public void getGoodsDetail(int id) {
        if(view!=null){
            this.model.getGoodsDetail( id, new Callback() {
                @Override
                public void success(Object data) {
                    view.getGoodsDetailReturn( (GoodsDetail) data );
                }

                @Override
                public void fail(String err) {
                    view.showToast( err );
                }
            } );
        }



    }

    @Override
    public void getGoodsRelated(int id) {
        if(view!=null){
            this.model.getGoodsRelated( id, new Callback() {
                @Override
                public void success(Object data) {
                    view.getGoodsRelatedReturn( (GoodsRelated) data );
                }

                @Override
                public void fail(String err) {
                    view.showToast( err );
                }
            } );
        }
    }
}
