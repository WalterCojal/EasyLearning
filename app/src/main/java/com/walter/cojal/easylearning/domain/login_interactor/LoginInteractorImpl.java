package com.walter.cojal.easylearning.domain.login_interactor;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.data.repository.auth.IAuthRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class LoginInteractorImpl implements ILoginInteractor {

    IAuthRepository authRepository;
    Scheduler uiThread;
    Scheduler executorThread;

    @Inject
    public LoginInteractorImpl(IAuthRepository authRepository, Scheduler uiThread, Scheduler executorThread) {
        this.authRepository = authRepository;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<Result> login(String email, String password, String token) {
        return authRepository.login(email, password, token);
    }

    @Override
    public Observable<Result> validatePhone(String phone) {
        return authRepository.validatePhone(phone);
    }

}
