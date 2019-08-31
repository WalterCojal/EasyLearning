package com.walter.cojal.easylearning.domain.main_interactor.profile_interactor;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

public interface IProfileInteractor {

    Observable<Result> getAssessorData(int userId);
    Observable<Result> updateUserData(User user);
    Observable<Result> updateUserImage(MultipartBody.Part image, int userId);
    User getUser();
    void saveUser(User user);

}
