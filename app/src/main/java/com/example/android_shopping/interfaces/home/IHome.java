package com.example.android_shopping.interfaces.home;

import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.IBaseModel;
import com.example.android_shopping.interfaces.IBasePresenter;
import com.example.android_shopping.interfaces.IBaseView;
import com.example.android_shopping.module.data.HomeBean;

public interface IHome {
    /**
     * 契约类
     */

    interface View extends IBaseView {
        void getHomeReturn(HomeBean homeBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getHome();
    }


    interface Model extends IBaseModel {
        void getHome(Callback callback);
    }
}
