package com.walter.cojal.easylearning.presentation.splash.presenter;

import com.walter.cojal.easylearning.presentation.splash.ISplashContract;

public class SplashPresenter implements ISplashContract.IPresenter {

    private ISplashContract.IView view;

    @Override
    public void attachView(ISplashContract.IView view) {
        this.view = view;
    }

    @Override
    public void dettachView() {
        view = null;
    }

    @Override
    public boolean isAttached() {
        return view != null;
    }

    @Override
    public void getUpdate() {

    }

    @Override
    public void getUser() {

    }
}
