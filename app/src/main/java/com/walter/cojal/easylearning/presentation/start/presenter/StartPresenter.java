package com.walter.cojal.easylearning.presentation.start.presenter;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.domain.start_interactor.IStartInteractor;
import com.walter.cojal.easylearning.presentation.start.IStartContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class StartPresenter implements IStartContract.IPresenter {

    IStartInteractor interactor;
    IStartContract.IView view;
    private Disposable disposable;

    @Inject
    public StartPresenter(IStartInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(IStartContract.IView view) {
        this.view = view;
    }

    @Override
    public void dettachView() {
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
    public void update() {
        interactor.updateData().subscribe(new Observer<Result>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Result result) {
                if (isViewAttached()) {
                    if (result.isSuccess()) {
                        view.updateSuccess(result.getCode());
                    } else {
                        view.showError(result.getMessage());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()) {
                    view.showError(e.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
