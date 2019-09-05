package com.walter.cojal.easylearning.di.module;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.squareup.picasso.Picasso;
import com.walter.cojal.easylearning.data.network.ServiceApi;
import com.walter.cojal.easylearning.di.scope.Qualifiers;
import com.walter.cojal.easylearning.utility.Constants;
import com.walter.cojal.easylearning.utility.SavePreferences;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    Interceptor provideInterceptor(SavePreferences savePreferences) {
        final String USER_ID = savePreferences.getString(Constants.USER_ID);
        final String API_TOKEN = savePreferences.getString(Constants.API_TOKEN);
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Api-Token", API_TOKEN)
                        .addHeader("User-Id", USER_ID)
                        .build();
                return chain.proceed(request);
            }
        };
    }

    @Provides
    OkHttpClient.Builder provideOkHttpClientBuilder(Interceptor interceptor) {
        return new OkHttpClient.Builder().addInterceptor(interceptor);
    }

    @Provides
    OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Provides
    ServiceApi provideServiceApi(Retrofit retrofit) {
        return retrofit.create(ServiceApi.class);
    }

    @Provides
    Retrofit provideRetrofit(GsonConverterFactory converterFactory, RxJava2CallAdapterFactory adapterFactory, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(converterFactory)
                .client(client)
                .addCallAdapterFactory(adapterFactory)
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
