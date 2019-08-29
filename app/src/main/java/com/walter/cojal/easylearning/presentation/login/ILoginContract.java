package com.walter.cojal.easylearning.presentation.login;

import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.presentation.IBasePresenter;
import com.walter.cojal.easylearning.presentation.IBaseView;

public interface ILoginContract {

    interface IView extends IBaseView {
        Boolean validate();
        void loginSuccess(User user, String apiToken);
        void loginError(String message, int status);
        void goToDashboard();
        void goToSignUp();
        void accountKitLogin();
    }

    interface IPresenter extends IBasePresenter {
        void login(String email, String password,String token);
    }

}
