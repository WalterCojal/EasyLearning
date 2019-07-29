package com.walter.cojal.easylearning.presentation.signup.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.presentation.signup.ISignupContract;

public class SignupActivity extends AppCompatActivity implements ISignupContract.IView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
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
}
