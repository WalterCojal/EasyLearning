package com.walter.cojal.easylearning.presentation.assessor;

import com.walter.cojal.easylearning.presentation.IBasePresenter;
import com.walter.cojal.easylearning.presentation.IBaseView;

import java.util.ArrayList;

import okhttp3.MultipartBody;

public interface IAssessorContract {

    interface IView extends IBaseView {
        boolean validate();
        void updateImage();
        void clearImage();
        void enableEditors();
        void showGenreOptions(ArrayList<String> items);
        void showAcademicOptions(ArrayList<String> items);
        void goToMain();
    }

    interface IPresenter extends IBasePresenter {
        void addAssessor(String genre, String document, String academic, ArrayList<String> assignments);
        void getLists();
        void setImage(MultipartBody.Part image);
        void setupGenres();
        void setupAcademics();
    }

}
