package com.walter.cojal.easylearning.presentation.login;

import com.walter.cojal.easylearning.data.Entities.User;

public interface ILoginContract {

    interface IView {
        void setupViews();
        void setupListener();
        void showProgress();
        void hideProgress();
        void showError(String error);
        Boolean validate();
        void loginSuccess(User user, String apiToken);
        void loginError(String message, int status);
        void goToDashboard();
        void goToSignUp();
        void accountKitLogin();
    }

    interface IPresenter {
        void attachView(IView view);
        void detachView();
        Boolean isViewAttached();
        void login(String email, String password,String token);
    }

}
