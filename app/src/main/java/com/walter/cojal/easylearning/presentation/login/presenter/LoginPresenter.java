package com.walter.cojal.easylearning.presentation.login.presenter;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.domain.login_interactor.ILoginInteractor;
import com.walter.cojal.easylearning.presentation.IBaseView;
import com.walter.cojal.easylearning.presentation.login.ILoginContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginPresenter implements ILoginContract.IPresenter {

    private final ILoginInteractor interactor;
    ILoginContract.IView view;
    Disposable disposable;

    @Inject
    public LoginPresenter(ILoginInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(IBaseView view) {
        this.view = (ILoginContract.IView) view ;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void login(String email, String password, String token) {
        if (!view.validate()) return;
        view.showProgress();
        interactor.login(email, password, token).subscribe(new Observer<Result>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Result result) {
                if (isViewAttached()) {
                    view.hideProgress();
                    if (result.isSuccess()) {
                        interactor.saveUser(result.getUser());
                        interactor.saveUserId(result.getUser().getId());
                        interactor.saveApiToken(result.getApiToken());
                        view.loginSuccess(result.getUser(), result.getApiToken());
                    } else {
                        view.loginError(result.getMessage(), result.getCode());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()) {
                    view.hideProgress();
                    view.showError(e.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });

    }
}
