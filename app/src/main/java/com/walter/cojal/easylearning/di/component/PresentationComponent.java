package com.walter.cojal.easylearning.di.component;

import com.walter.cojal.easylearning.di.module.PresentationModule;
import com.walter.cojal.easylearning.presentation.login.view.LoginActivity;
import com.walter.cojal.easylearning.presentation.signup.view.SignupActivity;
import com.walter.cojal.easylearning.presentation.splash.view.SplashActivity;

import dagger.Component;

@Component(modules = PresentationModule.class)
public interface PresentationComponent {
    void inject(LoginActivity loginActivity);
    void inject(SignupActivity signupActivity);
    void inject(SplashActivity splashActivity);
}
