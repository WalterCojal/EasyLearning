package com.walter.cojal.easylearning.presentation.main._home.view;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.base.BaseFragment;
import com.walter.cojal.easylearning.data.entities.Assessor;
import com.walter.cojal.easylearning.di.component.DaggerPresentationComponent;
import com.walter.cojal.easylearning.di.module.PresentationModule;
import com.walter.cojal.easylearning.presentation.login.view.LoginActivity;
import com.walter.cojal.easylearning.presentation.main._home.IHomeContract;
import com.walter.cojal.easylearning.presentation.main._home.presenter.HomePresenter;

import java.util.ArrayList;

import javax.inject.Inject;

public class HomeFragment extends BaseFragment implements IHomeContract.IView {

    @Inject
    ProgressDialog progressDialog;
    @Inject
    HomePresenter presenter;
    @Inject
    AssessorAdapter assessorAdapter;

    private TextView empty;
    private RecyclerView assessors;
    private SwipeRefreshLayout swipeRefreshLayout;

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
        setupViews();
        setupListeners();
        presenter.getData();
    }

    @Override
    public void setupViews() {
        empty = getActivity().findViewById(R.id.home_text);
        assessors = getActivity().findViewById(R.id.home_items);
        swipeRefreshLayout = getActivity().findViewById(R.id.home_swipe);
        assessors.setLayoutManager(new LinearLayoutManager(getActivity()));
        assessors.setItemAnimator(new DefaultItemAnimator());
        assessors.setAdapter(assessorAdapter);
    }

    @Override
    public void setupListeners() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getData();
            }
        });
        assessorAdapter.setOnAssessorClickListener(new OnAssessorListener() {
            @Override
            public void itemViewClick(Assessor assessor) {
                presenter.assessorDetail(assessor);
            }

            @Override
            public void itemFavClick(int assessorId) {
                presenter.addFavorite(assessorId);
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess(String message) {
        empty.setVisibility(View.GONE);
        assessors.setVisibility(View.VISIBLE);
    }


    @Override
    public void showEmptyAssessor(String message) {
        empty.setText(message);
        assessors.setVisibility(View.GONE);
        empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void getDataSuccess(ArrayList<Assessor> items) {
        assessorAdapter.setItems(items);
    }

    @Override
    public void goToAssessorDetail(int assessorId) {
        // TODO actualizar el activity
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.putExtra("id", assessorId);
        startActivity(intent);
    }
}
