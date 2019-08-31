package com.walter.cojal.easylearning.presentation.main.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.base.BaseActivity;
import com.walter.cojal.easylearning.di.component.DaggerPresentationComponent;
import com.walter.cojal.easylearning.di.module.PresentationModule;
import com.walter.cojal.easylearning.presentation.main.IMainContract;
import com.walter.cojal.easylearning.presentation.main._favotire.view.FavoriteFragment;
import com.walter.cojal.easylearning.presentation.main._home.view.HomeFragment;
import com.walter.cojal.easylearning.presentation.main._profile.view.ProfileFragment;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements IMainContract.IView {

    @Inject
    IMainContract.IPresenter presenter;
    @Inject
    HomeFragment homeFragment;
    @Inject
    ProfileFragment profileFragment;
    @Inject
    FavoriteFragment favoriteFragment;
    @Inject
    ProgressDialog progressDialog;

    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;

    FragmentTransaction fragmentTransaction;
    private static final String FRAGMENT_TAG_HOME = "Inicio";
    private static final String FRAGMENT_TAG_PROFILE = "Perfil";
    private static final String FRAGMENT_TAG_FAVORITE = "Favoritos";

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerPresentationComponent.builder()
                .applicationComponent(getApplicationComponent())
                .presentationModule(new PresentationModule(this))
                .build().inject(this);
    }

    @Override
    protected void onViewReady(Bundle saveInstanceState, Intent intent) {
        super.onViewReady(saveInstanceState, intent);
        getWindow().setBackgroundDrawable(null);
        setupViews();
        setupListeners();
        showHomeFragment();
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

    private BottomNavigationView.OnNavigationItemReselectedListener navigationItemReselectedListener = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
            Log.i(getLocalClassName(),  menuItem.getTitle() + " reselected");
        }
    };

    @Override
    public void setupViews() {
        toolbar = findViewById(R.id.main_toolbar);
        bottomNavigationView = findViewById(R.id.main_navbottom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Inicio");
    }

    @Override
    public void setupListeners() {
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationListener);
        bottomNavigationView.setOnNavigationItemReselectedListener(navigationItemReselectedListener);
    }

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
    public void showSuccess(String message) {

    }

    @Override
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void showHomeFragment() {
        setTitle(FRAGMENT_TAG_HOME);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, homeFragment, FRAGMENT_TAG_HOME);
        fragmentTransaction.commit();
    }

    @Override
    public void showProfileFragment() {
        setTitle(FRAGMENT_TAG_PROFILE);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, profileFragment, FRAGMENT_TAG_PROFILE);
        fragmentTransaction.commit();
    }

    @Override
    public void showFavoriteFragment() {
        setTitle(FRAGMENT_TAG_FAVORITE);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, favoriteFragment, FRAGMENT_TAG_FAVORITE);
        fragmentTransaction.commit();
    }
}
