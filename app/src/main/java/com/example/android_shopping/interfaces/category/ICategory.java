package com.example.android_shopping.interfaces.category;

import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.IBaseModel;
import com.example.android_shopping.interfaces.IBasePresenter;
import com.example.android_shopping.interfaces.IBaseView;
import com.example.android_shopping.interfaces.googs.IGoogs;
import com.example.android_shopping.module.data.CatalogListBase;
import com.example.android_shopping.module.data.GoodsBean;

public interface ICategory {
    /**
     * 契约类
     */

    interface View extends IBaseView {
        void getCategoryReturn(CatalogListBase catalogListBase);
    }

    interface Presenter extends IBasePresenter<ICategory.View> {
        void getCategory(int id);
    }


    interface Model extends IBaseModel {
        void getCategory(int id, Callback callback);
    }
}
