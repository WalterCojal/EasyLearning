package com.walter.cojal.easylearning.data.repository.user;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;

import io.reactivex.Observable;

public interface IRetrofitUserRepository {

    Observable<Result> create(User user);
    Observable<Result> update(User user);
    Observable<Result> read(int userId);
    Observable<Result> delete(int userId);

}
