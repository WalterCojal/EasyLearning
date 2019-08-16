package com.walter.cojal.easylearning.presentation.home.fragmentHome.presenter;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.domain.home_interactor.frag_home_interactor.IFragHomeInteractor;
import com.walter.cojal.easylearning.presentation.home.fragmentHome.IFragHomeContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FragHomePresenter implements IFragHomeContract.IPresenter {

    IFragHomeInteractor interactor;
    IFragHomeContract.IView view;
    Disposable disposable;

    @Inject
    public FragHomePresenter(IFragHomeInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(IFragHomeContract.IView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public Boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void getData() {
        view.showProgress();
        interactor.getHomeData().subscribe(new Observer<Result>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Result result) {
                if (isViewAttached()) {
                    view.hieProgress();
                    if (result.isSuccess()) {
                        view.getDataSuccess(result.getAsesors());
                    } else {
                        view.showError(result.getMessage());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()) {
                    view.hieProgress();
                    view.showError(e.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
