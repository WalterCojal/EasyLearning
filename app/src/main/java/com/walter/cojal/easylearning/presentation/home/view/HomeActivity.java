package com.walter.cojal.easylearning.presentation.home.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.base.BaseActivity;
import com.walter.cojal.easylearning.data.Entities.User;
import com.walter.cojal.easylearning.di.component.DaggerPresentationComponent;
import com.walter.cojal.easylearning.di.module.PresentationModule;
import com.walter.cojal.easylearning.presentation.home.IHomeContract;
import com.walter.cojal.easylearning.presentation.home.fragmentHome.view.HomeFragment;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements IHomeContract.IView {

    private Toolbar toolbar;
    private FrameLayout frameLayout;
    @Inject
    HomeFragment homeFragment;
    private BottomNavigationView bottomNavigationView;
    @Inject
    ProgressDialog progressDialog;
    @Inject
    IHomeContract.IPresenter presenter;
    FragmentTransaction fragmentTransaction;
    User user;
    String tagShowed = "";
    private static final String FRAGMENT_TAG_HOME = "Home";
    private static final String FRAGMENT_TAG_PROFILE = "Profile";
    private static final String FRAGMENT_TAG_FAVORITE = "Favorite";

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
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
        toolbar = findViewById(R.id.home_toolbar);
        frameLayout = findViewById(R.id.home_fragment);
        bottomNavigationView = findViewById(R.id.home_navbottom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Inicio");
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationListener);
        bottomNavigationView.setOnNavigationItemReselectedListener(navigationItemReselectedListener);
        if (saveInstanceState != null) {
            tagShowed = saveInstanceState.getString("fragment");
            switch (tagShowed) {
                case FRAGMENT_TAG_HOME: homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(tagShowed);
            }
        }
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
            Log.i(getLocalClassName(), tagShowed + "Reselected");
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("fragment", tagShowed);
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
    public void showHomeFragment() {
        homeFragment.setUser(user);
        homeFragment.setRetainInstance(true);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.home_fragment, homeFragment, FRAGMENT_TAG_HOME);
        fragmentTransaction.commitAllowingStateLoss();
        tagShowed = FRAGMENT_TAG_HOME;
    }

    @Override
    public void showProfileFragment() {

    }

    @Override
    public void showFavoriteFragment() {

    }
}
