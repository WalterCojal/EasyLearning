package com.walter.cojal.easylearning.data.repository.assessor;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.network.ServiceApi;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RetrofitRetrofitAssessorRepositoryImpl implements IRetrofitAssessorRepository {

    ServiceApi serviceApi;

    @Inject
    public RetrofitRetrofitAssessorRepositoryImpl(ServiceApi serviceApi) {
        this.serviceApi = serviceApi;
    }

    @Override
    public Observable<Result> getAll() {
        return serviceApi.getHomeData();
    }

    @Override
    public Observable<Result> getAssessorData(int userId) {
        return serviceApi.getAssessorData(userId);
    }

    @Override
    public Observable<Result> getFavorites(int userId) {
        return serviceApi.getFavorites(userId);
    }
}
