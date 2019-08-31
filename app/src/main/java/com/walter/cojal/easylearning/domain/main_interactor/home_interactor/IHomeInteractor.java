package com.walter.cojal.easylearning.domain.main_interactor.home_interactor;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;

import io.reactivex.Observable;

public interface IHomeInteractor {

    Observable<Result> getHomeData(int userId);
    Observable<Result> addFavorite(int userId, int assessorId);
    User getUser();

}
