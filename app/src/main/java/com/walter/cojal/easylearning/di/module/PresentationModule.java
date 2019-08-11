package com.walter.cojal.easylearning.di.module;

import com.walter.cojal.easylearning.di.scope.PerActivity;
import com.walter.cojal.easylearning.domain.login_interactor.ILoginInteractor;
import com.walter.cojal.easylearning.domain.login_interactor.LoginInteractorImpl;
import com.walter.cojal.easylearning.domain.signup_interactor.ISignupInteractor;
import com.walter.cojal.easylearning.domain.signup_interactor.SignupInteractorImpl;
import com.walter.cojal.easylearning.domain.splash_interactor.ISplashInteractor;
import com.walter.cojal.easylearning.domain.splash_interactor.SplashInteractorImpl;
import com.walter.cojal.easylearning.network.ServiceApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class PresentationModule {

    @PerActivity
    @Provides
    ServiceApi provideServiceApi(Retrofit retrofit) {
        return retrofit.create(ServiceApi.class);
    }

    @Provides
    ILoginInteractor provideLoginInteractor(ServiceApi serviceApi) {
        return new LoginInteractorImpl(serviceApi);
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
