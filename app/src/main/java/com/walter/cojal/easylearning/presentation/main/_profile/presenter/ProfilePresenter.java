package com.walter.cojal.easylearning.presentation.main._profile.presenter;

import com.walter.cojal.easylearning.domain.main_interactor.profile_interactor.IProfileInteractor;
import com.walter.cojal.easylearning.presentation.IBaseView;
import com.walter.cojal.easylearning.presentation.main._profile.IProfileContract;

import javax.inject.Inject;

public class ProfilePresenter implements IProfileContract.IPresenter {

    IProfileContract.IView view;
    IProfileInteractor interactor;

    @Inject
    public ProfilePresenter(IProfileInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(IBaseView view) {
        this.view = (IProfileContract.IView) view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }
}
