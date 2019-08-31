package com.walter.cojal.easylearning.domain.main_interactor.favorite_interactor;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.data.repository.assessor.IRetrofitAssessorRepository;
import com.walter.cojal.easylearning.data.repository.user.IPreferenceUserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class FavoriteInteractorImpl implements IFavoriteInteractor {

    private IPreferenceUserRepository userRepository;
    private IRetrofitAssessorRepository assessorRepository;
    private Scheduler uiThread;
    private Scheduler executorThread;

    @Inject
    public FavoriteInteractorImpl(IPreferenceUserRepository userRepository, IRetrofitAssessorRepository assessorRepository, Scheduler uiThread, Scheduler executorThread) {
        this.userRepository = userRepository;
        this.assessorRepository = assessorRepository;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public User getUser() {
        return userRepository.getUser();
    }

    @Override
    public Observable<Result> getFavorites(int userId) {
        return assessorRepository.getFavorites(userId)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}
