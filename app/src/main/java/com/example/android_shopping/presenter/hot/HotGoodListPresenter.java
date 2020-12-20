package com.example.android_shopping.presenter.hot;

import com.example.android_shopping.base.BasePresenter;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.hot.IHotGoodList;
import com.example.android_shopping.module.data.HotBean;
import com.example.android_shopping.module.data.HotGoodListBean;
import com.example.android_shopping.module.hot.HotGoodListModule;

import java.util.HashMap;

public class HotGoodListPresenter extends BasePresenter<IHotGoodList.View> implements IHotGoodList.Presenter {

    IHotGoodList.View view;
    IHotGoodList.Model model;

    public HotGoodListPresenter(IHotGoodList.View view) {
        this.view = view;
        this.model=new HotGoodListModule();
    }

    @Override
    public void getHot() {
        if (view != null) {
            this.model.getHot( new Callback() {
                @Override
                public void success(Object data) {
                    view.getHotReturn( (HotBean) data );
                }

                @Override
                public void fail(String err) {
                    view.showToast( err );
                }
            } );
        }
    }

    @Override
    public void getHotGoodList(HashMap<String, String> map) {
        if(view!=null){
            this.model.getHotGoodList( map, new Callback() {
                @Override
                public void success(Object data) {
                    view.getHotGoodListReturn( (HotGoodListBean) data );
                }

                @Override
                public void fail(String err) {
                    view.showToast( err );
                }
            } );
        }

    }
}
