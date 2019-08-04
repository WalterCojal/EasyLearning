package com.walter.cojal.easylearning.presentation.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.walter.cojal.easylearning.MyApplication;
import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.data.Entities.User;
import com.walter.cojal.easylearning.presentation.home.view.HomeActivity;
import com.walter.cojal.easylearning.presentation.login.ILoginContract;
import com.walter.cojal.easylearning.presentation.login.presenter.LoginPresenter;
import com.walter.cojal.easylearning.utility.Constant;
import com.walter.cojal.easylearning.utility.SavePreferences;
import com.walter.cojal.easylearning.utility.Util;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements ILoginContract.IView {

    Button signin;
    EditText txtEmail, txtPassword;
    LoginStatusFragment statusFragment;
    @Inject LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MyApplication application = (MyApplication) getApplication();
        application.getAppComponent().inject(this);
        txtEmail = findViewById(R.id.login_email);
        txtPassword = findViewById(R.id.login_password);
        signin = findViewById(R.id.login_main);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // presenter.login(txtEmail.getText().toString(), txtPassword.getText().toString(), "");
                goToDashboard();
            }
        });
        presenter.attachView(this);
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
    public Boolean validate() {
        if (txtEmail.getText().toString().isEmpty()) {
            txtEmail.setError("Ingresar correo electrónico");
            txtEmail.requestFocus();
            return false;
        }
        if (Util.verifyEmail(txtEmail.getText().toString())) {
            txtEmail.setError("Correo electrónico inválido");
            txtEmail.requestFocus();
            return false;
        }
        if (txtPassword.getText().toString().isEmpty()) {
            txtPassword.setError("Ingresar password");
            txtPassword.requestFocus();
            return false;
        }
        return true;
    }

    @Override

    public void loginSuccess(User user) {
        SavePreferences savePreferences = new SavePreferences(this);
        savePreferences.saveUser(Constant.USER_KEY, user);
        goToDashboard();
    }

    @Override
    public void goToDashboard() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginError(String message, int status) {
        statusFragment = new LoginStatusFragment(message, status);
        statusFragment.show(getSupportFragmentManager(), "LoginStatusFragment");
        statusFragment.setOnClickListener(new OnLoginStatusClick() {
            @Override
            public void onClick(View view, int status) {
                switch (view.getId()) {
                    case R.id.fls_agree: {
                        break;
                    }
                    case R.id.fls_cancel: {
                        break;
                    }
                }
            }
        });
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
