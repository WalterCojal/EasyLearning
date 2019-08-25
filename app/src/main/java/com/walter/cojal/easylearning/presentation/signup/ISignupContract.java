package com.walter.cojal.easylearning.presentation.signup;

import com.walter.cojal.easylearning.data.Entities.User;

public interface ISignupContract {

    interface IView {
        void showProgress();
        void hideProgress();
        void showError(String error);
        void signUpSuccess(String message);
        void signUpError(String message, int code);
        Boolean validate();
        void goToLogin();
        void setupViews();
        void setupListeners();
    }

    interface IPresenter {
        void attachView(IView view);
        void detachView();
        Boolean isViewAttached();
        void signUp(User user);
    }

}
