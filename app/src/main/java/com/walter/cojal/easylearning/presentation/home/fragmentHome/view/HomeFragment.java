package com.walter.cojal.easylearning.presentation.home.fragmentHome.view;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.base.BaseFragment;
import com.walter.cojal.easylearning.data.Entities.Asesor;
import com.walter.cojal.easylearning.data.Entities.User;
import com.walter.cojal.easylearning.di.component.DaggerPresentationComponent;
import com.walter.cojal.easylearning.di.module.PresentationModule;
import com.walter.cojal.easylearning.presentation.home.fragmentHome.IFragHomeContract;
import com.walter.cojal.easylearning.presentation.home.fragmentHome.presenter.FragHomePresenter;

import java.util.ArrayList;

import javax.inject.Inject;

public class HomeFragment extends BaseFragment implements IFragHomeContract.IView {

    @Inject
    ProgressDialog progressDialog;
    @Inject
    FragHomePresenter presenter;
    User user;
    private RecyclerView asesors;
    private AsesorAdapter asesorAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_home;
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
        presenter.attachView(this);
        asesorAdapter = new AsesorAdapter();
        asesors = view.findViewById(R.id.f_home_items);
        asesors.setLayoutManager(new LinearLayoutManager(getActivity()));
        asesors.setItemAnimator(new DefaultItemAnimator());
        asesors.setAdapter(asesorAdapter);
        ArrayList<Asesor> items = new ArrayList<>();
        items.add(new Asesor(1, "Richard", "Guevara", "mail", "991988248", 55));
        getDataSuccess(items);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hieProgress() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void getDataSuccess(ArrayList<Asesor> items) {
        asesorAdapter.setItems(items);
        asesorAdapter.notifyDataSetChanged();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
