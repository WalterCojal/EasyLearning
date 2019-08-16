package com.walter.cojal.easylearning.presentation.home.fragmentHome;

import com.walter.cojal.easylearning.data.Entities.Asesor;

import java.util.ArrayList;

public interface IFragHomeContract {

    interface IView {
        void showProgress();
        void hieProgress();
        void showError(String error);
        void getDataSuccess(ArrayList<Asesor> items);
    }

    interface IPresenter {
        void attachView(IView view);
        void detachView();
        Boolean isViewAttached();
        void getData();
    }

}
