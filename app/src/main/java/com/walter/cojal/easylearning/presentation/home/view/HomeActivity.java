package com.walter.cojal.easylearning.presentation.home.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.base.BaseActivity;
import com.walter.cojal.easylearning.presentation.home.IHomeContract;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements IHomeContract.IView {

    private Toolbar toolbar;
    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;
    @Inject
    ProgressDialog progressDialog;
    @Inject
    IHomeContract.IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void resolveDaggerDependency() {

    }

    @Override
    protected void onViewReady(Bundle saveInstanceState, Intent intent) {
        super.onViewReady(saveInstanceState, intent);
        toolbar = findViewById(R.id.home_toolbar);
        frameLayout = findViewById(R.id.home_fragment);
        bottomNavigationView = findViewById(R.id.home_navbottom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Inicio");
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nab_home:
                    showHomeFragment();
                    break;
                case R.id.nab_user:
                    showProfileFragment();
                    break;
                case R.id.nab_fav:
                    showFavoriteFragment();
                    break;
                default:
                    return false;
            }
            return true;
        }
    };

    @Override
    public void showProgress() {
        progressDialog.setMessage("Espere...");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showHomeFragment() {

    }

    @Override
    public void showProfileFragment() {

    }

    @Override
    public void showFavoriteFragment() {

    }
}
