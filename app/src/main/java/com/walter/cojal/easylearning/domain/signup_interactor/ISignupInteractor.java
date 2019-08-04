package com.walter.cojal.easylearning.domain.signup_interactor;

import com.walter.cojal.easylearning.data.Entities.User;

public interface ISignupInteractor {

    interface SignupCallback {
        void onSuccess(String message);
        void onSignError(String message, int status);
        void onError(String error);
    }

    void signUp(User user, SignupCallback callback);

}
