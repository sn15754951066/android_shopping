package com.example.android_shopping.interfaces.googs;

import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.IBaseModel;
import com.example.android_shopping.interfaces.IBasePresenter;
import com.example.android_shopping.interfaces.IBaseView;
import com.example.android_shopping.module.data.GoodsBean;

public interface IGoogs {
    /**
     * 契约类
     */

    interface View extends IBaseView {
        void getGoogsReturn(GoodsBean goodsBean);
    }

    interface Presenter extends IBasePresenter<IGoogs.View> {
        void getGoogs(int id);
    }


    interface Model extends IBaseModel {
        void getGoods(int id,Callback callback);
    }
}
