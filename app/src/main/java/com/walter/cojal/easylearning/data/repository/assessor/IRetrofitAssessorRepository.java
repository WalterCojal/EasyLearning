package com.walter.cojal.easylearning.data.repository.assessor;

import com.walter.cojal.easylearning.data.entities.Result;

import io.reactivex.Observable;

public interface IRetrofitAssessorRepository {

    Observable<Result> getAll();
    Observable<Result> getAssessorData(int userId);
    Observable<Result> getFavorites(int userId);
}
