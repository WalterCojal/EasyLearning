package com.walter.cojal.easylearning.di.module;

import android.content.Context;

import com.walter.cojal.easylearning.utility.SavePreferences;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class PreferenceModule {

    SavePreferences savePreferences;

    public PreferenceModule(SavePreferences savePreferences) {
        this.savePreferences = savePreferences;
    }

    @Provides
    SavePreferences provideSavePreference(Context context) {
        return new SavePreferences(context);
    }
}
