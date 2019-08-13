package com.walter.cojal.easylearning.presentation.signup.presenter;

import com.walter.cojal.easylearning.data.Entities.User;
import com.walter.cojal.easylearning.domain.signup_interactor.ISignupInteractor;
import com.walter.cojal.easylearning.presentation.signup.ISignupContract;

import javax.inject.Inject;

public class SignupPresenter implements ISignupContract.IPresenter {

    ISignupInteractor interactor;

    @Inject
    public SignupPresenter(ISignupInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(ISignupContract.IView view) {

    }

    @Override
    public void dettachView() {

    }

    @Override
    public Boolean isViewAttached() {
        return null;
    }

    @Override
    public void signup(User user) {

    }
}
