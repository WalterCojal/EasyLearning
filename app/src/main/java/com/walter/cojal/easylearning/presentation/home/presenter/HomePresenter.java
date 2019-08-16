package com.walter.cojal.easylearning.presentation.home.presenter;

import com.walter.cojal.easylearning.presentation.home.IHomeContract;

public class HomePresenter implements IHomeContract.IPresenter {

    IHomeContract.IView view;

    @Override
    public void attachView(IHomeContract.IView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public Boolean isViewAttached() {
        return view != null;
    }
}
