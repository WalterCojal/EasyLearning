package com.walter.cojal.easylearning.presentation.signup;

import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.presentation.IBasePresenter;
import com.walter.cojal.easylearning.presentation.IBaseView;

public interface ISignUpContract {

    interface IView extends IBaseView {
        void signUpError(String message, int code);
        Boolean validate();
        void goToLogin();
        void setupViews();
        void setupListeners();
    }

    interface IPresenter extends IBasePresenter {
        void signUp(String name, String lastName, String email, String phone, String password);
    }

}
