package com.walter.cojal.easylearning.domain.login_interactor;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;

import io.reactivex.Observable;

public interface ILoginInteractor {

    Observable<Result> login(String email, String password, String token);
    Observable<Result> validatePhone(String phone);
    void saveUser(User user);
    void saveApiToken(String token);

}
