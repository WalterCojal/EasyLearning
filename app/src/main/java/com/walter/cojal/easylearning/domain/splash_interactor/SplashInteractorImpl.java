package com.walter.cojal.easylearning.domain.splash_interactor;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.network.ApiClient;
import com.walter.cojal.easylearning.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashInteractorImpl implements ISplashInteractor{

    @Override
    public void getUpdateData(final UpdateCallBack callBack) {
        ServiceApi api = ApiClient.client().create(ServiceApi.class);
        Call<Result> call = api.getInitData();
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    Result result = response.body();
                    if (result != null && result.isSuccess()) {
                        callBack.onSuccess(result.getCode());
                    } else {
                        callBack.onError(result.getMessage());
                    }
                } else {
                    callBack.onError("Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                callBack.onError(t.getLocalizedMessage());
            }
        });
    }
}
