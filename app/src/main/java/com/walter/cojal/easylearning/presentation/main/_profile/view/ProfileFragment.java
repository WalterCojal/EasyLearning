package com.walter.cojal.easylearning.presentation.main._profile.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.base.BaseFragment;
import com.walter.cojal.easylearning.di.component.DaggerApplicationComponent;
import com.walter.cojal.easylearning.di.component.DaggerPresentationComponent;
import com.walter.cojal.easylearning.di.module.PresentationModule;
import com.walter.cojal.easylearning.presentation.main._profile.IProfileContract;
import com.walter.cojal.easylearning.presentation.main._profile.presenter.ProfilePresenter;

import javax.inject.Inject;

public class ProfileFragment extends BaseFragment implements IProfileContract.IView {

    @Inject
    ProfilePresenter presenter;

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
    }

    @Override
    public void setupViews() {

    }

    @Override
    public void setupListeners() {

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
