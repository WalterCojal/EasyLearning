package com.walter.cojal.easylearning.di.component;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.squareup.picasso.Picasso;
import com.walter.cojal.easylearning.di.module.ApplicationModule;
import com.walter.cojal.easylearning.di.scope.Qualifiers;
import com.walter.cojal.easylearning.data.network.ServiceApi;
import com.walter.cojal.easylearning.utility.SavePreferences;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Retrofit exposeRetrofit();
    Picasso exposePicasso();
    SavePreferences exposeSavePreferences();
    ServiceApi exposeServiceApi();
    @Qualifiers.UiThread
    Scheduler exposeUiThread();
    @Qualifiers.ExecutorThread
    Scheduler exposeExecutorThread();

    /*GsonConverterFactory exposeGsonConverterFactory();
    RxJava2CallAdapterFactory exposeRxJava2CallAdapterFactory();*/
}
