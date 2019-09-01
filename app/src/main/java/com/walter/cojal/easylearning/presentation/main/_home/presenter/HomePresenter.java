package com.walter.cojal.easylearning.presentation.main._home.presenter;

import com.walter.cojal.easylearning.data.entities.Assessor;
import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.domain.main_interactor.home_interactor.IHomeInteractor;
import com.walter.cojal.easylearning.presentation.IBaseView;
import com.walter.cojal.easylearning.presentation.main._home.IHomeContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomePresenter implements IHomeContract.IPresenter {

    IHomeInteractor interactor;
    IHomeContract.IView view;
    Disposable disposable;
    User user;

    @Inject
    public HomePresenter(IHomeInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(IBaseView view) {
        this.view = (IHomeContract.IView) view;
    }

    @Override
    public void detachView() {
        view = null;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void getData() {
        view.showProgress();
        user = interactor.getUser();
        interactor.getHomeData(user.getId()).subscribe(new Observer<Result>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Result result) {
                if (isViewAttached()) {
                    view.hideProgress();
                    if (result.isSuccess()) {
                        view.getDataSuccess(result.getAssessors());
                    } else {
                        view.showEmptyAssessor(result.getMessage());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()) {
                    view.hideProgress();
                    view.showError(e.getMessage());
                    view.showEmptyAssessor("No hay asesores en esta zona");
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void addFavorite(int assessorId, final int position) {
        view.showProgress();
        interactor.addFavorite(user.getId(), assessorId).subscribe(new Observer<Result>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Result result) {
                if (isViewAttached()) {
                    view.hideProgress();
                    if (result.isSuccess()) {
                        view.favoriteSuccess(position);
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
    public void assessorDetail(Assessor assessor) {
        view.goToAssessorDetail(assessor.getId());
    }
}
