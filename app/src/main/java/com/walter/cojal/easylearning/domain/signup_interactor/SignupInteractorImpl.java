package com.walter.cojal.easylearning.domain.signup_interactor;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.data.Entities.User;
import com.walter.cojal.easylearning.repository.user.IUserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SignupInteractorImpl implements ISignupInteractor {

    IUserRepository userRepository;

    @Inject
    public SignupInteractorImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Observable<Result> signUp(User user) {
        return userRepository.create(user);
    }
}
