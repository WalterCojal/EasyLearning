package com.walter.cojal.easylearning.presentation.signup.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.base.BaseActivity;
import com.walter.cojal.easylearning.di.component.DaggerPresentationComponent;
import com.walter.cojal.easylearning.di.module.PresentationModule;
import com.walter.cojal.easylearning.presentation.signup.ISignUpContract;
import com.walter.cojal.easylearning.presentation.signup.presenter.SignUpPresenter;
import com.walter.cojal.easylearning.utility.Utils;

import javax.inject.Inject;

public class SignupActivity extends BaseActivity implements ISignUpContract.IView {

    @Inject
    SignUpPresenter presenter;
    @Inject
    ProgressDialog progressDialog;
    EditText edtName, edtLastName, edtEmail, edtPhone, edtPassword;
    Button btnSignUp;

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
        setupViews();
        setupListeners();
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
    public void setupViews() {
        edtName = findViewById(R.id.sign_name);
        edtLastName = findViewById(R.id.sign_lastname);
        edtEmail = findViewById(R.id.sign_email);
        edtPhone = findViewById(R.id.sign_phone);
        edtPassword = findViewById(R.id.sign_password);
        btnSignUp = findViewById(R.id.sign_agree);
    }

    @Override
    public void setupListeners() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.signUp(edtName.getText().toString(),
                        edtLastName.getText().toString(),
                        edtEmail.getText().toString(),
                        edtPhone.getText().toString(),
                        edtPassword.getText().toString());
            }
        });
    }

    @Override
    public void showProgress() {
        progressDialog.setMessage("Espere...");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToLogin() {
        onBackPressed();
    }

    @Override
    public Boolean validate() {
        if (edtName.getText().toString().isEmpty()) {
            edtName.setError("Ingrese su nombre");
            edtName.requestFocus();
            return false;
        }
        if (edtLastName.getText().toString().isEmpty()) {
            edtLastName.setError("Ingrese su apellido");
            edtLastName.requestFocus();
            return false;
        }
        if (edtPhone.getText().toString().isEmpty()) {
            edtPhone.setError("Ingrese su número de celular");
            edtPhone.requestFocus();
            return false;
        }
        if (edtEmail.getText().toString().isEmpty()) {
            edtEmail.setError("Ingrese su correo electónico");
            edtEmail.requestFocus();
            return false;
        }
        if (!Utils.verifyEmail(edtEmail.getText().toString())) {
            edtEmail.setError("Ingrese un correo válido");
            edtEmail.requestFocus();
            return false;
        }
        if (edtPassword.getText().toString().isEmpty()) {
            edtPassword.setError("Ingrese una contraseña");
            edtPassword.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void signUpError(String message, int code) {
        showError(message);
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
