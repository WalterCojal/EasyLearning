package com.walter.cojal.easylearning.domain.login_interactor;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.repository.auth.IAuthRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LoginInteractorImpl implements ILoginInteractor {

    IAuthRepository authRepository;

    @Inject
    public LoginInteractorImpl(IAuthRepository authRepository) {
        this.authRepository = authRepository;
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
