package com.walter.cojal.easylearning.domain.main_interactor.home_interactor;

import com.walter.cojal.easylearning.data.entities.Result;

import io.reactivex.Observable;

public interface IHomeInteractor {

    Observable<Result> getHomeData();

}
