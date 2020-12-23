package com.example.android_shopping.presenter.category;

import com.example.android_shopping.base.BasePresenter;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.category.ICategory;
import com.example.android_shopping.module.category.CategoryModule;
import com.example.android_shopping.module.data.CatalogListBase;

public class CategotyPersenter extends BasePresenter<ICategory.View> implements ICategory.Presenter {

    ICategory.View view;
    ICategory.Model model;

    public CategotyPersenter(ICategory.View view) {
        this.view = view;
        this.model=new CategoryModule();
    }

    @Override
    public void getCategory(int id) {
        if(view!=null){
            this.model.getCategory( id, new Callback() {
                @Override
                public void success(Object data) {
                    view.getCategoryReturn( (CatalogListBase) data );
                }

                @Override
                public void fail(String err) {
                    view.showToast( err );
                }
            } );
        }

    }
}
