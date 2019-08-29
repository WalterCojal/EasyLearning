package com.walter.cojal.easylearning.domain.home_interactor.frag_home_interactor;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.data.network.ServiceApi;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class FragHomeInteractorImpl implements IFragHomeInteractor {

    private ServiceApi serviceApi;
    private Scheduler uiThread;
    private Scheduler executorThread;

    @Inject
    public FragHomeInteractorImpl(ServiceApi serviceApi, Scheduler uiThread, Scheduler executorThread) {
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
