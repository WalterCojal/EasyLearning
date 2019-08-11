package com.walter.cojal.easylearning.di.component;

import com.squareup.picasso.Picasso;
import com.walter.cojal.easylearning.di.module.ApplicationModule;
import com.walter.cojal.easylearning.di.module.PicassoModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {ApplicationModule.class, PicassoModule.class})
public interface ApplicationComponent {

    Retrofit exposeRetrofit();
    Picasso exposePicasso();

}
