package com.walter.cojal.easylearning.domain.login_interactor;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.network.ServiceApi;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class LoginInteractorImpl implements ILoginInteractor {

    ServiceApi api;
    Scheduler uiThread;
    Scheduler executorThread;

    @Inject
    public LoginInteractorImpl(ServiceApi api, Scheduler uiThread, Scheduler executorThread) {
        this.api = api;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<Result> login(String email, String password, String token) {
        return api.login(email, password, token)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }

    @Override
    public Observable<Result> sendEmail(String email) {
        return api.sendEmail(email)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }

    @Override
    public Observable<Result> validatePhone(String phone) {
        return api.validatePhone(phone)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }

}
