package com.example.android_shopping.interfaces.tab;

import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.IBaseModel;
import com.example.android_shopping.interfaces.IBasePresenter;
import com.example.android_shopping.interfaces.IBaseView;
import com.example.android_shopping.interfaces.home.IHome;
import com.example.android_shopping.module.data.HomeBean;
import com.example.android_shopping.module.data.TabBean;

public interface ITab {
    /**
     * 契约类
     */

    interface View extends IBaseView {
        void getTabReturn(TabBean tabBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getTab(int id);
    }


    interface Model extends IBaseModel {
        void getTab(int id,Callback callback);
    }
}
