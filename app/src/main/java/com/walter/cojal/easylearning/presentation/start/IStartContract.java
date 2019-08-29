package com.walter.cojal.easylearning.presentation.start;

import com.walter.cojal.easylearning.presentation.IBasePresenter;
import com.walter.cojal.easylearning.presentation.IBaseView;

public interface IStartContract {

    interface IView extends IBaseView {
        void updateSuccess(int code);
        void showUpdateDialog();
        void goToLogin();
        void goToDashboard();
    }

    interface IPresenter extends IBasePresenter {
        void isUserLogged();
        void update();
    }

}
