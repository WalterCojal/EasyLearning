package com.walter.cojal.easylearning.data.repository.assessor;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.network.ServiceApi;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public class RetrofitAssessorRepositoryImpl implements IRetrofitAssessorRepository {

    ServiceApi serviceApi;

    @Inject
    public RetrofitAssessorRepositoryImpl(ServiceApi serviceApi) {
        this.serviceApi = serviceApi;
    }

    @Override
    public Observable<Result> getAll(int userId) {
        return serviceApi.getHomeData(userId);
    }

    @Override
    public Observable<Result> getAssessorData(int userId) {
        return serviceApi.getAssessorData(userId);
    }

    @Override
    public Observable<Result> getFavorites(int userId) {
        return serviceApi.getFavorites(userId);
    }

    @Override
    public Observable<Result> addFavorite(int userId, int assessorId) {
        return serviceApi.addFavorite(userId, assessorId);
    }

    @Override
    public Observable<Result> getAssessorDetail(int assessorId) {
        return serviceApi.getAssessorDetail(assessorId);
    }

    @Override
    public Observable<Result> addAssessor(RequestBody genre, RequestBody document, RequestBody academic, RequestBody assignments, int userId) {
        return serviceApi.addAssessor(genre, document, academic, assignments, userId);
    }
}
