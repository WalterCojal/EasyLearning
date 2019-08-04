package com.walter.cojal.easylearning.presentation.splash.presenter;

import android.content.Context;

import com.walter.cojal.easylearning.data.Entities.User;
import com.walter.cojal.easylearning.domain.splash_interactor.ISplashInteractor;
import com.walter.cojal.easylearning.domain.splash_interactor.SplashInteractorImpl;
import com.walter.cojal.easylearning.presentation.splash.ISplashContract;
import com.walter.cojal.easylearning.utility.SavePreferences;

import javax.inject.Inject;

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
    public void getDataUser(Context context, String key) {
        SavePreferences savePreferences = new SavePreferences(context);
        User user = savePreferences.getUser(key);
        view.getUser(user);
    }
}
