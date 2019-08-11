package com.walter.cojal.easylearning.domain.login_interactor;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.network.ServiceApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractorImpl implements ILoginInteractor {

    ServiceApi api;

    @Inject
    public LoginInteractorImpl(ServiceApi api) {
        this.api = api;
    }

    @Override
    public void login(String email, String password, String token, final LoginCallback callback) {
        Call<Result> call = api.login(email, password, token);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    Result result = response.body();
                    if (result.isSuccess()) {
                        callback.onSuccess(result.getUser());
                    } else {
                        callback.onLoginError(result.getMessage(), result.getCode());
                    }
                } else {
                    callback.onError("Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                callback.onError("Error: " + t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void sendEmail(String email, final EmailCallback emailCallback) {
        Call<Result> call = api.sendEmail(email);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        emailCallback.onSuccess(response.body().getMessage());
                    } else {
                        emailCallback.onError(response.body().getMessage());
                    }
                } else {
                    emailCallback.onError("Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                emailCallback.onError("Error: " + t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void validatePhone(String phone, final ValidatePhoneCallback callback) {
        Call<Result> call = api.validatePhone(phone);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        callback.onUserSuccess(response.body().getUser());
                    } else {
                        callback.onNewUser();
                    }
                } else {
                    callback.onError("Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                callback.onError("Error: " + t.getLocalizedMessage());
            }
        });
    }
}
