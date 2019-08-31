package com.walter.cojal.easylearning.presentation.main._favotire.view;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.base.BaseFragment;
import com.walter.cojal.easylearning.data.entities.Assessor;
import com.walter.cojal.easylearning.di.component.DaggerPresentationComponent;
import com.walter.cojal.easylearning.di.module.PresentationModule;
import com.walter.cojal.easylearning.presentation.main._favotire.IFavoriteContract;
import com.walter.cojal.easylearning.presentation.main._favotire.presenter.FavoritePresenter;

import java.util.ArrayList;

import javax.inject.Inject;

public class FavoriteFragment extends BaseFragment implements IFavoriteContract.IView {

    @Inject
    FavoritePresenter presenter;
    @Inject
    ProgressDialog progressDialog;
    @Inject
    FavoriteAdapter favoriteAdapter;

    private RecyclerView favorites;
    private TextView empty;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_favorite;
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
        presenter.getFavorites();
    }

    @Override
    public void setupViews() {
        empty = getActivity().findViewById(R.id.favorite_empty);
        favorites = getActivity().findViewById(R.id.favorite_items);
        favorites.setLayoutManager(new LinearLayoutManager(getActivity()));
        favorites.setItemAnimator(new DefaultItemAnimator());
        favorites.setAdapter(favoriteAdapter);
    }

    @Override
    public void setupListeners() {

    }

    @Override
    public void showProgress() {
        progressDialog.setMessage("Espere...");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showItems(ArrayList<Assessor> items) {
        favorites.setVisibility(View.VISIBLE);
        empty.setVisibility(View.GONE);
        favoriteAdapter.updateItems(items);
    }

    @Override
    public void showNoItems() {
        favorites.setVisibility(View.GONE);
        empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateItemDeleted(int position) {
        // TODO eliminar item de adapter
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
