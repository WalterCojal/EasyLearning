package com.walter.cojal.easylearning.domain.signup_interactor;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;

import io.reactivex.Observable;

public interface ISignupInteractor {

    Observable<Result> signUp(User user);

}
