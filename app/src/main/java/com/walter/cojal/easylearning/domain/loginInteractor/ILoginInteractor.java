package com.walter.cojal.easylearning.domain.loginInteractor;

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

    void login(String email, String password, String token, LoginCallback callback);
    void sendEmail(String email, EmailCallback emailCallback);

}
