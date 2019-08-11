package com.walter.cojal.easylearning;

import android.app.Application;

import com.walter.cojal.easylearning.di.component.ApplicationComponent;
import com.walter.cojal.easylearning.di.component.DaggerApplicationComponent;
import com.walter.cojal.easylearning.di.module.ApplicationModule;

public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
    }

    void initializeComponent() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
