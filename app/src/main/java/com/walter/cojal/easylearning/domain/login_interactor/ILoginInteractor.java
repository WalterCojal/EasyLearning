package com.walter.cojal.easylearning.domain.login_interactor;

import com.walter.cojal.easylearning.data.Entities.Result;

import io.reactivex.Observable;

public interface ILoginInteractor {

    Observable<Result> login(String email, String password, String token);
    Observable<Result> sendEmail(String email);
    Observable<Result> validatePhone(String phone);

}
