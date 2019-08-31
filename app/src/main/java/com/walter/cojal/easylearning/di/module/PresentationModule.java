package com.walter.cojal.easylearning.di.module;

import android.app.Activity;
import android.app.ProgressDialog;

import com.squareup.picasso.Picasso;
import com.walter.cojal.easylearning.data.network.ServiceApi;
import com.walter.cojal.easylearning.data.repository.assessor.IPreferenceAssessorRepository;
import com.walter.cojal.easylearning.data.repository.assessor.IRetrofitAssessorRepository;
import com.walter.cojal.easylearning.data.repository.assessor.PreferenceAssessorRepositoryImpl;
import com.walter.cojal.easylearning.data.repository.assessor.RetrofitAssessorRepositoryImpl;
import com.walter.cojal.easylearning.data.repository.auth.IAuthRepository;
import com.walter.cojal.easylearning.data.repository.auth.IPreferenceAuthRepository;
import com.walter.cojal.easylearning.data.repository.auth.PreferenceAuthRepositoryImpl;
import com.walter.cojal.easylearning.data.repository.auth.RetrofitAuthRepositoryImpl;
import com.walter.cojal.easylearning.data.repository.user.IPreferenceUserRepository;
import com.walter.cojal.easylearning.data.repository.user.IRetrofitUserRepository;
import com.walter.cojal.easylearning.data.repository.user.PreferenceUserRepositoryImpl;
import com.walter.cojal.easylearning.data.repository.user.RetrofitRetrofitUserRepositoryImpl;
import com.walter.cojal.easylearning.di.scope.PerActivity;
import com.walter.cojal.easylearning.di.scope.Qualifiers;
import com.walter.cojal.easylearning.domain.assessor_interactor.AssessorInteractorImpl;
import com.walter.cojal.easylearning.domain.assessor_interactor.IAssessorInteractor;
import com.walter.cojal.easylearning.domain.login_interactor.ILoginInteractor;
import com.walter.cojal.easylearning.domain.login_interactor.LoginInteractorImpl;
import com.walter.cojal.easylearning.domain.main_interactor.favorite_interactor.FavoriteInteractorImpl;
import com.walter.cojal.easylearning.domain.main_interactor.favorite_interactor.IFavoriteInteractor;
import com.walter.cojal.easylearning.domain.main_interactor.home_interactor.HomeInteractorImpl;
import com.walter.cojal.easylearning.domain.main_interactor.home_interactor.IHomeInteractor;
import com.walter.cojal.easylearning.domain.main_interactor.profile_interactor.IProfileInteractor;
import com.walter.cojal.easylearning.domain.main_interactor.profile_interactor.ProfileInteractorImpl;
import com.walter.cojal.easylearning.domain.signup_interactor.ISignupInteractor;
import com.walter.cojal.easylearning.domain.signup_interactor.SignupInteractorImpl;
import com.walter.cojal.easylearning.domain.start_interactor.IStartInteractor;
import com.walter.cojal.easylearning.domain.start_interactor.StartInteractorImpl;
import com.walter.cojal.easylearning.presentation.main.IMainContract;
import com.walter.cojal.easylearning.presentation.main._favotire.view.FavoriteAdapter;
import com.walter.cojal.easylearning.presentation.main._favotire.view.FavoriteFragment;
import com.walter.cojal.easylearning.presentation.main._home.view.AssessorAdapter;
import com.walter.cojal.easylearning.presentation.main._home.view.HomeFragment;
import com.walter.cojal.easylearning.presentation.main._profile.view.ProfileFragment;
import com.walter.cojal.easylearning.presentation.main.presenter.MainPresenter;
import com.walter.cojal.easylearning.utility.SavePreferences;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

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

    @Provides
    IAuthRepository provideAuthRepository(ServiceApi serviceApi) {
        return new RetrofitAuthRepositoryImpl(serviceApi);
    }

    @Provides
    IPreferenceAuthRepository providePreferenceAuthRepository(SavePreferences savePreferences) {
        return new PreferenceAuthRepositoryImpl(savePreferences);
    }

    @Provides
    IRetrofitUserRepository provideRetrofitUserRepository(ServiceApi serviceApi) {
        return new RetrofitRetrofitUserRepositoryImpl(serviceApi);
    }

    @Provides
    IPreferenceUserRepository providePreferenceUserRepository(SavePreferences savePreferences) {
        return new PreferenceUserRepositoryImpl(savePreferences);
    }

    @Provides
    IPreferenceAssessorRepository providePreferenceAssessorRepository(SavePreferences savePreferences) {
        return new PreferenceAssessorRepositoryImpl(savePreferences);
    }

    @Provides
    IRetrofitAssessorRepository provideRetrofitAssessorRepository(ServiceApi serviceApi) {
        return new RetrofitAssessorRepositoryImpl(serviceApi);
    }

    // ============================================ Login ============================================ //

    @Provides
    ILoginInteractor provideLoginInteractor(IAuthRepository authRepository,
                                            @Qualifiers.UiThread Scheduler uiThread,
                                            @Qualifiers.ExecutorThread Scheduler executorThread,
                                            IPreferenceUserRepository userRepository, IPreferenceAuthRepository preferenceAuthRepository) {
        return new LoginInteractorImpl(authRepository, uiThread, executorThread, userRepository, preferenceAuthRepository);
    }

    // ============================================ Signup ============================================ //
    @Provides
    ISignupInteractor provideSignupInteractor(IRetrofitUserRepository userRepository,
                                              @Qualifiers.UiThread Scheduler uiThread,
                                              @Qualifiers.ExecutorThread Scheduler executorThread) {
        return new SignupInteractorImpl(userRepository, uiThread, executorThread);
    }

    // ============================================ Start ============================================ //
    @Provides
    IStartInteractor provideStartInteractor(ServiceApi serviceApi,
                                            @Qualifiers.UiThread Scheduler uiThread,
                                            @Qualifiers.ExecutorThread Scheduler executorThread,
                                            IPreferenceUserRepository userRepository) {
        return new StartInteractorImpl(serviceApi, uiThread, executorThread, userRepository);
    }

    // ============================================ Main ============================================ //
    @Provides
    IMainContract.IPresenter provideHomeContractPresenter() {
        return new MainPresenter();
    }

    // Home Fragment
    @Provides
    HomeFragment provideHomeFragment() {
        return new HomeFragment();
    }

    @Provides
    IHomeInteractor provideHomeInteractor(IRetrofitAssessorRepository assessorRepository,
                                          @Qualifiers.UiThread Scheduler uiThread,
                                          @Qualifiers.ExecutorThread Scheduler executorThread,
                                          IPreferenceUserRepository userRepository) {
        return new HomeInteractorImpl(assessorRepository, uiThread, executorThread, userRepository);
    }

    @Provides
    AssessorAdapter provideAssessorAdapter(Picasso picasso) {
        return new AssessorAdapter(picasso);
    }

    // Profile Fragment
    @Provides
    ProfileFragment provideProfileFragment() {
        return new ProfileFragment();
    }

    @Provides
    IProfileInteractor provideProfileInteractor(IPreferenceUserRepository preferenceUser,
                                                IRetrofitUserRepository retrofitUser,
                                                IRetrofitAssessorRepository retrofitAssessor,
                                                @Qualifiers.UiThread Scheduler uiThread,
                                                @Qualifiers.ExecutorThread Scheduler executorThread,
                                                IPreferenceAuthRepository authRepository) {
        return new ProfileInteractorImpl(preferenceUser, retrofitUser, retrofitAssessor, uiThread, executorThread, authRepository);
    }

    // Favorite Fragment
    @Provides
    FavoriteFragment provideFavoriteFragment() {
        return new FavoriteFragment();
    }

    @Provides
    FavoriteAdapter provideFavoriteAdapter(Picasso picasso) {
        return new FavoriteAdapter(picasso);
    }

    @Provides
    IFavoriteInteractor provideFavoriteInteractor(IPreferenceUserRepository userRepository,
                                                  IRetrofitAssessorRepository assessorRepository,
                                                  @Qualifiers.UiThread Scheduler uiThread,
                                                  @Qualifiers.ExecutorThread Scheduler executorThread) {
        return new FavoriteInteractorImpl(userRepository, assessorRepository, uiThread, executorThread);
    }

    // ======================================== Assessor ======================================== //
    @Provides
    IAssessorInteractor provideAssessorInteractor(IRetrofitAssessorRepository assessorRepository,
                                                  IPreferenceUserRepository userRepository,
                                                  IRetrofitUserRepository retrofitUserRepository,
                                                  @Qualifiers.UiThread Scheduler uiThread,
                                                  @Qualifiers.ExecutorThread Scheduler executorThread) {
        return new AssessorInteractorImpl(assessorRepository, userRepository, retrofitUserRepository, uiThread, executorThread);
    }
}
