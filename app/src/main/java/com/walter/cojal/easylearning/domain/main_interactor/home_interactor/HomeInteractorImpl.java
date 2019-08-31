package com.walter.cojal.easylearning.domain.main_interactor.home_interactor;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.repository.assessor.IRetrofitAssessorRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class HomeInteractorImpl implements IHomeInteractor {

    private IRetrofitAssessorRepository assessorRepository;
    private Scheduler uiThread;
    private Scheduler executorThread;

    @Inject
    public HomeInteractorImpl(IRetrofitAssessorRepository assessorRepository, Scheduler uiThread, Scheduler executorThread) {
        this.assessorRepository = assessorRepository;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<Result> getHomeData() {
        return assessorRepository.getAll()
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}
