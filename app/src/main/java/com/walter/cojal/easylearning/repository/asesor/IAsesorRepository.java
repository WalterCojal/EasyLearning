package com.walter.cojal.easylearning.repository.asesor;

import com.walter.cojal.easylearning.data.Entities.Result;

import io.reactivex.Observable;

public interface IAsesorRepository {

    Observable<Result> getAll();

}
