package com.walter.cojal.easylearning.domain.start_interactor;

import com.walter.cojal.easylearning.data.Entities.Result;

import io.reactivex.Observable;

public interface IStartInteractor {

    Observable<Result> updateData();

}
