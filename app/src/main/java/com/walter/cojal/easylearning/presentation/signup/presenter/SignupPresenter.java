package com.walter.cojal.easylearning.presentation.signup.presenter;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.data.Entities.User;
import com.walter.cojal.easylearning.domain.signup_interactor.ISignupInteractor;
import com.walter.cojal.easylearning.presentation.signup.ISignupContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SignupPresenter implements ISignupContract.IPresenter {

    ISignupInteractor interactor;
    ISignupContract.IView view;
    Disposable disposable;

    @Inject
    public SignupPresenter(ISignupInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(ISignupContract.IView view) {
        this.view = view;
    }

    @Override
    public void dettachView() {
        view = null;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public Boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void signup(User user) {
        view.showProgress();
        interactor.signUp(user).subscribe(new Observer<Result>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Result result) {
                if (isViewAttached()) {
                    view.hideProgress();
                    if (result.isSuccess()) {
                        view.signupSuccess(result.getMessage());
                    } else {
                        view.signupError(result.getMessage(), result.getCode());
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
