package com.walter.cojal.easylearning.domain.start_interactor;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.data.network.ServiceApi;
import com.walter.cojal.easylearning.data.repository.user.IPreferenceUserRepository;
import com.walter.cojal.easylearning.data.repository.user.PreferenceUserRepositoryImpl;
import com.walter.cojal.easylearning.utility.SavePreferences;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class StartInteractorImpl implements IStartInteractor {

    private ServiceApi api;
    private Scheduler uiThread;
    private Scheduler executorScheduler;
    private IPreferenceUserRepository userRepository;

    @Inject
    public StartInteractorImpl(ServiceApi api, Scheduler uiThread, Scheduler executorScheduler, IPreferenceUserRepository userRepository) {
        this.api = api;
        this.uiThread = uiThread;
        this.executorScheduler = executorScheduler;
        this.userRepository = userRepository;
    }

    @Override
    public Observable<Result> updateData() {
        return api.getInitData()
                .observeOn(uiThread)
                .subscribeOn(executorScheduler);
    }

    @Override
    public User getUser() {
        return userRepository.getUser();
    }
}
