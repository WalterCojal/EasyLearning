package com.walter.cojal.easylearning.presentation.signup.presenter;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.domain.signup_interactor.ISignupInteractor;
import com.walter.cojal.easylearning.presentation.IBaseView;
import com.walter.cojal.easylearning.presentation.signup.ISignUpContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SignUpPresenter implements ISignUpContract.IPresenter {

    ISignupInteractor interactor;
    ISignUpContract.IView view;
    Disposable disposable;

    @Inject
    public SignUpPresenter(ISignupInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(IBaseView view) {
        this.view = (ISignUpContract.IView) view;
    }

    @Override
    public void detachView() {
        view = null;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void signUp(String name, String lastName, String email, String phone, String password) {

        if (!view.validate()) return;

        view.showProgress();
        User user = new User(name, lastName, email, phone, password);
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
                        view.showSuccess(result.getMessage());
                        view.goToLogin();
                    } else {
                        view.signUpError(result.getMessage(), result.getCode());
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
