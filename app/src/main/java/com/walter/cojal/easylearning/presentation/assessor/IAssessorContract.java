package com.walter.cojal.easylearning.presentation.assessor;

import com.walter.cojal.easylearning.presentation.IBasePresenter;
import com.walter.cojal.easylearning.presentation.IBaseView;

import java.util.ArrayList;

public interface IAssessorContract {

    interface IView extends IBaseView {
        boolean validate();
    }

    interface IPresenter extends IBasePresenter {
        void addAssessor(String genre, String document, String academic, ArrayList<String> assignments);
        void getLists();
    }

}
