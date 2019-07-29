package com.walter.cojal.easylearning.domain.splash_interactor;

public interface ISplashInteractor {

    interface UpdateCallBack {
        void onSuccess(int code);
        void onError(String errorMsg);
    }

    void getUpdateData(UpdateCallBack callBack);

}
