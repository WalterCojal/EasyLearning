package com.walter.cojal.easylearning.presentation.main._profile;

import com.walter.cojal.easylearning.data.entities.Assessor;
import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.presentation.IBasePresenter;
import com.walter.cojal.easylearning.presentation.IBaseView;

import okhttp3.MultipartBody;

public interface IProfileContract {

    interface IView extends IBaseView {
        void setMainEdition(boolean edition);
        void fillUserData(User user);
        void fillAssessorData(Assessor assessor);
        boolean validate();
        void fillImage(String path);
    }

    interface IPresenter extends IBasePresenter {
        void getAssessorData();
        void updateUserData(String name, String lastName, String email, String phone, int age, String birthDate);
        void updateUserImage(MultipartBody.Part image);
    }

}
