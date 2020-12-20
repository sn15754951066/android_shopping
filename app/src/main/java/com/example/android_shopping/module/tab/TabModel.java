package com.example.android_shopping.module.tab;

import com.example.android_shopping.api.ServiceApi;
import com.example.android_shopping.base.BaseModel;
import com.example.android_shopping.interfaces.Callback;
import com.example.android_shopping.interfaces.tab.ITab;
import com.example.android_shopping.module.data.TabBean;
import com.example.android_shopping.net.CommonSubscriber;
import com.example.android_shopping.net.HttpManager;
import com.example.android_shopping.utils.RxUtils;

public class TabModel extends BaseModel implements ITab.Model {

    private ServiceApi service;

    @Override
    public void getTab(String url,Callback callback) {
        service = HttpManager.getInstance().getService();
        CommonSubscriber<TabBean> commonSubscriber = service.getTab(url).compose( RxUtils.rxScheduler() )
                .subscribeWith( new CommonSubscriber<TabBean>( callback ) {
                    @Override
                    public void onNext(TabBean tabBean) {
                        callback.success( tabBean );
                    }
                } );
        addDisposible( commonSubscriber );

    }


}
