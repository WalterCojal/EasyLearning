package com.walter.cojal.easylearning.domain.signup_interactor;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.data.Entities.User;
import com.walter.cojal.easylearning.network.ApiClient;
import com.walter.cojal.easylearning.network.ServiceApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupInteractorImpl implements ISignupInteractor {

    ServiceApi api;

    @Inject
    public SignupInteractorImpl(ServiceApi api) {
        this.api = api;
    }

    @Override
    public void signUp(User user, final SignupCallback callback) {
        api = ApiClient.client().create(ServiceApi.class);
        Call<Result> call = api.signup(user);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    Result result = response.body();
                    if (result.isSuccess()) {
                        callback.onSuccess(result.getMessage());
                    } else {
                        callback.onSignError(result.getMessage(), result.getCode());
                    }
                } else {
                    callback.onError("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                callback.onError("Error: " + t.getLocalizedMessage());
            }
        });
    }
}
