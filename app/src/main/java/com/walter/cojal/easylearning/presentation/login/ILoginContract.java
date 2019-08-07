package com.walter.cojal.easylearning.presentation.login;

import com.walter.cojal.easylearning.data.Entities.User;

public interface ILoginContract {

    interface IView {
        void showProgress();
        void hideProgress();
        void showError(String error);
        Boolean validate();
        void loginSuccess(User user);
        void loginError(String message, int status);
        void goToDashboard();
        void accountKitLogin();
    }

    interface IPresenter {
        void attachView(IView view);
        void dettachView();
        Boolean isViewAttached();
        void login(String email, String password,String token);
    }

}
