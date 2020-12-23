package com.example.android_shopping.presenter.tab;

import com.example.android_shopping.base.BasePresenter;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.home.IHome;
import com.example.android_shopping.interfaces.tab.ITab;
import com.example.android_shopping.module.data.TabBean;
import com.example.android_shopping.module.tab.TabModel;

public class TabPresenter extends BasePresenter<ITab.View> implements ITab.Presenter {

    ITab.View view;
    ITab.Model model;

    public TabPresenter(ITab.View view) {
        this.view = view;
        this.model=new TabModel();
    }


    @Override
    public void getTab(int id) {
        if(view!=null){
            this.model.getTab( id, new Callback() {
                @Override
                public void success(Object data) {
                    view.getTabReturn( (TabBean) data );
                }

                @Override
                public void fail(String err) {
                    view.showToast( err );
                }
            } );
        }
    }
}
