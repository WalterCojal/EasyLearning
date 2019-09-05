package com.walter.cojal.easylearning.domain.login_interactor;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.data.repository.auth.IAuthRepository;
import com.walter.cojal.easylearning.data.repository.auth.IPreferenceAuthRepository;
import com.walter.cojal.easylearning.data.repository.user.IPreferenceUserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class LoginInteractorImpl implements ILoginInteractor {

    IAuthRepository authRepository;
    Scheduler uiThread;
    Scheduler executorThread;
    IPreferenceUserRepository userRepository;
    IPreferenceAuthRepository preferenceAuthRepository;

    @Inject
    public LoginInteractorImpl(IAuthRepository authRepository, Scheduler uiThread, Scheduler executorThread, IPreferenceUserRepository userRepository, IPreferenceAuthRepository preferenceAuthRepository) {
        this.authRepository = authRepository;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
        this.userRepository = userRepository;
        this.preferenceAuthRepository = preferenceAuthRepository;
    }

    @Override
    public Observable<Result> login(String email, String password, String token) {
        return authRepository.login(email, password, token)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }

    @Override
    public Observable<Result> validatePhone(String phone) {
        return authRepository.validatePhone(phone)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }

    @Override
    public void saveUser(User user) {
        userRepository.saveUser(user);
    }

    @Override
    public void saveApiToken(String token) {
        preferenceAuthRepository.saveToken(token);
    }

    @Override
    public void saveUserId(int userId) {
        preferenceAuthRepository.saveUserId(userId);
    }
}
