package com.walter.cojal.easylearning.presentation.main._home;

import com.walter.cojal.easylearning.data.entities.Assessor;
import com.walter.cojal.easylearning.presentation.IBasePresenter;
import com.walter.cojal.easylearning.presentation.IBaseView;

import java.util.ArrayList;

public interface IHomeContract {

    interface IView extends IBaseView {
        void getDataSuccess(ArrayList<Assessor> items);
        void showEmptyAssessor(String message);
        void goToAssessorDetail(int assessorId);
    }

    interface IPresenter extends IBasePresenter {
        void getData();
        void addFavorite(int assessorId);
        void assessorDetail(Assessor assessor);
    }

}
