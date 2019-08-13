package com.walter.cojal.easylearning.presentation.start.view;

import android.content.Intent;
import android.os.Bundle;

import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.base.BaseActivity;
import com.walter.cojal.easylearning.data.Entities.User;
import com.walter.cojal.easylearning.di.component.DaggerPresentationComponent;
import com.walter.cojal.easylearning.di.module.PresentationModule;
import com.walter.cojal.easylearning.presentation.start.IStartContract;
import com.walter.cojal.easylearning.presentation.start.presenter.StartPresenter;
import com.walter.cojal.easylearning.utility.Constant;
import com.walter.cojal.easylearning.utility.SavePreferences;

import javax.inject.Inject;

public class StartActivity extends BaseActivity implements IStartContract.IView {

    @Inject
    StartPresenter presenter;
    @Inject
    SavePreferences savePreferences;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_start;
    }

    @Override
    protected void onViewReady(Bundle saveInstanceState, Intent intent) {
        super.onViewReady(saveInstanceState, intent);
        user = savePreferences.getUser(Constant.USER_KEY);
        presenter.attachView(this);
        if (user != null) {
            goToDashboard();
        } else {
            goToLogin();
        }
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerPresentationComponent.builder()
                .applicationComponent(getApplicationComponent())
                .presentationModule(new PresentationModule())
                .build().inject(this);
    }

    @Override
    public void showError(String errorMsg) {

    }

    @Override
    public void updateSuccess(int code) {

    }

    @Override
    public void showUpdateDialog() {

    }

    @Override
    public void goToLogin() {

    }

    @Override
    public void goToDashboard() {

    }
}
