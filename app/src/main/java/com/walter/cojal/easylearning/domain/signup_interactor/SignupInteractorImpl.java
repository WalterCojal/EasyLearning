package com.walter.cojal.easylearning.domain.signup_interactor;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.data.repository.user.IRetrofitUserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class SignupInteractorImpl implements ISignupInteractor {

    IRetrofitUserRepository userRepository;
    Scheduler uiThread;
    Scheduler executorThread;

    @Inject
    public SignupInteractorImpl(IRetrofitUserRepository userRepository, Scheduler uiThread, Scheduler executorThread) {
        this.userRepository = userRepository;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<Result> signUp(User user) {
        return userRepository.create(user)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}
