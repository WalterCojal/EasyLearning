package com.walter.cojal.easylearning.di.module;

import com.walter.cojal.easylearning.domain.login_interactor.ILoginInteractor;
import com.walter.cojal.easylearning.domain.login_interactor.LoginInteractorImpl;
import com.walter.cojal.easylearning.domain.signup_interactor.ISignupInteractor;
import com.walter.cojal.easylearning.domain.signup_interactor.SignupInteractorImpl;
import com.walter.cojal.easylearning.domain.splash_interactor.ISplashInteractor;
import com.walter.cojal.easylearning.domain.splash_interactor.SplashInteractorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    @Provides
    ILoginInteractor provideLoginInteractor() {
        return new LoginInteractorImpl();
    }

    @Provides
    ISignupInteractor provideSignupInteractor() {
        return new SignupInteractorImpl();
    }

    @Provides
    ISplashInteractor provideSplashInteractor() {
        return new SplashInteractorImpl();
    }

}
