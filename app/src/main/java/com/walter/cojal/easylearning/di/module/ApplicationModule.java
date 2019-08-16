package com.walter.cojal.easylearning.di.module;

import android.app.ProgressDialog;
import android.content.Context;

import com.squareup.picasso.Picasso;
import com.walter.cojal.easylearning.di.scope.Qualifiers;
import com.walter.cojal.easylearning.utility.Constant;
import com.walter.cojal.easylearning.utility.SavePreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context.getApplicationContext();
    }

    @Provides
    @Singleton
    Picasso providePicasso(Context context) {
        return new Picasso.Builder(context).build();
    }

    @Provides
    @Singleton
    SavePreferences provideSavePreference(Context context) {
        return new SavePreferences(context);
    }

    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(GsonConverterFactory converterFactory) {
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(converterFactory)
                .build();
    }

    @Qualifiers.UiThread
    @Provides
    Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }

    @Qualifiers.ExecutorThread
    @Provides
    Scheduler provideExecutorThread() {
        return Schedulers.io();
    }

}