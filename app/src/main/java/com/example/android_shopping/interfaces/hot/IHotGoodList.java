package com.example.android_shopping.interfaces.hot;

import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.IBaseModel;
import com.example.android_shopping.interfaces.IBasePresenter;
import com.example.android_shopping.interfaces.IBaseView;
import com.example.android_shopping.module.data.HotBean;
import com.example.android_shopping.module.data.HotGoodListBean;

import java.util.HashMap;

public interface IHotGoodList {
    /**
     * 契约类
     */

    interface View extends IBaseView {
        void getHotReturn(HotBean hotBean);
        void getHotGoodListReturn(HotGoodListBean hotGoodListBean);
    }

    interface Presenter extends IBasePresenter<IHotGoodList.View> {
        void getHot();
        void getHotGoodList(HashMap<String,String> map);
    }


    interface Model extends IBaseModel {
        void getHot(Callback callback);
        void getHotGoodList(HashMap<String,String> map,Callback callback);
    }
}
