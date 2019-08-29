package com.walter.cojal.easylearning.domain.main_interactor.profile_interactor;

import com.walter.cojal.easylearning.data.repository.user.IPreferenceUserRepository;
import com.walter.cojal.easylearning.data.repository.user.IRetrofitUserRepository;

import javax.inject.Inject;

public class ProfileInteractorImpl implements IProfileInteractor {

    IPreferenceUserRepository preferenceUser;
    IRetrofitUserRepository retrofitUser;

    @Inject
    public ProfileInteractorImpl(IPreferenceUserRepository preferenceUser, IRetrofitUserRepository retrofitUser) {
        this.preferenceUser = preferenceUser;
        this.retrofitUser = retrofitUser;
    }
}
