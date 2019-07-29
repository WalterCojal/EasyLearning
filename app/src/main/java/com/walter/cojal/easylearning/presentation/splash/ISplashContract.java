package com.walter.cojal.easylearning.presentation.splash;

import android.content.Context;

import com.walter.cojal.easylearning.data.Entities.User;

public interface ISplashContract {

    interface IView {
        void showError(String errorMsg);
        void getUpdateSuccess(int code);
        void getUser(User user);
        void showUpdateDialog();
        void goToLogin();
        void goToDashboard();
    }

    interface IPresenter {
        void attachView(IView view);
        void dettachView();
        boolean isAttached();
        void getUpdate();
        void getDataUser(Context context, String key);
    }

}
