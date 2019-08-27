package com.walter.cojal.easylearning.data.repository.user;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.data.Entities.User;
import com.walter.cojal.easylearning.network.ServiceApi;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RetrofitRetrofitUserRepositoryImpl implements IRetrofitUserRepository {

    ServiceApi serviceApi;

    @Inject
    public RetrofitRetrofitUserRepositoryImpl(ServiceApi serviceApi) {
        this.serviceApi = serviceApi;
    }

    @Override
    public Observable<Result> create(User user) {
        return serviceApi.signup(user);
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
