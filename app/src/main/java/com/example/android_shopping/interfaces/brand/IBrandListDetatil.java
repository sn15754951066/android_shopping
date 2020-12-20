package com.example.android_shopping.interfaces.brand;

import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.IBaseModel;
import com.example.android_shopping.interfaces.IBasePresenter;
import com.example.android_shopping.interfaces.IBaseView;
import com.example.android_shopping.module.data.BrandHeadBean;
import com.example.android_shopping.module.data.BrandListDetailsBean;

public interface IBrandListDetatil {
    /**
     * 契约类
     */

    interface View extends IBaseView {


        void getBrandHeadReturn(BrandHeadBean brandHeadBean);
        void getBrandListDetailReturn(BrandListDetailsBean brandListDetailsBean);
    }

    interface Presenter extends IBasePresenter<IBrandListDetatil.View> {


        void getBrandHead(int id);
        void getBrandListDetail(int id);
    }


    interface Model extends IBaseModel {


        void getBrandHead(int id,Callback callback);
        void getBrandListDetail(int id,Callback callback);
    }
}
