package com.walter.cojal.easylearning.domain.main_interactor.profile_interactor;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.data.repository.assessor.IRetrofitAssessorRepository;
import com.walter.cojal.easylearning.data.repository.user.IPreferenceUserRepository;
import com.walter.cojal.easylearning.data.repository.user.IRetrofitUserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import okhttp3.MultipartBody;

public class ProfileInteractorImpl implements IProfileInteractor {

    IPreferenceUserRepository preferenceUser;
    IRetrofitUserRepository retrofitUser;
    IRetrofitAssessorRepository retrofitAssessor;
    Scheduler uiThread;
    Scheduler executorThread;

    @Inject
    public ProfileInteractorImpl(IPreferenceUserRepository preferenceUser, IRetrofitUserRepository retrofitUser, IRetrofitAssessorRepository retrofitAssessor, Scheduler uiThread, Scheduler executorThread) {
        this.preferenceUser = preferenceUser;
        this.retrofitUser = retrofitUser;
        this.retrofitAssessor = retrofitAssessor;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public User getUser() {
        return preferenceUser.getUser();
    }

    @Override
    public void saveUser(User user) {
        preferenceUser.saveUser(user);
    }

    @Override
    public Observable<Result> getAssessorData(int userId) {
        return retrofitAssessor.getAssessorData(userId)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }

    @Override
    public Observable<Result> updateUserData(User user) {
        return retrofitUser.update(user)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }

    @Override
    public Observable<Result> updateUserImage(MultipartBody.Part image, int userId) {
        return retrofitUser.updateImage(image, userId)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}
