package com.walter.cojal.easylearning.di.module;

import android.app.Activity;
import android.app.ProgressDialog;

import com.walter.cojal.easylearning.di.scope.PerActivity;
import com.walter.cojal.easylearning.di.scope.Qualifiers;
import com.walter.cojal.easylearning.domain.home_interactor.frag_home_interactor.FragHomeInteractorImpl;
import com.walter.cojal.easylearning.domain.home_interactor.frag_home_interactor.IFragHomeInteractor;
import com.walter.cojal.easylearning.domain.login_interactor.ILoginInteractor;
import com.walter.cojal.easylearning.domain.login_interactor.LoginInteractorImpl;
import com.walter.cojal.easylearning.domain.signup_interactor.ISignupInteractor;
import com.walter.cojal.easylearning.domain.signup_interactor.SignupInteractorImpl;
import com.walter.cojal.easylearning.domain.start_interactor.IStartInteractor;
import com.walter.cojal.easylearning.domain.start_interactor.StartInteractorImpl;
import com.walter.cojal.easylearning.network.ServiceApi;
import com.walter.cojal.easylearning.presentation.home.IHomeContract;
import com.walter.cojal.easylearning.presentation.home.presenter.HomePresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import retrofit2.Retrofit;

@Module
public class PresentationModule {

    Activity activity;

    public PresentationModule(Activity activity) {
        this.activity = activity;
    }

    @PerActivity
    @Provides
    ProgressDialog provideProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    @PerActivity
    @Provides
    ServiceApi provideServiceApi(Retrofit retrofit) {
        return retrofit.create(ServiceApi.class);
    }

    // ============================================ Login ============================================ //
    @Provides
    ILoginInteractor provideLoginInteractor(ServiceApi serviceApi,
                                            @Qualifiers.UiThread Scheduler uiThread,
                                            @Qualifiers.ExecutorThread Scheduler executorThrea) {
        return new LoginInteractorImpl(serviceApi, uiThread, executorThrea);
    }

    // ============================================ Signup ============================================ //
    @Provides
    ISignupInteractor provideSignupInteractor(ServiceApi serviceApi,
                                              @Qualifiers.UiThread Scheduler uiThread,
                                              @Qualifiers.ExecutorThread Scheduler executorThread) {
        return new SignupInteractorImpl(serviceApi, uiThread, executorThread);
    }

    // ============================================ Start ============================================ //
    @Provides
    IStartInteractor provideStartInteractor(ServiceApi serviceApi,
                                            @Qualifiers.UiThread Scheduler uiThread,
                                            @Qualifiers.ExecutorThread Scheduler executorThread) {
        return new StartInteractorImpl(serviceApi, uiThread, executorThread);
    }

    // ============================================ Home ============================================ //
    @Provides
    IHomeContract.IPresenter provideHomeContractPresenter() {
        return new HomePresenter();
    }
    // Home Fragment
    @Provides
    IFragHomeInteractor provideFragHomeInteractor(ServiceApi serviceApi,
                                                  @Qualifiers.UiThread Scheduler uiThread,
                                                  @Qualifiers.ExecutorThread Scheduler executorThread) {
        return new FragHomeInteractorImpl(serviceApi, uiThread, executorThread);
    }

}
