package com.walter.cojal.easylearning.domain.signup_interactor;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.data.Entities.User;

import io.reactivex.Observable;

public interface ISignupInteractor {

    Observable<Result> signUp(User user);

}
