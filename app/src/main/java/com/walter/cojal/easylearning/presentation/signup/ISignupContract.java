package com.walter.cojal.easylearning.presentation.signup;

import com.walter.cojal.easylearning.data.Entities.User;

public interface ISignupContract {

    interface IView {
        void showProgress();
        void hideProgress();
        void showError(String error);
        void signupSuccess(String message);
        void signupError(String message, int code);
        void validate();
    }

    interface IPresenter {
        void attachView(IView view);
        void dettachView();
        Boolean isViewAttached();
        void signup(User user);
    }

}