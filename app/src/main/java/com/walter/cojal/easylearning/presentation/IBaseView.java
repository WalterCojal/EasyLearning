package com.walter.cojal.easylearning.presentation;

public interface IBaseView {

    void setupViews();
    void setupListeners();
    void showProgress();
    void hideProgress();
    void showError(String errorMsg);
    void showSuccess(String message);

}
