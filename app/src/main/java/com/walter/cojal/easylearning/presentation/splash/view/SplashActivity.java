package com.walter.cojal.easylearning.presentation.splash.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.walter.cojal.easylearning.MyApplication;
import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.data.Entities.User;
import com.walter.cojal.easylearning.domain.splash_interactor.SplashInteractorImpl;
import com.walter.cojal.easylearning.presentation.home.view.HomeActivity;
import com.walter.cojal.easylearning.presentation.login.view.LoginActivity;
import com.walter.cojal.easylearning.presentation.splash.ISplashContract;
import com.walter.cojal.easylearning.presentation.splash.presenter.SplashPresenter;
import com.walter.cojal.easylearning.utility.Constant;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity implements ISplashContract.IView, Constant {

    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        presenter = new SplashPresenter(new SplashInteractorImpl());
        presenter.attachView(this);
        presenter.getDataUser(this, USER_KEY);
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getUpdateSuccess(int code) {

    }

    @Override
    public void getUser(User user) {
        if (user == null) goToLogin();
        else goToDashboard();
    }

    @Override
    public void showUpdateDialog() {

    }

    @Override
    public void goToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void goToDashboard() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.dettachView();
    }

    @Override
    protected void onDestroy() {
        presenter.dettachView();
        super.onDestroy();
    }
}
