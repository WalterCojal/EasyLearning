package com.walter.cojal.easylearning.repository.user;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.data.Entities.User;

import io.reactivex.Observable;

public interface IUserRepository {

    Observable<Result> create(User user);
    Observable<Result> update(User user);
    Observable<Result> read(int userId);
    Observable<Result> delete(int userId);

}
