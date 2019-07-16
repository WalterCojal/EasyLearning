package com.walter.cojal.easylearning.domain.splash_interactor;

import com.walter.cojal.easylearning.network.ApiClient;
import com.walter.cojal.easylearning.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashInteractorImpl implements ISplashInteractor{

    @Override
    public void getUserData(UserCallBack callBack) {

    }

    @Override
    public void getUpdateData(final UpdateCallBack callBack) {
        ServiceApi api = ApiClient.client().create(ServiceApi.class);
        Call<Integer> call = api.getInitData();
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onError("Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                callBack.onError(t.getLocalizedMessage());
            }
        });
    }
}
