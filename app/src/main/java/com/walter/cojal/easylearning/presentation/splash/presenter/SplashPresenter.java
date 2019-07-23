package com.walter.cojal.easylearning.presentation.splash.presenter;

import android.content.Context;

import com.walter.cojal.easylearning.data.Entities.User;
import com.walter.cojal.easylearning.domain.splash_interactor.ISplashInteractor;
import com.walter.cojal.easylearning.domain.splash_interactor.SplashInteractorImpl;
import com.walter.cojal.easylearning.presentation.splash.ISplashContract;

public class SplashPresenter implements ISplashContract.IPresenter {

    private ISplashContract.IView view;
    private SplashInteractorImpl interactor;

    public SplashPresenter(SplashInteractorImpl interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(ISplashContract.IView view) {
        this.view = view;
    }

    @Override
    public void dettachView() {
        view = null;
    }

    @Override
    public boolean isAttached() {
        return view != null;
    }

    @Override
    public void getUpdate() {
        interactor.getUpdateData(new ISplashInteractor.UpdateCallBack() {
            @Override
            public void onSuccess(int code) {
                view.getUpdateSuccess(code);
            }

            @Override
            public void onError(String errorMsg) {
                view.showError(errorMsg);
            }
        });
    }

    @Override
    public void getUser(Context context, String key) {
        interactor.getUserData(context, key, new ISplashInteractor.UserCallBack() {
            @Override
            public void onSuccess(User user) {
                view.getUserSuccess(user);
            }

            @Override
            public void onError(String errorMsg) {
                view.showError(errorMsg);
            }
        });
    }
}
