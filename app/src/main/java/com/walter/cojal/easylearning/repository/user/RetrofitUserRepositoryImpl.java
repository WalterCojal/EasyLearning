package com.walter.cojal.easylearning.repository.user;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.data.Entities.User;
import com.walter.cojal.easylearning.network.ServiceApi;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class RetrofitUserRepositoryImpl implements IUserRepository {

    ServiceApi serviceApi;
    Scheduler uiThread;
    Scheduler executorThread;

    @Inject
    public RetrofitUserRepositoryImpl(ServiceApi serviceApi, Scheduler uiThread, Scheduler executorThread) {
        this.serviceApi = serviceApi;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<Result> create(User user) {
        return serviceApi.signup(user)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }

    @Override
    public Observable<Result> update(User user) {
        return null;
    }

    @Override
    public Observable<Result> read(int userId) {
        return null;
    }

    @Override
    public Observable<Result> delete(int userId) {
        return null;
    }
}
