package com.walter.cojal.easylearning.data.repository.asesor;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.data.network.ServiceApi;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RetrofitRetrofitAsesorRepositoryImpl implements IRetrofitAsesorRepository {

    ServiceApi serviceApi;

    @Inject
    public RetrofitRetrofitAsesorRepositoryImpl(ServiceApi serviceApi) {
        this.serviceApi = serviceApi;
    }

    @Override
    public Observable<Result> getAll() {
        return null;
    }
}
