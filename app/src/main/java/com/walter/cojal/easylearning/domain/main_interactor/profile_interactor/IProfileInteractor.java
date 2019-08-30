package com.walter.cojal.easylearning.domain.main_interactor.profile_interactor;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;

import io.reactivex.Observable;

public interface IProfileInteractor {

    Observable<Result> getAssessorData(int userId);
    Observable<Result> updateUserData(User user);
    User getUser();
    void saveUser(User user);

}
