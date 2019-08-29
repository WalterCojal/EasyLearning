package com.walter.cojal.easylearning.domain.main_interactor.home_interactor;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.network.ServiceApi;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class HomeInteractorImpl implements IHomeInteractor {

    private ServiceApi serviceApi;
    private Scheduler uiThread;
    private Scheduler executorThread;

    @Inject
    public HomeInteractorImpl(ServiceApi serviceApi, Scheduler uiThread, Scheduler executorThread) {
        this.serviceApi = serviceApi;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<Result> getHomeData() {
        return serviceApi.getHomeData().
                observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}
