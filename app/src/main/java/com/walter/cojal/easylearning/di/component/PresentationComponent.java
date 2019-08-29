package com.walter.cojal.easylearning.di.component;

import com.walter.cojal.easylearning.di.module.PresentationModule;
import com.walter.cojal.easylearning.di.scope.PerActivity;
import com.walter.cojal.easylearning.presentation.main.home.view.HomeFragment;
import com.walter.cojal.easylearning.presentation.main.view.HomeActivity;
import com.walter.cojal.easylearning.presentation.login.view.LoginActivity;
import com.walter.cojal.easylearning.presentation.signup.view.SignupActivity;
import com.walter.cojal.easylearning.presentation.start.view.StartActivity;

import dagger.Component;

@PerActivity
@Component(modules = PresentationModule.class,
            dependencies = ApplicationComponent.class)
public interface PresentationComponent {
    void inject(LoginActivity loginActivity);
    void inject(SignupActivity signupActivity);
    void inject(StartActivity startActivity);
    void inject(HomeActivity homeActivity);
    void inject(HomeFragment homeFragment);
}
