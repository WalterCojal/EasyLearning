package com.walter.cojal.easylearning.presentation.start.view;

import android.content.Intent;
import android.os.Bundle;

import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.base.BaseActivity;
import com.walter.cojal.easylearning.di.component.DaggerPresentationComponent;
import com.walter.cojal.easylearning.di.module.PresentationModule;
import com.walter.cojal.easylearning.presentation.main.view.MainActivity;
import com.walter.cojal.easylearning.presentation.login.view.LoginActivity;
import com.walter.cojal.easylearning.presentation.start.IStartContract;
import com.walter.cojal.easylearning.presentation.start.presenter.StartPresenter;

import javax.inject.Inject;

public class StartActivity extends BaseActivity implements IStartContract.IView {

    @Inject
    StartPresenter presenter;

    @Override
    protected int getContentView() {
        return R.layout.activity_start;
    }

    @Override
    protected void onViewReady(Bundle saveInstanceState, Intent intent) {
        super.onViewReady(saveInstanceState, intent);
        presenter.attachView(this);
        setupViews();
        setupListeners();
        presenter.isUserLogged();
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerPresentationComponent.builder()
                .applicationComponent(getApplicationComponent())
                .presentationModule(new PresentationModule(this))
                .build().inject(this);
    }

    @Override
    public void setupViews() {

    }

    @Override
    public void setupListeners() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String errorMsg) {

    }

    @Override
    public void showSuccess(String message) {

    }

    @Override
    public void updateSuccess(int code) {

    }

    @Override
    public void showUpdateDialog() {

    }

    @Override
    public void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToDashboard() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDetachedFromWindow() {
        presenter.detachView();
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
