package com.walter.cojal.easylearning.domain.start_interactor;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;

import io.reactivex.Observable;

public interface IStartInteractor {

    Observable<Result> updateData();
    User getUser();

}
