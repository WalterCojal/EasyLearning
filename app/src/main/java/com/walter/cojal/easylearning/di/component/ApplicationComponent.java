package com.walter.cojal.easylearning.di.component;

import android.app.ProgressDialog;

import com.squareup.picasso.Picasso;
import com.walter.cojal.easylearning.di.module.ApplicationModule;
import com.walter.cojal.easylearning.di.scope.Qualifiers;
import com.walter.cojal.easylearning.utility.SavePreferences;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;
import retrofit2.Retrofit;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Retrofit exposeRetrofit();
    Picasso exposePicasso();
    SavePreferences exposeSavePreferences();
    ProgressDialog exposeProgressDialog();
    @Qualifiers.UiThread
    Scheduler exposeUiThread();
    @Qualifiers.ExecutorThread
    Scheduler exposeExecutorThread();
}
