package com.walter.cojal.easylearning.presentation.start.presenter;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.domain.start_interactor.IStartInteractor;
import com.walter.cojal.easylearning.presentation.IBaseView;
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
    public void attachView(IBaseView view) {
        this.view = (IStartContract.IView) view;
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
    public void isUserLogged() {
        User user = interactor.getUser();
        if (user != null) {
            view.goToDashboard();
        } else {
            view.goToLogin();
        }
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
