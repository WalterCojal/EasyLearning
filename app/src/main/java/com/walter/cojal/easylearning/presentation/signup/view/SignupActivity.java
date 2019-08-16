package com.walter.cojal.easylearning.presentation.signup.view;

import android.content.Intent;
import android.os.Bundle;

import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.base.BaseActivity;
import com.walter.cojal.easylearning.di.component.DaggerPresentationComponent;
import com.walter.cojal.easylearning.di.module.PresentationModule;
import com.walter.cojal.easylearning.presentation.signup.ISignupContract;
import com.walter.cojal.easylearning.presentation.signup.presenter.SignupPresenter;

import javax.inject.Inject;

public class SignupActivity extends BaseActivity implements ISignupContract.IView {

    @Inject
    SignupPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_signup;
    }

    @Override
    protected void onViewReady(Bundle saveInstanceState, Intent intent) {
        super.onViewReady(saveInstanceState, intent);
        presenter.attachView(this);
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerPresentationComponent.builder()
                .applicationComponent(getApplicationComponent())
                .presentationModule(new PresentationModule(this))
                .build().inject(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void signupSuccess(String message) {

    }

    @Override
    public void validate() {

    }

    @Override
    public void signupError(String message, int code) {

    }
}
