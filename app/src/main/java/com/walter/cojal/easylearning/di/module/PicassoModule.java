package com.walter.cojal.easylearning.di.module;

import android.content.Context;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class PicassoModule {

    @Provides
    Picasso providePicasso(Context context) {
        return new Picasso.Builder(context).build();
    }

}
