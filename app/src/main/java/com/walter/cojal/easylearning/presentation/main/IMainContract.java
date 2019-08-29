package com.walter.cojal.easylearning.presentation.main;

import com.walter.cojal.easylearning.presentation.IBasePresenter;
import com.walter.cojal.easylearning.presentation.IBaseView;

public interface IMainContract {

    interface IView extends IBaseView {
        void showHomeFragment();
        void showProfileFragment();
        void showFavoriteFragment();
    }

    interface IPresenter extends IBasePresenter {
    }

}
