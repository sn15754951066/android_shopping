package com.example.android_shopping.interfaces.brand;

import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.IBaseModel;
import com.example.android_shopping.interfaces.IBasePresenter;
import com.example.android_shopping.interfaces.IBaseView;
import com.example.android_shopping.module.data.BrandBean;

public interface IBrand {
    /**
     * 契约类
     */

    interface View extends IBaseView {
        void getBrandReturn(BrandBean brandBean);
    }

    interface Presenter extends IBasePresenter<IBrand.View> {
        void getBrand();
    }


    interface Model extends IBaseModel {
        void getBrand(Callback callback);
    }
}
