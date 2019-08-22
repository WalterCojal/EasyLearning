package com.walter.cojal.easylearning.repository.auth;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.network.ServiceApi;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class RetrofitAuthRepositoryImpl implements IAuthRepository {

    ServiceApi serviceApi;
    Scheduler uiThread;
    Scheduler executorThread;

    @Inject
    public RetrofitAuthRepositoryImpl(ServiceApi serviceApi, Scheduler uiThread, Scheduler executorThread) {
        this.serviceApi = serviceApi;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<Result> login(String email, String password, String token) {
        return serviceApi.login(email, password, token)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
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
        return serviceApi.validatePhone(phone)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}
