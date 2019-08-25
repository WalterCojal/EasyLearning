package com.walter.cojal.easylearning.data.repository.asesor;

import com.walter.cojal.easylearning.data.Entities.Result;

import io.reactivex.Observable;

public interface IRetrofitAsesorRepository {

    Observable<Result> getAll();

}
