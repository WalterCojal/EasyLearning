package com.walter.cojal.easylearning.repository.user;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.data.Entities.User;

import io.reactivex.Observable;

public class UserRepositoryImpl implements IUserRepository {

    @Override
    public Observable<Result> create(User user) {
        return null;
    }

    @Override
    public Observable<Result> update(User user) {
        return null;
    }

    @Override
    public Observable<Result> read(int userId) {
        return null;
    }

    @Override
    public Observable<Result> delete(int userId) {
        return null;
    }
}
