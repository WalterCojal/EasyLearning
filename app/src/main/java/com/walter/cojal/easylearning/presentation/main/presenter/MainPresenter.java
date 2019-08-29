package com.walter.cojal.easylearning.presentation.main.presenter;

import com.walter.cojal.easylearning.presentation.IBaseView;
import com.walter.cojal.easylearning.presentation.main.IMainContract;

public class MainPresenter implements IMainContract.IPresenter {

    IMainContract.IView view;

    @Override
    public void attachView(IBaseView view) {
        this.view = (IMainContract.IView) view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }
}
