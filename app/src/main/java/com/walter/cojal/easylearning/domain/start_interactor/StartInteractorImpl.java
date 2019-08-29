package com.walter.cojal.easylearning.domain.start_interactor;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.data.network.ServiceApi;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class StartInteractorImpl implements IStartInteractor {

    private ServiceApi api;
    private Scheduler uiThread;
    private Scheduler executorScheduler;

    @Inject
    public StartInteractorImpl(ServiceApi api, Scheduler uiThread, Scheduler executorScheduler) {
        this.api = api;
        this.uiThread = uiThread;
        this.executorScheduler = executorScheduler;
    }

    @Override
    public Observable<Result> updateData() {
        return api.getInitData()
                .observeOn(uiThread)
                .subscribeOn(executorScheduler);
    }

}
