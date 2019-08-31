package com.walter.cojal.easylearning.data.repository.auth;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.network.ServiceApi;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RetrofitAuthRepositoryImpl implements IAuthRepository {

    ServiceApi serviceApi;

    @Inject
    public RetrofitAuthRepositoryImpl(ServiceApi serviceApi) {
        this.serviceApi = serviceApi;
    }

    @Override
    public Observable<Result> login(String email, String password, String token) {
        return serviceApi.login(email, password, token);
    }

    @Override
    public Observable<Result> recover(String email) {
        return null;
    }

    @Override
    public Observable<Result> reset(int userID, String password) {
        return null;
    }

    @Override
    public Observable<Result> validatePhone(String phone) {
        return serviceApi.validatePhone(phone);
    }

    @Override
    public Observable<Result> updateData() {
        return serviceApi.getInitData();
    }
}
