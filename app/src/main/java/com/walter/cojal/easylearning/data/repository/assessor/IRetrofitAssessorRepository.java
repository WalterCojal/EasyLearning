package com.walter.cojal.easylearning.data.repository.assessor;

import com.walter.cojal.easylearning.data.entities.Result;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public interface IRetrofitAssessorRepository {

    Observable<Result> getAll(int userId);
    Observable<Result> getAssessorData(int userId);
    Observable<Result> getFavorites(int userId);
    Observable<Result> addFavorite(int userId, int assessorId);
    Observable<Result> getAssessorDetail(int assessorId);
    Observable<Result> addAssessor(RequestBody genre, RequestBody document, RequestBody academic, RequestBody assignments, int userId);
    Observable<Result> getLists();
}
