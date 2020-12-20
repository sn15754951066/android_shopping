package com.example.android_shopping.presenter.goods;

import android.telecom.Call;

import com.example.android_shopping.base.BasePresenter;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.googs.IGoogs;
import com.example.android_shopping.module.data.GoodsBean;
import com.example.android_shopping.module.data.HomeBean;
import com.example.android_shopping.module.googs.GoogsModel;

public class GoodsPresenter extends BasePresenter<IGoogs.View> implements IGoogs.Presenter {

    IGoogs.View view;
    IGoogs.Model model;

    public GoodsPresenter(IGoogs.View view) {
        this.view = view;
        this.model = new GoogsModel();
    }

    @Override
    public void getGoogs(int id) {
        this.model.getGoods( id, new Callback() {
            @Override
            public void success(Object data) {
                view.getGoogsReturn( (GoodsBean) data );
            }

            @Override
            public void fail(String err) {
                view.showToast( err );
            }
        } );


    }
}
