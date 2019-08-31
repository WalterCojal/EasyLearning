package com.walter.cojal.easylearning.domain.assessor_interactor;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface IAssessorInteractor {

    User getUser();
    Observable<Result> addAssessor(RequestBody genre, RequestBody document, RequestBody academic, RequestBody assignments, int userId);
    Observable<Result> addImage(MultipartBody.Part image, int userId);

}
