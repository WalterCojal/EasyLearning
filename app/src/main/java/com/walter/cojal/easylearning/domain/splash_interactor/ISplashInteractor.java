package com.walter.cojal.easylearning.domain.splash_interactor;

import com.walter.cojal.easylearning.data.Entities.User;

public interface ISplashInteractor {

    interface UserCallBack {
        void onSuccess(User user);
        void onError(String errorMsg);
    }

    interface UpdateCallBack {
        void onSuccess(int code);
        void onError(String errorMsg);
    }

    void getUserData(UserCallBack callBack);
    void getUpdateData(UpdateCallBack callBack);

}
