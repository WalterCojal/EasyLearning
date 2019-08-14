package com.walter.cojal.easylearning.domain.signup_interactor;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.data.Entities.User;
import com.walter.cojal.easylearning.network.ServiceApi;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class SignupInteractorImpl implements ISignupInteractor {

    ServiceApi api;
    Scheduler uiThread;
    Scheduler executorThread;

    @Inject
    public SignupInteractorImpl(ServiceApi api, Scheduler uiThread, Scheduler executorThread) {
        this.api = api;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<Result> signUp(User user) {
        return api.signup(user)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}
