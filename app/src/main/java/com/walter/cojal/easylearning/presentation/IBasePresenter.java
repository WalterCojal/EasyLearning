package com.walter.cojal.easylearning.presentation;

public interface IBasePresenter {

    void attachView(IBaseView view);
    void detachView();
    boolean isViewAttached();

}
