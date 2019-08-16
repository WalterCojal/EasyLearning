package com.walter.cojal.easylearning.presentation.home;

public interface IHomeContract {

    interface IView {
        void showProgress();
        void hideProgress();
        void showError(String error);
        void showHomeFragment();
        void showProfileFragment();
        void showFavoriteFragment();
    }

    interface IPresenter {
        void attachView(IView view);
        void detachView();
        Boolean isViewAttached();
    }

}
