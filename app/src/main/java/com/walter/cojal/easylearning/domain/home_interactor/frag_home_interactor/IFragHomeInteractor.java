package com.walter.cojal.easylearning.domain.home_interactor.frag_home_interactor;

import com.walter.cojal.easylearning.data.Entities.Result;

import io.reactivex.Observable;

public interface IFragHomeInteractor {

    Observable<Result> getHomeData();

}
