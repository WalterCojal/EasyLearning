package com.walter.cojal.easylearning.domain.main_interactor.home_interactor;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.data.repository.assessor.IRetrofitAssessorRepository;
import com.walter.cojal.easylearning.data.repository.user.IPreferenceUserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class HomeInteractorImpl implements IHomeInteractor {

    private IRetrofitAssessorRepository assessorRepository;
    private Scheduler uiThread;
    private Scheduler executorThread;
    private IPreferenceUserRepository userRepository;

    @Inject
    public HomeInteractorImpl(IRetrofitAssessorRepository assessorRepository, Scheduler uiThread, Scheduler executorThread, IPreferenceUserRepository userRepository) {
        this.assessorRepository = assessorRepository;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
        this.userRepository = userRepository;
    }

    @Override
    public Observable<Result> getHomeData(int userId) {
        return assessorRepository.getAll(userId)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }

    @Override
    public Observable<Result> addFavorite(int userId, int assessorId) {
        return assessorRepository.addFavorite(userId, assessorId)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }

    @Override
    public User getUser() {
        return userRepository.getUser();
    }
}
