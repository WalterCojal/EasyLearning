package com.walter.cojal.easylearning.presentation.login.presenter;

import com.walter.cojal.easylearning.data.Entities.User;
import com.walter.cojal.easylearning.domain.login_interactor.ILoginInteractor;
import com.walter.cojal.easylearning.presentation.login.ILoginContract;
import javax.inject.Inject;

public class LoginPresenter implements ILoginContract.IPresenter {

    private final ILoginInteractor interactor;
    ILoginContract.IView view;

    @Inject
    public LoginPresenter(ILoginInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(ILoginContract.IView view) {
        this.view = view;
    }

    @Override
    public void dettachView() {
        view = null;
    }

    @Override
    public Boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void login(String email, String password, String token) {
        if (!isViewAttached()) {
            return;
        }
        if (!view.validate()) {
            return;
        }

        view.showProgress();
        interactor.login(email, password, token, new ILoginInteractor.LoginCallback() {
            @Override
            public void onSuccess(User user) {
                view.loginSuccess(user);
            }

            @Override
            public void onLoginError(String message, int status) {
                view.hideProgress();
                view.loginError(message, status);
            }

            @Override
            public void onError(String error) {
                view.hideProgress();
                view.showError(error);
            }
        });

    }
}
