package combackandroid.mk.group.baseproject.Views.Presenters;

import android.os.Bundle;

import combackandroid.mk.group.baseproject.BaseProject.API.MKGroupAPI;
import combackandroid.mk.group.baseproject.Views.BasePresenter;
import combackandroid.mk.group.baseproject.Views.Fragments.HomeFragment;
import mkgroup.ApplicationController;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<HomeFragment> {
    private int API_DEMO = 3;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        restartableFirst(API_DEMO,
                () -> {
                    return ApplicationController.getInstance()
                            .getRetrofitService(MKGroupAPI.class)
                            .fetchDataContact()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io());
                }
                ,
                (fragment, response) -> {
                    fragment.showLoading(false);
                    fragment.onFetchAPIDemoSuccess(response);
                },
                (fragment, throwable) -> {
                    fragment.showLoading(false);
                    fragment.onFetchAPIDemoError(throwable);
                });
    }

    public void fetchDataApiDemo(){
        start(API_DEMO);
    }
}