package com.walter.cojal.easylearning.data.repository.auth;

import com.walter.cojal.easylearning.data.Entities.Result;

import io.reactivex.Observable;

public interface IAuthRepository {

    Observable<Result> login(String email, String password, String token);
    Observable<Result> recover(String email);
    Observable<Result> reset(int userID, String password);
    Observable<Result> validatePhone(String phone);

}
