package com.walter.cojal.easylearning.presentation.assessor.presenter;

import com.google.gson.Gson;
import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.domain.assessor_interactor.IAssessorInteractor;
import com.walter.cojal.easylearning.presentation.IBaseView;
import com.walter.cojal.easylearning.presentation.assessor.IAssessorContract;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AssessorPresenter implements IAssessorContract.IPresenter {

    IAssessorContract.IView view;
    IAssessorInteractor interactor;
    Disposable disposable;
    User user;
    ArrayList<String> genres = new ArrayList<>();
    ArrayList<String> academics = new ArrayList<>();

    @Inject
    public AssessorPresenter(IAssessorInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(IBaseView view) {
        this.view = (IAssessorContract.IView) view;
    }

    @Override
    public void detachView() {
        view = null;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public void setupGenres() {
        view.showGenreOptions(genres);
    }

    @Override
    public void setupAcademics() {
        view.showAcademicOptions(academics);
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void addAssessor(String genre, String document, String academic, ArrayList<String> assignments) {
        if (!view.validate()) return;
        view.showProgress();
        RequestBody rbGenre = RequestBody.create(MediaType.parse("text/plain"), genre.trim());
        RequestBody rbDocument = RequestBody.create(MediaType.parse("text/plain"), document.trim());
        RequestBody rbAcademic = RequestBody.create(MediaType.parse("text/plain"), academic.trim());
        String jsonAssignments = (new Gson()).toJson(assignments);
        RequestBody rbAssignments = RequestBody.create(MediaType.parse("text/plain"), jsonAssignments.trim());
        interactor.addAssessor(rbGenre, rbDocument, rbAcademic, rbAssignments, user.getId()).subscribe(new Observer<Result>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Result result) {
                if (isViewAttached()) {
                    view.hideProgress();
                    if (result.isSuccess()) {
                        view.goToMain();
                    } else {
                        view.showError(result.getMessage());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()) {
                    view.hideProgress();
                    view.showError(e.getLocalizedMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getLists() {
        view.showProgress();
        user = interactor.getUser();
        interactor.getLists().subscribe(new Observer<Result>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Result result) {
                if (isViewAttached()) {
                    view.hideProgress();
                    genres.add("Masculino");
                    genres.add("Femenino");
                    academics = result.getAcademicList();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()) {
                    view.hideProgress();
                    view.enableEditors();
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void setImage(MultipartBody.Part image) {
        view.showProgress();
        interactor.addImage(image, user.getId()).subscribe(new Observer<Result>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Result result) {
                if (isViewAttached()) {
                    view.hideProgress();
                    if (result.isSuccess()) {
                        user.setImage(result.getUser().getImage());
                        interactor.saveUser(user);
                        view.updateImage();
                    } else {
                        view.clearImage();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()) {
                    view.hideProgress();
                    view.showError(e.getLocalizedMessage());
                    view.clearImage();
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
