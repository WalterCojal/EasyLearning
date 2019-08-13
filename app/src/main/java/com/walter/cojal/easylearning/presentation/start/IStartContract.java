package com.walter.cojal.easylearning.presentation.start;

public interface IStartContract {

    interface IView {
        void showError(String errorMsg);
        void updateSuccess(int code);
        void showUpdateDialog();
        void goToLogin();
        void goToDashboard();
    }

    interface IPresenter {
        void attachView(IView view);
        void dettachView();
        boolean isViewAttached();
        void update();
    }

}
