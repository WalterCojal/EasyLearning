package com.walter.cojal.easylearning.di.component;

import com.walter.cojal.easylearning.di.module.PresentationModule;
import com.walter.cojal.easylearning.di.scope.PerActivity;
import com.walter.cojal.easylearning.presentation.login.view.LoginActivity;
import com.walter.cojal.easylearning.presentation.signup.view.SignupActivity;

import dagger.Component;

@PerActivity
@Component(modules = PresentationModule.class,
            dependencies = ApplicationComponent.class)
public interface PresentationComponent {
    void inject(LoginActivity loginActivity);
    void inject(SignupActivity signupActivity);
}
