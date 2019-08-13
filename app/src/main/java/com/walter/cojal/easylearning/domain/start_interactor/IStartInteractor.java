package com.walter.cojal.easylearning.domain.start_interactor;

public interface IStartInteractor {

    interface UpdateCallBack {
        void onSuccess(int code);
        void onError(String errorMsg);
    }

    void updateData(UpdateCallBack callBack);

}
