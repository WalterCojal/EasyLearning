package com.walter.cojal.easylearning.domain.login_interactor;

import com.walter.cojal.easylearning.data.Entities.User;

public interface ILoginInteractor {

    interface LoginCallback {
        void onSuccess(User user);
        void onLoginError(String message, int status);
        void onError(String error);
    }

    interface EmailCallback {
        void onSuccess(String message);
        void onError(String error);
    }

    interface ValidatePhoneCallback {
        void onUserSuccess(User user);
        void onNewUser();
        void onError(String error);
    }

    void login(String email, String password, String token, LoginCallback callback);
    void sendEmail(String email, EmailCallback emailCallback);
    void validatePhone(String phone, ValidatePhoneCallback callback);

}
