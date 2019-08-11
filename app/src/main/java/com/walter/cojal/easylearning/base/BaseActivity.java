package com.walter.cojal.easylearning.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.walter.cojal.easylearning.MyApplication;
import com.walter.cojal.easylearning.di.component.ApplicationComponent;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        onViewReady(savedInstanceState, getIntent());
    }

    protected void onViewReady(Bundle saveInstanceState, Intent intent) {
        resolveDaggerDependency();
    }

    protected void resolveDaggerDependency() {

    }

    protected abstract int getContentView();

    protected ApplicationComponent getApplicationComponent() {
        return ((MyApplication)getApplication()).getApplicationComponent();
    }

}
