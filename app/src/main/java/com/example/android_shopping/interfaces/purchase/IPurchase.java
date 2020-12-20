package com.example.android_shopping.interfaces.purchase;

import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.IBaseModel;
import com.example.android_shopping.interfaces.IBasePresenter;
import com.example.android_shopping.interfaces.IBaseView;
import com.example.android_shopping.interfaces.hot.IHotGoodList;
import com.example.android_shopping.module.data.HotBean;
import com.example.android_shopping.module.data.HotGoodListBean;
import com.example.android_shopping.module.purchasedetails.GoodsDetail;
import com.example.android_shopping.module.purchasedetails.GoodsRelated;

import java.util.HashMap;

public interface IPurchase {
    /**
     * 契约类
     */

    interface View extends IBaseView {
        void getGoodsDetailReturn(GoodsDetail goodsDetail);

        void getGoodsRelatedReturn(GoodsRelated goodsRelated);
    }

    interface Presenter extends IBasePresenter<IPurchase.View> {
        void getGoodsDetail(int id);

        void getGoodsRelated(int id);
    }


    interface Model extends IBaseModel {
        void getGoodsDetail(int id, Callback callback);

        void getGoodsRelated(int id, Callback callback);
    }
}
