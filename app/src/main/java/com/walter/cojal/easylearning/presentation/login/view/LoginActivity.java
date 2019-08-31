package com.walter.cojal.easylearning.presentation.login.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.facebook.accountkit.ui.SkinManager;
import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.base.BaseActivity;
import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.di.component.DaggerPresentationComponent;
import com.walter.cojal.easylearning.di.module.PresentationModule;
import com.walter.cojal.easylearning.presentation.main.view.MainActivity;
import com.walter.cojal.easylearning.presentation.login.ILoginContract;
import com.walter.cojal.easylearning.presentation.login.presenter.LoginPresenter;
import com.walter.cojal.easylearning.presentation.signup.view.SignupActivity;
import com.walter.cojal.easylearning.utility.Utils;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements ILoginContract.IView {

    Button signin, signup;
    EditText txtEmail, txtPassword;
    LoginStatusFragment statusFragment;
    public static int APP_REQUEST_CODE = 99;
    @Inject
    LoginPresenter presenter;
    @Inject
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onViewReady(Bundle saveInstanceState, Intent intent) {
        super.onViewReady(saveInstanceState, intent);
        presenter.attachView(this);
        setupViews();
        setupListeners();
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
        txtEmail = findViewById(R.id.login_email);
        txtPassword = findViewById(R.id.login_password);
        signin = findViewById(R.id.login_main);
        signup = findViewById(R.id.login_signup);
    }

    @Override
    public void setupListeners() {
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(txtEmail.getText().toString(), txtPassword.getText().toString(), "");
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUp();
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
    public Boolean validate() {
        if (txtEmail.getText().toString().isEmpty()) {
            txtEmail.setError("Ingresar correo electrónico");
            txtEmail.requestFocus();
            return false;
        }
        if (!Utils.verifyEmail(txtEmail.getText().toString())) {
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
    public void accountKitLogin() {
        final Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.TOKEN);
        // ... perform additional configuration ...

        SkinManager manager = new SkinManager(
                SkinManager.Skin.CONTEMPORARY,
                getResources().getColor(R.color.colorPrimary),
                R.drawable.splash,
                SkinManager.Tint.WHITE,
                0.7
        );
        configurationBuilder.setUIManager(manager);
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        startActivityForResult(intent, APP_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE) {
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String message;
            if (loginResult.getError() != null) {
                message = loginResult.getError().getErrorType().getMessage();
            } else if (loginResult.wasCancelled()) {
                message = "Inicio de sesión cancelado";
            } else {
                if (loginResult.getAccessToken() != null) {
                    message = "Inició sesión: " + loginResult.getAccessToken().getAccountId();
                } else {
                    message = String.format("Inicio de sesión:%s...", loginResult.getAuthorizationCode().substring(0,10));
                }
            }
            Log.i(getLocalClassName(), message);
            getAccountKitData();
        }
    }

    private void getAccountKitData() {
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(Account account) {
                String number = account.getPhoneNumber().toString();
            }

            @Override
            public void onError(AccountKitError accountKitError) {
                showError(accountKitError.toString());
                AccountKit.logOut();
            }
        });
    }

    @Override
    public void loginSuccess(User user, String apiToken) {
        goToDashboard();
    }

    @Override
    public void goToDashboard() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToSignUp() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginError(String message, int status) {
        statusFragment = new LoginStatusFragment();
        statusFragment.show(getSupportFragmentManager(), "LoginStatusFragment");
        statusFragment.setMessage(message);
        statusFragment.setAgreeText(getString(R.string.agree));
        switch (status) {
            case 0: {
                statusFragment.setCancelText(getString(R.string.create_account));
                break;
            }
            case 1: {
                statusFragment.setCancelText(getString(R.string.recover_password));
                break;
            }
        }
        statusFragment.setOnClickListener(new OnLoginStatusClick() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.fls_agree: {
                        statusFragment.dismiss();
                        break;
                    }
                    case R.id.fls_cancel: {
                        statusFragment.dismiss();
                        break;
                    }
                }
            }
        });
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
