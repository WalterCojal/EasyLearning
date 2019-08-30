package com.walter.cojal.easylearning.presentation.main._profile.view;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.makeramen.roundedimageview.RoundedImageView;
import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.base.BaseFragment;
import com.walter.cojal.easylearning.data.entities.Assessor;
import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.di.component.DaggerPresentationComponent;
import com.walter.cojal.easylearning.di.module.PresentationModule;
import com.walter.cojal.easylearning.presentation.main._profile.IProfileContract;
import com.walter.cojal.easylearning.presentation.main._profile.presenter.ProfilePresenter;
import com.walter.cojal.easylearning.utility.Util;

import javax.inject.Inject;

public class ProfileFragment extends BaseFragment implements IProfileContract.IView {

    @Inject
    ProfilePresenter presenter;

    EditText edtName, edtLastName, edtEmail, edtPhone, edtAge, edtBirthDate;
    RoundedImageView imgIcon;
    ImageButton editImage;
    Button editMain;
    boolean isEditing = false;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerPresentationComponent.builder()
                .applicationComponent(getApplicationComponent())
                .presentationModule(new PresentationModule(getActivity()))
                .build().inject(this);
    }

    @Override
    protected void onViewReady(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewReady(view, savedInstanceState);
        setupViews();
        setupListeners();
        presenter.attachView(this);
        presenter.getAssessorData();
    }

    @Override
    public void setupViews() {
        edtAge = getActivity().findViewById(R.id.profile_age);
        edtBirthDate = getActivity().findViewById(R.id.profile_birth_date);
        edtEmail = getActivity().findViewById(R.id.profile_email);
        edtLastName = getActivity().findViewById(R.id.profile_last_name);
        edtName = getActivity().findViewById(R.id.profile_name);
        edtPhone = getActivity().findViewById(R.id.profile_phone);
        imgIcon = getActivity().findViewById(R.id.profile_image);
        editImage = getActivity().findViewById(R.id.profile_edit_image);
        editMain = getActivity().findViewById(R.id.profile_edit_main);
    }

    @Override
    public void setupListeners() {
        editMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditing) {
                    presenter.updateUserData(edtName.getText().toString(),
                            edtLastName.getText().toString(),
                            edtEmail.getText().toString(),
                            edtPhone.getText().toString(),
                            Integer.parseInt(edtAge.getText().toString()),
                            edtBirthDate.getText().toString());
                } else {
                    setMainEdition(true);
                }
                isEditing = !isEditing;
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String errorMsg) {

    }

    @Override
    public void showSuccess(String message) {

    }

    @Override
    public boolean validate() {
        if (edtName.getText().toString().isEmpty()) {
            edtName.setError("Ingrese su nombre");
            edtName.requestFocus();
            return false;
        }
        if (edtLastName.getText().toString().isEmpty()) {
            edtLastName.setError("Ingrese su apellido");
            edtLastName.requestFocus();
            return false;
        }
        if (edtPhone.getText().toString().isEmpty()) {
            edtPhone.setError("Ingrese su número de celular");
            edtPhone.requestFocus();
            return false;
        }
        if (!edtEmail.getText().toString().isEmpty() && !Util.verifyEmail(edtEmail.getText().toString())) {
            edtEmail.setError("Ingrese un correo válido");
            edtEmail.requestFocus();
            return false;
        }
        if (edtAge.getText().toString().isEmpty()) {
            edtAge.setError("Ingrese su edad");
            edtAge.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void setMainEdition(boolean edition) {
        edtName.setEnabled(edition);
        edtLastName.setEnabled(edition);
        edtEmail.setEnabled(edition);
        edtPhone.setEnabled(edition);
        edtAge.setEnabled(edition);
        edtBirthDate.setEnabled(edition);
        editImage.setVisibility(edition? View.VISIBLE: View.GONE);
        editMain.setText(edition? R.string.save_changed: R.string.modify_my_profile);
    }

    @Override
    public void fillUserData(User user) {
        edtName.setText(user.getName());
        edtLastName.setText(user.getLastName());
        edtPhone.setText(user.getPhone());
        edtEmail.setText(user.getEmail());
        edtAge.setText(String.valueOf(user.getAge()));
        edtBirthDate.setText(user.getBirthDate());
    }

    @Override
    public void fillAssessorData(Assessor assessor) {

    }

    @Override
    public void onDetach() {
        presenter.detachView();
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
