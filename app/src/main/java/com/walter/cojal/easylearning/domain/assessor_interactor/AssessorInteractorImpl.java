package com.walter.cojal.easylearning.domain.assessor_interactor;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.data.repository.assessor.IRetrofitAssessorRepository;
import com.walter.cojal.easylearning.data.repository.user.IPreferenceUserRepository;
import com.walter.cojal.easylearning.data.repository.user.IRetrofitUserRepository;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AssessorInteractorImpl implements IAssessorInteractor {

    IRetrofitAssessorRepository assessorRepository;
    IPreferenceUserRepository userRepository;
    IRetrofitUserRepository retrofitUserRepository;
    Scheduler uiThread;
    Scheduler executorThread;

    public AssessorInteractorImpl(IRetrofitAssessorRepository assessorRepository, IPreferenceUserRepository userRepository, IRetrofitUserRepository retrofitUserRepository, Scheduler uiThread, Scheduler executorThread) {
        this.assessorRepository = assessorRepository;
        this.userRepository = userRepository;
        this.retrofitUserRepository = retrofitUserRepository;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public User getUser() {
        return userRepository.getUser();
    }

    @Override
    public void saveUser(User user) {
        userRepository.saveUser(user);
    }

    @Override
    public Observable<Result> addAssessor(RequestBody genre, RequestBody document, RequestBody academic, RequestBody assignments, int userId) {
        return assessorRepository.addAssessor(genre, document, academic, assignments, userId)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }

    @Override
    public Observable<Result> addImage(MultipartBody.Part image, int userId) {
        return retrofitUserRepository.updateImage(image, userId)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }

    @Override
    public Observable<Result> getLists() {
        return assessorRepository.getLists()
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}
