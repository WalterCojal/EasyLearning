package com.walter.cojal.easylearning.di.module;

import com.walter.cojal.easylearning.di.scope.PerActivity;
import com.walter.cojal.easylearning.di.scope.Qualifiers;
import com.walter.cojal.easylearning.domain.login_interactor.ILoginInteractor;
import com.walter.cojal.easylearning.domain.login_interactor.LoginInteractorImpl;
import com.walter.cojal.easylearning.domain.signup_interactor.ISignupInteractor;
import com.walter.cojal.easylearning.domain.signup_interactor.SignupInteractorImpl;
import com.walter.cojal.easylearning.domain.start_interactor.IStartInteractor;
import com.walter.cojal.easylearning.domain.start_interactor.StartInteractorImpl;
import com.walter.cojal.easylearning.network.ServiceApi;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import retrofit2.Retrofit;

@Module
public class PresentationModule {

    @PerActivity
    @Provides
    ServiceApi provideServiceApi(Retrofit retrofit) {
        return retrofit.create(ServiceApi.class);
    }

    @Provides
    ILoginInteractor provideLoginInteractor(ServiceApi serviceApi,
                                            @Qualifiers.UiThread Scheduler uiThread,
                                            @Qualifiers.ExecutorThread Scheduler executorThrea) {
        return new LoginInteractorImpl(serviceApi, uiThread, executorThrea);
    }

    @Provides
    ISignupInteractor provideSignupInteractor(ServiceApi serviceApi,
                                              @Qualifiers.UiThread Scheduler uiThread,
                                              @Qualifiers.ExecutorThread Scheduler executorThread) {
        return new SignupInteractorImpl(serviceApi, uiThread, executorThread);
    }

    @Provides
    IStartInteractor provideStartInteractor(ServiceApi serviceApi,
                                            @Qualifiers.UiThread Scheduler uiThread,
                                            @Qualifiers.ExecutorThread Scheduler executorThread) {
        return new StartInteractorImpl(serviceApi, uiThread, executorThread);
    }

}
