package com.walter.cojal.easylearning.presentation.start.presenter;

import com.walter.cojal.easylearning.domain.start_interactor.IStartInteractor;
import com.walter.cojal.easylearning.presentation.start.IStartContract;

import javax.inject.Inject;

public class StartPresenter implements IStartContract.IPresenter {

    IStartInteractor interactor;
    IStartContract.IView view;

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
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void update() {
        interactor.updateData(new IStartInteractor.UpdateCallBack() {
            @Override
            public void onSuccess(int code) {
                if (isViewAttached()) {
                    view.updateSuccess(code);
                }
            }

            @Override
            public void onError(String errorMsg) {
                view.showError(errorMsg);
            }
        });
    }
}
