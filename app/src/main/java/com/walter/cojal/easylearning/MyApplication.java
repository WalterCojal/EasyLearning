package com.walter.cojal.easylearning;

import android.app.Application;

import com.walter.cojal.easylearning.di.component.DaggerPresentationComponent;
import com.walter.cojal.easylearning.di.component.PresentationComponent;
import com.walter.cojal.easylearning.di.module.PresentationModule;

public class MyApplication extends Application {

    private PresentationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
    }

    void initializeComponent() {
        appComponent = DaggerPresentationComponent.builder()
                .presentationModule(new PresentationModule())
                .build();
    }

    public PresentationComponent getAppComponent() {
        return appComponent;
    }
}
