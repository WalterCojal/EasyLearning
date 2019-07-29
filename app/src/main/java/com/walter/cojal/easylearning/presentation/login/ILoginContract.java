package com.walter.cojal.easylearning.presentation.login;

import com.walter.cojal.easylearning.data.Entities.User;

interface ILoginContract {

    interface IView {
        void showProgress();
        void hideProgress();
        void showError();
        void validate();
        void loginSuccess(User user);
        void loginError(String message, int status);
    }

    interface IPresenter {
        void attachView();
        void dettachView();
        Boolean isViewAttaached();
        void login(String email, String password,String token);
    }

}
